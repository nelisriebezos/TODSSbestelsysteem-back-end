package nl.hu.todds_backend.presentation.dto.mapper.category;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.presentation.dto.category.PatchCategoryDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PatchCategoryDTOMapper implements DTOMapper<PatchCategoryDTO, Category> {
    @Override
    public PatchCategoryDTO toDTO(Category o) {
        return PatchCategoryDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .description(o.getDescription())
                .build();
    }

    @Override
    public Category fromDTO(PatchCategoryDTO o) {
        return Category.builder()
                .id(o.getId())
                .name(o.getName())
                .description(o.getDescription())
                .build();
    }
}
