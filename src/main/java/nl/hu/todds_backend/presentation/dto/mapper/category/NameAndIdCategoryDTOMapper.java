package nl.hu.todds_backend.presentation.dto.mapper.category;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.presentation.dto.category.NameAndIdCategoryDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class NameAndIdCategoryDTOMapper implements DTOMapper<NameAndIdCategoryDTO, Category> {

    @Override
    public NameAndIdCategoryDTO toDTO(Category o) {
        return NameAndIdCategoryDTO.builder()
                .id(o.getId())
                .name(o.getName())
                .build();
    }

    @Override
    public Category fromDTO(NameAndIdCategoryDTO o) {
        return Category.builder()
                .id(o.getId())
                .name(o.getName())
                .build();
    }
}
