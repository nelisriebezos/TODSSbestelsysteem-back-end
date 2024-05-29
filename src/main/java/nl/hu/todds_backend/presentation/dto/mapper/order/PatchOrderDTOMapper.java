package nl.hu.todds_backend.presentation.dto.mapper.order;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.presentation.dto.order.PatchOrderDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PatchOrderDTOMapper implements DTOMapper<PatchOrderDTO, Order> {

    @Override
    public PatchOrderDTO toDTO(Order o) {
        return PatchOrderDTO.builder()
                .id(o.getId())
                .status(o.getStatus())
                .build();
    }

    @Override
    public Order fromDTO(PatchOrderDTO o) {
        return Order.builder()
                .id(o.getId())
                .status(o.getStatus())
                .build();
    }
}
