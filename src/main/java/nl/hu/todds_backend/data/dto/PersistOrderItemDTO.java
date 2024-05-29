package nl.hu.todds_backend.data.dto;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_Item")
public class PersistOrderItemDTO extends DTO {
    @GeneratedValue
    @Id
    private Long id;
    private int amount;
    private String menuItemName;

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersistOrderItemDTO that = (PersistOrderItemDTO) o;

        return Objects.equals(id, that.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return 1280097032;
    }
}
