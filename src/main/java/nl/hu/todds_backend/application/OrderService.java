package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.OrderRepository;
import nl.hu.todds_backend.data.dto.mapper.PersistOrderDTOMapper;
import nl.hu.todds_backend.domain.Order;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static nl.hu.todds_backend.domain.OrderStatus.PENDING;

@Transactional
@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    private final PersistOrderDTOMapper persistOrderDTOMapper;
    private final OrderRepository orderRepository;
    private final MenuItemService menuItemService;

    @Override
    public List<Order> findAllActiveOrdersByLocationId(Long locationId) {
        return this.persistOrderDTOMapper.fromMultipleDTO(this.orderRepository.findAllActiveOrdersByLocationId(locationId));
    }

    @Override
    public List<Order> findAllOrdersByLocationId(Long locationId) {
        return this.persistOrderDTOMapper.fromMultipleDTO(this.orderRepository.findAllOrdersByLocationId(locationId));
    }

    @Override
    public Order getOrderById(Long id) {
        return this.persistOrderDTOMapper.fromDTO(this.orderRepository.getById(id));
    }

    @Override
    public Order updateOrderStatus(Order order) {
        Order o = this.persistOrderDTOMapper.fromDTO(this.orderRepository.getById(order.getId()));
        System.out.println(o.getStatus() + " " + o.getStatus().getClass());
        o.setStatus(order.getStatus());
        return this.persistOrderDTOMapper.fromDTO(this.orderRepository.save(this.persistOrderDTOMapper.toDTO(o)));
    }

    @Override
    public Order persistOrder(Order order) {
        if(this.orderRepository.existsById(order.getId())) {
            order.getOrderItems().forEach((item) -> item.setMenuItem(this.menuItemService.getMenuItemById(item.getMenuItem().getId())));
            order.setStatus(PENDING);
            return this.persistOrderDTOMapper.fromDTO(this.orderRepository.save(this.persistOrderDTOMapper.toDTO(order)));
        } else throw new EntityNotFoundException("Order not found");
    }

    @Override
    public boolean deleteOrder(Order order) {
        this.orderRepository.delete(this.persistOrderDTOMapper.toDTO(order));
        return true;
    }
}
