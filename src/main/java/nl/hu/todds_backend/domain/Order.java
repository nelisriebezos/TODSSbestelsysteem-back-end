package nl.hu.todds_backend.domain;

import lombok.*;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static nl.hu.todds_backend.domain.OrderStatus.PENDING;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private OrderStatus status;
    @ToString.Exclude
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order addItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        return this;
    }

    public void removeItem(OrderItem orderItem) {
        this.orderItems.remove(orderItem);
    }

    public void cancel(OrderItem orderItem) {
        if (status == PENDING) this.removeItem(orderItem);
    }

    public double calculatePrice() {
        return this.orderItems.stream().map(OrderItem::calculatePrice).reduce(0.0, Double::sum);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;

        return Objects.equals(id, order.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
