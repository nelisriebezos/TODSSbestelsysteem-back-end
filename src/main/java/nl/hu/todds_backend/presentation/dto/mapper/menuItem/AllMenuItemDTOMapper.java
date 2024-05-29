package nl.hu.todds_backend.presentation.dto.mapper.menuItem;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.presentation.dto.mapper.location.LocationDTOMapper;
import nl.hu.todds_backend.presentation.dto.menuItem.AllMenuItemDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class AllMenuItemDTOMapper implements DTOMapper<AllMenuItemDTO, MenuItem> {
    private final LocationDTOMapper locationDTOMapper;

    @Override
    public AllMenuItemDTO toDTO(MenuItem o) {
        return AllMenuItemDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .price(o.getPrice())
                .pictureURI(o.getPictureURI())
                .location(this.locationDTOMapper.toDTO(o.getLocation()))
                .published(o.isPublished())
                .build();
    }

    @Override
    public MenuItem fromDTO(AllMenuItemDTO o) {
        return MenuItem.builder()
                .id(o.getId())
                .name(o.getName())
                .price(o.getPrice())
                .pictureURI(o.getPictureURI())
                .location(this.locationDTOMapper.fromDTO(o.getLocation()))
                .published(o.isPublished())
                .build();
    }
}
