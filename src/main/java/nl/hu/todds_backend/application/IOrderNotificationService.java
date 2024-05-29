package nl.hu.todds_backend.application;

import nl.hu.todds_backend.domain.OrderNotification;

import java.util.List;

public interface IOrderNotificationService {
    public List<OrderNotification> getOrderNotifications();
    public List<OrderNotification> getAndRemoveNotifications();
    public boolean containsNotification();
    public OrderNotification save(OrderNotification orderNotification);
}
