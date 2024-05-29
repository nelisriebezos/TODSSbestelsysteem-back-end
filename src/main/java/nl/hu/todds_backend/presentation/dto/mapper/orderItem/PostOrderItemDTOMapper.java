package nl.hu.todds_backend.presentation.dto.mapper.orderItem;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.domain.OrderItem;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.MenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.orderitem.PostOrderItemDTO;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PostOrderItemDTOMapper implements DTOMapper<PostOrderItemDTO, OrderItem> {
    private final MenuItemDTOMapper menuItemDTOMapper;

    @Override
    public PostOrderItemDTO toDTO(OrderItem o) {
        return PostOrderItemDTO.builder()
                .amount(o.getAmount())
                .menuItemId(this.menuItemDTOMapper.toDTO(o.getMenuItem()).getId())
                .build();
    }

    @Override
    public OrderItem fromDTO(PostOrderItemDTO o) {
        return OrderItem.builder()
                .amount(o.getAmount())
                .menuItem(MenuItem.builder().id(o.getMenuItemId()).build())
                .build();
    }
}
