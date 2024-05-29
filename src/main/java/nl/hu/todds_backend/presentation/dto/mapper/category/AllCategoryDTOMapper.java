package nl.hu.todds_backend.presentation.dto.mapper.category;

import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.presentation.dto.category.AllCategoryDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@ExcludeFromJacocoGeneratedReport
public class AllCategoryDTOMapper implements DTOMapper<AllCategoryDTO, Category> {
    @Override
    public AllCategoryDTO toDTO(Category o) {
        return AllCategoryDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .subCategories(this.toMultipleDTO(o.getSubCategories()))
                .pictureURI(o.getPictureURI())
                .build();
    }

    @Override
    public Category fromDTO(AllCategoryDTO o) {
        return Category.builder()
                .id(o.getId())
                .name(o.getName())
                .subCategories(this.fromMultipleDTO(o.getSubCategories()))
                .pictureURI(o.getPictureURI())
                .build();
    }
}
