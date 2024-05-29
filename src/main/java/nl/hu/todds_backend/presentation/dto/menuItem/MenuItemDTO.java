package nl.hu.todds_backend.presentation.dto.menuItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.hu.todds_backend.presentation.dto.location.LocationDTO;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class MenuItemDTO extends DTO {
    private Long id;
    private String name;
    private double price;
    private String pictureURI;
    private boolean published;
    private String features;
    private LocationDTO location;
}
