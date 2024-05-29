package nl.hu.todds_backend.presentation.dto.order;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.presentation.dto.orderitem.PostOrderItemDTO;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostOrderDTO extends DTO {
    private List<PostOrderItemDTO> orderItems;
}
