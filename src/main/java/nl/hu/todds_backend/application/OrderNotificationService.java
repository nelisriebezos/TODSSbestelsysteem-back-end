package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.todds_backend.data.OrderNotificationRepository;
import nl.hu.todds_backend.data.dto.mapper.PersistOrderNotificationMapper;
import nl.hu.todds_backend.domain.*;
import nl.hu.todds_backend.presentation.dto.mapper.order.OrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Transactional
@Service
@AllArgsConstructor
public class OrderNotificationService implements IOrderNotificationService {

    private final OrderNotificationRepository repository;
    private final OrderService orderService;
    private final PersistOrderNotificationMapper mapper;
    private final OrderDTOMapper orderDTOMapper;
    @Getter
    private final BlockingQueue<LongPollingSession> longPollingQueue = new ArrayBlockingQueue<>(100);


    @Override
    public List<OrderNotification> getOrderNotifications() {
        return mapper.fromMultipleDTO(repository.findAll());
    }

    @Override
    public List<OrderNotification> getAndRemoveNotifications() {
        List<OrderNotification> orderNotificationDTOs = getOrderNotifications();
        List<OrderNotification> clonedList = new ArrayList<>(orderNotificationDTOs);
        orderNotificationDTOs.forEach(on -> repository.delete(mapper.toDTO(on)));
        return clonedList;
    }

    @Override
    public boolean containsNotification() {
        return !repository.findAll().isEmpty();
    }

    @Override
    public OrderNotification save(OrderNotification orderNotification) {
        return mapper.fromDTO(repository.save(mapper.toDTO(orderNotification)));
    }

    public Visit saveSendingOrders(Visit visit) {
        Map<Long, Long> orderIdAndLocationId = new HashMap<>();
        for (Order order : visit.getOrders()) {
            if (order.getStatus() == OrderStatus.SENDING) {
                for (OrderItem orderItem : order.getOrderItems()) {
                    if (!orderIdAndLocationId.containsValue(orderItem.getLocationIdOfMenuItem())) {
                        orderIdAndLocationId.put(order.getId(), orderItem.getLocationIdOfMenuItem());
                    }
                }
                order.setStatus(OrderStatus.PENDING);
            }
        }
        orderIdAndLocationId.forEach((k, v) -> this.save(new OrderNotification(k, v)));
        return visit;
    }

    public void outgoingNotifications(List<OrderNotification> orderNotifications) {
        for (OrderNotification orderNotification : orderNotifications) {
            List<OrderDTO> dtos = new ArrayList<>();
            OrderDTO dto = orderDTOMapper.toDTO(orderService.getOrderById(orderNotification.getOrderId()));
            dtos.add(dto);
            longPollingQueue.stream()
                    .filter(o -> o.getLocationId() == orderNotification.getLocationId())
                    .forEach((LongPollingSession lps) -> {
                        try {
                            lps.getDeferredResult().setResult(dtos);
                        } catch (Exception e) {
                            throw new RuntimeException();
                        }
                    });
        }
        longPollingQueue.removeIf(o -> o.getDeferredResult().isSetOrExpired());
    }
}
