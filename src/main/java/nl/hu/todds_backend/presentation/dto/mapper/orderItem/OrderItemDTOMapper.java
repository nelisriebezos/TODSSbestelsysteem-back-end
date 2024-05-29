package nl.hu.todds_backend.presentation.dto.mapper.orderItem;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.domain.OrderItem;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.MenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.menuItem.MenuItemDTO;
import nl.hu.todds_backend.presentation.dto.orderitem.OrderItemDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class OrderItemDTOMapper implements DTOMapper<OrderItemDTO, OrderItem> {
    private final MenuItemDTOMapper menuItemDTOMapper;

    @Override
    public OrderItemDTO toDTO(OrderItem o) {
        if (o.getMenuItem().getId() == null) {
            return OrderItemDTO.builder()
                    .id(o.getId())
                    .amount(o.getAmount())
                    .menuItem(MenuItemDTO.builder().name(o.getMenuItem().getName()).build())
                    .build();
        }
        return OrderItemDTO.builder()
                .id(o.getId())
                .amount(o.getAmount())
                .menuItem(this.menuItemDTOMapper.toDTO(o.getMenuItem()))
                .build();
    }

    @Override
    public OrderItem fromDTO(OrderItemDTO o) {
        if (o.getMenuItem().getId() == null) {
            return OrderItem.builder()
                    .id(o.getId())
                    .amount(o.getAmount())
                    .menuItem(MenuItem.builder().name(o.getMenuItem().getName()).build())
                    .build();
        }
        return OrderItem.builder()
                .id(o.getId())
                .amount(o.getAmount())
                .menuItem(this.menuItemDTOMapper.fromDTO(o.getMenuItem()))
                .build();
    }
}
