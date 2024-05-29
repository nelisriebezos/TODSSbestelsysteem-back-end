package nl.hu.todds_backend.presentation.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.presentation.dto.menuItem.MenuItemDTO;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class OrderItemDTO extends DTO {
    private Long id;
    private int amount;
    private MenuItemDTO menuItem;
}
