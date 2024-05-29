package nl.hu.todds_backend.presentation.dto.mapper.category;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.presentation.dto.category.CategoryDTO;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.AllMenuItemDTOMapper;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class CategoryDTOMapper implements DTOMapper<CategoryDTO, Category> {
    private final AllCategoryDTOMapper allCategoryDTOMapper;
    private final AllMenuItemDTOMapper allMenuItemDTOMapper;

    @Override
    public CategoryDTO toDTO(Category o) {
        return CategoryDTO.builder()
                .id(o.getId())
                .description(o.getDescription())
                .name(o.getName())
                .subCategories(this.allCategoryDTOMapper.toMultipleDTO(o.getSubCategories()))
                .upperCategoryId(o.getUpperCategoryId())
                .menuItems(this.allMenuItemDTOMapper.toMultipleDTO(o.getMenuItems()))
                .pictureURI(o.getPictureURI())
                .build();
    }

    @Override
    public Category fromDTO(CategoryDTO o) {
        return Category.builder()
                .id(o.getId())
                .description(o.getDescription())
                .name(o.getName())
                .subCategories(this.allCategoryDTOMapper.fromMultipleDTO(o.getSubCategories()))
                .upperCategoryId(o.getUpperCategoryId())
                .menuItems(this.allMenuItemDTOMapper.fromMultipleDTO(o.getMenuItems()))
                .pictureURI(o.getPictureURI())
                .build();
    }
}
