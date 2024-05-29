package nl.hu.todds_backend.data.dto.mapper;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.MenuItemService;
import nl.hu.todds_backend.data.dto.PersistOrderItemDTO;
import nl.hu.todds_backend.domain.OrderItem;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@ExcludeFromJacocoGeneratedReport
public class PersistOrderItemDTOMapper implements DTOMapper<PersistOrderItemDTO, OrderItem> {
    private final MenuItemService menuItemService;

    @Override
    public PersistOrderItemDTO toDTO(OrderItem o) {
        return PersistOrderItemDTO.builder()
                .id(o.getId())
                .menuItemName(o.getMenuItem().getName())
                .amount(o.getAmount())
                .build();
    }

    @Override
    public OrderItem fromDTO(PersistOrderItemDTO o) {
        return OrderItem.builder()
                .id(o.getId())
                .menuItem(menuItemService.getMenuItemByName(o.getMenuItemName()))
                .amount(o.getAmount())
                .build();
    }
}
