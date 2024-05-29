package nl.hu.todds_backend.presentation.dto.mapper.location;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.presentation.dto.location.PutLocationDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PutLocationDTOMapper implements DTOMapper<PutLocationDTO, Location> {
    @Override
    public PutLocationDTO toDTO(Location o) {
        return PutLocationDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .build();
    }

    @Override
    public Location fromDTO(PutLocationDTO o) {
        return Location.builder()
                .id(o.getId())
                .name(o.getName())
                .build();
    }
}
