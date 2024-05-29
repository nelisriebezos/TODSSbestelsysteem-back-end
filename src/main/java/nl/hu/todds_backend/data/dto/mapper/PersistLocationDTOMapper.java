package nl.hu.todds_backend.data.dto.mapper;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.dto.PersistLocationDTO;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PersistLocationDTOMapper implements DTOMapper<PersistLocationDTO, Location> {

    @Override
    public PersistLocationDTO toDTO(Location location) {
        return PersistLocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .build();
    }

    @Override
    public Location fromDTO(PersistLocationDTO persistLocationDTO) {
        return Location.builder()
                .id(persistLocationDTO.getId())
                .name(persistLocationDTO.getName())
                .build();
    }
}
