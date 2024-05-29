package nl.hu.todds_backend.data.dto;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Visit")
public class PersistVisitDTO extends DTO {
    @GeneratedValue
    @Id
    private Long id;
    private int tableNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<PersistOrderDTO> orders = new ArrayList<>();

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersistVisitDTO that = (PersistVisitDTO) o;

        return Objects.equals(id, that.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return 1137688976;
    }
}
