package nl.hu.todds_backend.domain;

import lombok.*;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit {
    private Long id;
    private int tableNumber;
    @ToString.Exclude
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    public double calculatePrice() {
        return this.orders.stream().map(Order::calculatePrice).reduce(0.0, Double::sum);
    }

    public Visit addOrder(Order order) {
        if (!orders.contains(order)) orders.add(order);
        return this;
    }

    public void removeOrder(Order order) {
        orders.remove(order);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Visit visit = (Visit) o;

        return Objects.equals(id, visit.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
