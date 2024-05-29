package nl.hu.todds_backend.presentation.dto.mapper.order;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.presentation.dto.mapper.orderItem.PutOrderItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.order.PutOrderDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PutOrderDTOMapper implements DTOMapper<PutOrderDTO, Order> {
    private final PutOrderItemDTOMapper putOrderItemDTOMapper;

    @Override
    public PutOrderDTO toDTO(Order o) {
        return PutOrderDTO.builder()
                .id(o.getId())
                .orderItems(this.putOrderItemDTOMapper.toMultipleDTO(o.getOrderItems()))
                .build();
    }

    @Override
    public Order fromDTO(PutOrderDTO o) {
        return Order.builder()
                .id(o.getId())
                .orderItems(this.putOrderItemDTOMapper.fromMultipleDTO(o.getOrderItems()))
                .build();
    }
}
