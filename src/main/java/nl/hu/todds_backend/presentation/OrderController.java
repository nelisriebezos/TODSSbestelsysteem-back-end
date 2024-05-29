package nl.hu.todds_backend.presentation;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.OrderService;
import nl.hu.todds_backend.application.VisitService;
import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.presentation.dto.mapper.order.OrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.order.OrderVisitDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.order.PatchOrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.order.PutOrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;
import nl.hu.todds_backend.presentation.dto.order.OrderVisitDTO;
import nl.hu.todds_backend.presentation.dto.order.PatchOrderDTO;
import nl.hu.todds_backend.presentation.dto.order.PutOrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderDTOMapper orderDTOMapper;
    private final OrderVisitDTOMapper orderVisitDTOMapper;
    private final PutOrderDTOMapper putOrderDTOMapper;
    private final PatchOrderDTOMapper patchOrderDTOMapper;
    private final VisitService visitService;

    @GetMapping("/active/{locationId}")
    public List<OrderDTO> getAllActiveOrdersFromLocation(@PathVariable Long locationId) {
        return orderDTOMapper.toMultipleDTO(orderService.findAllActiveOrdersByLocationId(locationId));
    }

    @GetMapping("/all/{locationId}")
    public List<OrderVisitDTO> getAllOrdersFromLocation(@PathVariable Long locationId) {
        List<Order> orderList = orderService.findAllOrdersByLocationId((locationId));
        List<OrderVisitDTO> dtos = orderVisitDTOMapper.toMultipleDTO(orderList);
        dtos.forEach(dto -> dto.setVisitId(visitService.getVisitIdFromOrderId(dto.getId())));
        return dtos;
    }

    @GetMapping("/{id}")
    public OrderDTO getOrderById(@PathVariable Long id) {
        return orderDTOMapper.toDTO(orderService.getOrderById(id));
    }

    @PutMapping
    public OrderDTO putOrder(@RequestBody PutOrderDTO putOrderDTO) {
        return orderDTOMapper.toDTO(orderService.persistOrder(putOrderDTOMapper.fromDTO(putOrderDTO)));
    }

    @PatchMapping
    public OrderDTO patchOrder(@RequestBody PatchOrderDTO patchOrderDTO) {
        return orderDTOMapper.toDTO(orderService.updateOrderStatus(patchOrderDTOMapper.fromDTO(patchOrderDTO)));
    }

    @DeleteMapping("/{id}")
    public boolean deleteOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        visitService.removeOrderFromVisit(order);
        return orderService.deleteOrder(order);
    }
}
