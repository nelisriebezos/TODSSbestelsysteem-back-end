package nl.hu.todds_backend.presentation.dto.mapper.category;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.presentation.dto.category.PostCategoryDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PostCategoryDTOMapper implements DTOMapper<PostCategoryDTO, Category> {
    @Override
    public PostCategoryDTO toDTO(Category o) {
        return PostCategoryDTO.builder()
                .name(o.getName())
                .description(o.getDescription())
                .build();
    }

    @Override
    public Category fromDTO(PostCategoryDTO o) {
        return Category.builder()
                .name(o.getName())
                .description(o.getDescription())
                .build();
    }
}
