package nl.hu.todds_backend.presentation.dto.mapper.orderItem;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.domain.OrderItem;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.MenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.orderitem.PutOrderItemDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PutOrderItemDTOMapper implements DTOMapper<PutOrderItemDTO, OrderItem> {
    private final MenuItemDTOMapper menuItemDTOMapper;

    @Override
    public PutOrderItemDTO toDTO(OrderItem o) {
        return PutOrderItemDTO.builder()
                .amount(o.getAmount())
                .menuItemId((this.menuItemDTOMapper.toDTO(o.getMenuItem()).getId()))
                .build();
    }

    @Override
    public OrderItem fromDTO(PutOrderItemDTO o) {
        return OrderItem.builder()
                .amount(o.getAmount())
                .menuItem(MenuItem.builder().id(o.getMenuItemId()).build())
                .build();
    }
}
