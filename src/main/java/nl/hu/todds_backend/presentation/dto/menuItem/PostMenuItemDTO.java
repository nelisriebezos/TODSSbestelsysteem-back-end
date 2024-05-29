package nl.hu.todds_backend.presentation.dto.menuItem;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMenuItemDTO extends DTO {
    private String name;
    private double price;
    private Long locationId;
    private String features;
    private MultipartFile image;
}
