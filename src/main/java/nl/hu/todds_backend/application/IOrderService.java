package nl.hu.todds_backend.application;

import nl.hu.todds_backend.domain.Order;

import java.util.List;

public interface IOrderService {
    public List<Order> findAllActiveOrdersByLocationId(Long locationId);
    public List<Order> findAllOrdersByLocationId(Long locationId);
    public Order getOrderById(Long id);

    public Order updateOrderStatus(Order order);

    public Order persistOrder(Order order);
    public boolean deleteOrder(Order order);
}
