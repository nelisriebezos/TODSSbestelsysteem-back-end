package nl.hu.todds_backend.data.dto.mapper;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.data.dto.PersistCategoryDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PersistCategoryDTOMapper implements DTOMapper<PersistCategoryDTO, Category> {
    private final PersistMenuItemDTOMapper persistMenuItemDTOMapper;

    @Override
    public PersistCategoryDTO toDTO(Category category) {
        return PersistCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .menuItems(persistMenuItemDTOMapper.toMultipleDTO(category.getMenuItems()))
                .subCategories(this.toMultipleDTO(category.getSubCategories()))
                .upperCategoryId(category.getUpperCategoryId())
                .pictureURI(category.getPictureURI())
                .build();
    }

    @Override
    public Category fromDTO(PersistCategoryDTO persistCategoryDTO) {
        return Category.builder()
                .id(persistCategoryDTO.getId())
                .name(persistCategoryDTO.getName())
                .description(persistCategoryDTO.getDescription())
                .menuItems(persistMenuItemDTOMapper.fromMultipleDTO(persistCategoryDTO.getMenuItems()))
                .subCategories(this.fromMultipleDTO(persistCategoryDTO.getSubCategories()))
                .upperCategoryId(persistCategoryDTO.getUpperCategoryId())
                .pictureURI(persistCategoryDTO.getPictureURI())
                .build();
    }
}
