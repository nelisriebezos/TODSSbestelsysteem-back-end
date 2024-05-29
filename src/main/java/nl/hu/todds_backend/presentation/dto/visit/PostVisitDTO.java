package nl.hu.todds_backend.presentation.dto.visit;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVisitDTO extends DTO {
    private int tableNumber;
}
