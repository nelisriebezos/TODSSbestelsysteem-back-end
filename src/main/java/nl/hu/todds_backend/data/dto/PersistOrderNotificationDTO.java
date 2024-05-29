package nl.hu.todds_backend.data.dto;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_Notification")
public class PersistOrderNotificationDTO extends DTO {
    @Id
    private long orderId;
    private long locationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersistOrderNotificationDTO that = (PersistOrderNotificationDTO) o;
        return orderId == that.orderId && locationId == that.locationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, locationId);
    }
}
