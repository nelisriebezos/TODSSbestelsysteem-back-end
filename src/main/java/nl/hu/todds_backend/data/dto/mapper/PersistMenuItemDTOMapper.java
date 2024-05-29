package nl.hu.todds_backend.data.dto.mapper;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.dto.PersistMenuItemDTO;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PersistMenuItemDTOMapper implements DTOMapper<PersistMenuItemDTO, MenuItem> {
    private final PersistLocationDTOMapper persistLocationDTOMapper;

    @Override
    public PersistMenuItemDTO toDTO(MenuItem menuItem) {
        return PersistMenuItemDTO.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .price(menuItem.getPrice())
                .features(menuItem.getFeatures())
                .location(persistLocationDTOMapper.toDTO(menuItem.getLocation()))
                .pictureURI(menuItem.getPictureURI())
                .published(menuItem.isPublished())
                .build();
    }

    @Override
    public MenuItem fromDTO(PersistMenuItemDTO persistMenuItemDTO) {
        return MenuItem.builder()
                .id(persistMenuItemDTO.getId())
                .name(persistMenuItemDTO.getName())
                .price(persistMenuItemDTO.getPrice())
                .features(persistMenuItemDTO.getFeatures())
                .location(persistLocationDTOMapper.fromDTO(persistMenuItemDTO.getLocation()))
                .pictureURI(persistMenuItemDTO.getPictureURI())
                .published(persistMenuItemDTO.isPublished())
                .build();
    }
}
