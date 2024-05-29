package nl.hu.todds_backend.presentation.dto.mapper.menuItem;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.presentation.dto.menuItem.PutMenuItemDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PutMenuItemDTOMapper implements DTOMapper<PutMenuItemDTO, MenuItem> {
    @Override
    public PutMenuItemDTO toDTO(MenuItem o) {
        return PutMenuItemDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .features(o.getFeatures())
                .locationId(o.getLocation().getId())
                .price(o.getPrice())
                .published(o.isPublished())
                .build();
    }

    @Override
    public MenuItem fromDTO(PutMenuItemDTO o) {
        return MenuItem.builder()
                .id(o.getId())
                .name(o.getName())
                .features(o.getFeatures())
                .price(o.getPrice())
                .published(o.isPublished())
                .build();
    }
}
