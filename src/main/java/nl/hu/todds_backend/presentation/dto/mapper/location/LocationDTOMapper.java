package nl.hu.todds_backend.presentation.dto.mapper.location;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.presentation.dto.location.LocationDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class LocationDTOMapper implements DTOMapper<LocationDTO, Location> {
    @Override
    public LocationDTO toDTO(Location o) {
        return LocationDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .build();
    }

    @Override
    public Location fromDTO(LocationDTO o) {
        return Location.builder()
                .id(o.getId())
                .name(o.getName())
                .build();
    }
}
