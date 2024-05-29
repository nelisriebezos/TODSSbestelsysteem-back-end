package nl.hu.todds_backend.presentation.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class PatchCategoryDTO extends DTO {
    private Long id;
    private String name;
    private String description;
    private Long upperCategoryId;
}
