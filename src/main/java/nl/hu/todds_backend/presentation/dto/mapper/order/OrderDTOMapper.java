package nl.hu.todds_backend.presentation.dto.mapper.order;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.presentation.dto.mapper.orderItem.OrderItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class OrderDTOMapper implements DTOMapper<OrderDTO, Order> {
    private final OrderItemDTOMapper orderItemDTOMapper;

    @Override
    public OrderDTO toDTO(Order o) {
        return OrderDTO.builder()
                .id(o.getId())
                .status(o.getStatus())
                .orderItems(this.orderItemDTOMapper.toMultipleDTO(o.getOrderItems()))
                .build();
    }

    @Override
    public Order fromDTO(OrderDTO o) {
        return Order.builder()
                .id(o.getId())
                .status(o.getStatus())
                .orderItems(this.orderItemDTOMapper.fromMultipleDTO(o.getOrderItems()))
                .build();
    }
}
