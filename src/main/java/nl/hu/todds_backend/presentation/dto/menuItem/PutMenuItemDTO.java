package nl.hu.todds_backend.presentation.dto.menuItem;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PutMenuItemDTO extends DTO {
    private long id;
    private String name;
    private double price;
    private Long locationId;
    private boolean published;
    private String features;
}
