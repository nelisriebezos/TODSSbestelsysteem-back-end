package nl.hu.todds_backend.data.dto;

import lombok.*;
import nl.hu.todds_backend.domain.OrderStatus;
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
@Table(name = "Order_Round")
public class PersistOrderDTO extends DTO {
    @GeneratedValue
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<PersistOrderItemDTO> orderItems = new ArrayList<>();

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersistOrderDTO that = (PersistOrderDTO) o;

        return Objects.equals(id, that.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return 170859637;
    }
}
