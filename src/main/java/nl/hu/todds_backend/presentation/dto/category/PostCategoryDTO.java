package nl.hu.todds_backend.presentation.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.hu.todds_backend.utils.DTO;
import org.springframework.web.multipart.MultipartFile;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class PostCategoryDTO extends DTO {
    private String name;
    private String description;
    private MultipartFile image;
}
