package nl.hu.todds_backend.presentation.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.hu.todds_backend.domain.OrderStatus;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.presentation.dto.orderitem.OrderItemDTO;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
public class OrderDTO extends DTO {
    private Long id;
    private OrderStatus status;
    private List<OrderItemDTO> orderItems;
}
