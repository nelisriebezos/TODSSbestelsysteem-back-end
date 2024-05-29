package nl.hu.todds_backend.presentation.dto.visit;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PutVisitDTO extends DTO {
    private long id;
    private int tableNumber;
}
