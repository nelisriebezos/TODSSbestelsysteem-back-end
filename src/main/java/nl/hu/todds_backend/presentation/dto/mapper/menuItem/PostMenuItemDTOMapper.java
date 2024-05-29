package nl.hu.todds_backend.presentation.dto.mapper.menuItem;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.presentation.dto.menuItem.PostMenuItemDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PostMenuItemDTOMapper implements DTOMapper<PostMenuItemDTO, MenuItem> {
    @Override
    public PostMenuItemDTO toDTO(MenuItem o) {
        return PostMenuItemDTO.builder()
                .name(o.getName())
                .features(o.getFeatures())
                .locationId(o.getLocation().getId())
                .price(o.getPrice())
                .build();
    }

    @Override
    public MenuItem fromDTO(PostMenuItemDTO o) {
        return MenuItem.builder()
                .name(o.getName())
                .features(o.getFeatures())
                .location(Location.builder().id(o.getLocationId()).build())
                .price(o.getPrice())
                .build();
    }
}
