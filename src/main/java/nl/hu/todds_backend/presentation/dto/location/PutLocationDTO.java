package nl.hu.todds_backend.presentation.dto.location;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PutLocationDTO extends DTO {
    private Long id;
    private String name;
}
