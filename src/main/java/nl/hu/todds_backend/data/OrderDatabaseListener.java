package nl.hu.todds_backend.data;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.OrderNotificationService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@AllArgsConstructor
public class OrderDatabaseListener {
    private final OrderNotificationService service;

    @Scheduled(fixedRate = 5000)
    public void checkOrders() {
        if (service.containsNotification()) {
            service.outgoingNotifications(service.getAndRemoveNotifications());
        }
    }
}
