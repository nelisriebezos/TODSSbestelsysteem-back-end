package nl.hu.todds_backend.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nl.hu.todds_backend.domain.OrderStatus.PREPARING;
import static org.junit.jupiter.api.Assertions.*;
import static nl.hu.todds_backend.domain.OrderStatus.PENDING;

public class OrderTest {
    Order order;
    OrderItem orderItem1;
    OrderItem orderItem2;

    @BeforeEach
    public void init() {
        order = new Order();
        orderItem1 = new OrderItem();
        orderItem2 = new OrderItem();
        order.addItem(orderItem1).addItem(orderItem2);
    }

    @Test
    @DisplayName("remove orderitem")
    public void removeItem() {
        order.removeItem(orderItem2);
        assertEquals(1, order.getOrderItems().size());
    }

    @Test
    @DisplayName("Cancel")
    public void cancel() {
        order.setStatus(PENDING);
        order.cancel(orderItem1);
        assertEquals(1, order.getOrderItems().size());
    }

    @Test
    @DisplayName("Cancel while status is not pending")
    public void cancelWhileStatusIsNotPending() {
        order.setStatus(PREPARING);
        order.cancel(orderItem1);
        assertEquals(2, order.getOrderItems().size());
    }
}
