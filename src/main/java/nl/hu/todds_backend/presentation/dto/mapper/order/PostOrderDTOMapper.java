package nl.hu.todds_backend.presentation.dto.mapper.order;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.presentation.dto.mapper.orderItem.PostOrderItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.order.PostOrderDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PostOrderDTOMapper implements DTOMapper<PostOrderDTO, Order> {
    private final PostOrderItemDTOMapper postOrderItemDTOMapper;

    @Override
    public PostOrderDTO toDTO(Order o) {
        return PostOrderDTO.builder()
                .orderItems(this.postOrderItemDTOMapper.toMultipleDTO(o.getOrderItems()))
                .build();
    }

    @Override
    public Order fromDTO(PostOrderDTO o) {
        return Order.builder()
                .orderItems(this.postOrderItemDTOMapper.fromMultipleDTO(o.getOrderItems()))
                .build();
    }
}
