package nl.hu.todds_backend.presentation.dto.orderitem;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderItemDTO extends DTO {
    private int amount;
    private Long menuItemId;
}
