package nl.hu.todds_backend.presentation.dto.mapper.visit;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Visit;
import nl.hu.todds_backend.presentation.dto.visit.PostVisitDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PostVisitDTOMapper implements DTOMapper<PostVisitDTO, Visit> {
    @Override
    public PostVisitDTO toDTO(Visit visit) {
        return PostVisitDTO.builder()
                .tableNumber(visit.getTableNumber())
                .build();
    }

    @Override
    public Visit fromDTO(PostVisitDTO postVisitDTO) {
        return Visit.builder()
                .tableNumber(postVisitDTO.getTableNumber())
                .build();
    }
}
