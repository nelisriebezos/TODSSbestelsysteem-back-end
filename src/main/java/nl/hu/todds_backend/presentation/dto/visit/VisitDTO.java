package nl.hu.todds_backend.presentation.dto.visit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class VisitDTO extends DTO {
    private Long id;
    private int tableNumber;
    private List<OrderDTO> orders;
}
