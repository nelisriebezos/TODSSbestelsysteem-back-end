package nl.hu.todds_backend.presentation.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.presentation.dto.menuItem.AllMenuItemDTO;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class CategoryDTO extends DTO {
    private Long id;
    private String name;
    private String description;
    private Long upperCategoryId;
    private String pictureURI;
    private List<AllMenuItemDTO> menuItems;
    private List<AllCategoryDTO> subCategories;
}
