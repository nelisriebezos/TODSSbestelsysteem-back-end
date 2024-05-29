package nl.hu.todds_backend.data.dto.mapper;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.dto.PersistVisitDTO;
import nl.hu.todds_backend.domain.Visit;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PersistVisitDTOMapper implements DTOMapper<PersistVisitDTO, Visit> {
    private final PersistOrderDTOMapper persistOrderDTOMapper;

    @Override
    public PersistVisitDTO toDTO(Visit o) {
        return PersistVisitDTO.builder()
                .id(o.getId())
                .tableNumber(o.getTableNumber())
                .orders(persistOrderDTOMapper.toMultipleDTO(o.getOrders()))
                .build();
    }

    @Override
    public Visit fromDTO(PersistVisitDTO o) {
        return Visit.builder()
                .id(o.getId())
                .tableNumber(o.getTableNumber())
                .orders(persistOrderDTOMapper.fromMultipleDTO(o.getOrders()))
                .build();
    }
}
