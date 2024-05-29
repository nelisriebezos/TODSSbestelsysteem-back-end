package nl.hu.todds_backend.domain;

import lombok.*;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    private Long id;
    private int amount;
    private MenuItem menuItem;

    public double calculatePrice() {
        return this.menuItem.getPrice() * amount;
    }

    public long getLocationIdOfMenuItem() {
        return menuItem.getLocation().getId();
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderItem orderItem = (OrderItem) o;

        return Objects.equals(id, orderItem.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
