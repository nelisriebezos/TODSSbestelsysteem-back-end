package nl.hu.todds_backend.presentation.dto.mapper.location;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.presentation.dto.location.PostLocationDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PostLocationDTOMapper implements DTOMapper<PostLocationDTO, Location> {
    @Override
    public PostLocationDTO toDTO(Location o) {
        return PostLocationDTO.builder()
                .name(o.getName())
                .build();
    }

    @Override
    public Location fromDTO(PostLocationDTO o) {
        return Location.builder()
                .name(o.getName())
                .build();
    }
}
