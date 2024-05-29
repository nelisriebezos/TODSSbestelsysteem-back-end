package nl.hu.todds_backend.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderNotification {
    private long orderId;
    private long locationId;
}
