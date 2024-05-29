package nl.hu.todds_backend.presentation.dto.order;

import lombok.*;
import nl.hu.todds_backend.presentation.dto.orderitem.PutOrderItemDTO;
import nl.hu.todds_backend.utils.DTO;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PutOrderDTO extends DTO {
    private long id;
    private List<PutOrderItemDTO> orderItems;
}
