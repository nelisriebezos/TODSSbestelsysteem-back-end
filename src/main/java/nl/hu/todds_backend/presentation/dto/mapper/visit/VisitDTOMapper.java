package nl.hu.todds_backend.presentation.dto.mapper.visit;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Visit;
import nl.hu.todds_backend.presentation.dto.mapper.order.OrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.visit.VisitDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class VisitDTOMapper implements DTOMapper<VisitDTO, Visit> {
    private final OrderDTOMapper orderDTOMapper;

    @Override
    public VisitDTO toDTO(Visit visit) {
        return VisitDTO.builder()
                .id(visit.getId())
                .tableNumber(visit.getTableNumber())
                .orders(orderDTOMapper.toMultipleDTO(visit.getOrders()))
                .build();
    }

    @Override
    public Visit fromDTO(VisitDTO visitDTO) {
        return Visit.builder()
                .id(visitDTO.getId())
                .tableNumber(visitDTO.getTableNumber())
                .orders(orderDTOMapper.fromMultipleDTO(visitDTO.getOrders()))
                .build();
    }
}
