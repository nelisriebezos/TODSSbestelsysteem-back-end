package nl.hu.todds_backend.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitTest {
    Visit visit;
    Order order1;

    @BeforeEach
    public void init() {
        visit = new Visit();
        MenuItem appeltje = new MenuItem();
        MenuItem eitje = new MenuItem();
        order1 = new Order();
        Order order2 = new Order();
        OrderItem orderItem1_1 = new OrderItem();
        OrderItem orderItem1_2 = new OrderItem();
        OrderItem orderItem2_1 = new OrderItem();
        OrderItem orderItem2_2 = new OrderItem();

        order1.addItem(orderItem1_1).addItem(orderItem1_2);
        order2.addItem(orderItem2_1).addItem(orderItem2_2);

        appeltje.setPrice(10.0);
        eitje.setPrice(10.0);
        orderItem1_1.setMenuItem(appeltje);
        orderItem1_1.setAmount(2);
        orderItem1_2.setMenuItem(eitje);
        orderItem1_2.setAmount(3);
        orderItem2_1.setMenuItem(appeltje);
        orderItem2_1.setAmount(4);
        orderItem2_2.setMenuItem(eitje);
        orderItem2_2.setAmount(4);

        order1.setId(1L);
        order2.setId(2L);
        visit.addOrder(order1);
        visit.addOrder(order2);
    }

    @Test
    @DisplayName("Testing calculatePrice")
    public void calculatePrice() {
        System.out.println(visit.getOrders());
        assertEquals(130.0, visit.calculatePrice());
    }

    @Test
    @DisplayName("Testing removeOrder")
    public void removeOrder() {
        visit.removeOrder(order1);
        assertEquals(1, visit.getOrders().size());
    }

    @Test
    @DisplayName("Testing addOrder if order already exists")
    public void addOrderAlreadyExists() {
        visit.addOrder(order1);
        assertEquals(2, visit.getOrders().size());
    }
}
