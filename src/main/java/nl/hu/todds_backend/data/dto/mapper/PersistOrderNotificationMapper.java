package nl.hu.todds_backend.data.dto.mapper;

import nl.hu.todds_backend.data.dto.PersistOrderNotificationDTO;
import nl.hu.todds_backend.domain.OrderNotification;
import nl.hu.todds_backend.utils.DTOMapper;
import org.springframework.stereotype.Component;

@Component
public class PersistOrderNotificationMapper implements DTOMapper<PersistOrderNotificationDTO, OrderNotification> {
    @Override
    public PersistOrderNotificationDTO toDTO(OrderNotification o) {
        return PersistOrderNotificationDTO.builder()
                .orderId(o.getOrderId())
                .locationId(o.getLocationId())
                .build();
    }

    @Override
    public OrderNotification fromDTO(PersistOrderNotificationDTO o) {
        return OrderNotification.builder()
                .orderId(o.getOrderId())
                .locationId(o.getLocationId())
                .build();
    }
}
