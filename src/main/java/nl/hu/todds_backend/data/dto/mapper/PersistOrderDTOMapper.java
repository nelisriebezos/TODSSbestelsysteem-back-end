package nl.hu.todds_backend.data.dto.mapper;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.dto.PersistOrderDTO;
import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PersistOrderDTOMapper implements DTOMapper<PersistOrderDTO, Order> {
    private final PersistOrderItemDTOMapper persistOrderItemDTOMapper;

    @Override
    public PersistOrderDTO toDTO(Order o) {
        return PersistOrderDTO.builder()
                .id(o.getId())
                .orderStatus(o.getStatus())
                .orderItems(persistOrderItemDTOMapper.toMultipleDTO(o.getOrderItems()))
                .build();
    }

    @Override
    public Order fromDTO(PersistOrderDTO o) {
        return Order.builder()
                .id(o.getId())
                .status(o.getOrderStatus())
                .orderItems(persistOrderItemDTOMapper.fromMultipleDTO(o.getOrderItems()))
                .build();
    }
}
