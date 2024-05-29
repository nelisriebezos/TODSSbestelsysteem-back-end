package nl.hu.todds_backend.presentation.dto.order;

import lombok.*;
import nl.hu.todds_backend.domain.OrderStatus;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchOrderDTO extends DTO {
    private long id;
    private OrderStatus status;
}
