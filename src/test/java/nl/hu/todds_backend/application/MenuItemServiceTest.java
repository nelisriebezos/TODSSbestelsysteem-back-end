package nl.hu.todds_backend.application;

import nl.hu.todds_backend.data.MenuItemRepository;
import nl.hu.todds_backend.data.dto.PersistLocationDTO;
import nl.hu.todds_backend.data.dto.PersistMenuItemDTO;
import nl.hu.todds_backend.data.dto.mapper.PersistMenuItemDTOMapper;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.domain.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceTest {

    @Mock
    PersistMenuItemDTOMapper persistMenuItemDTOMapper;

    @Mock
    MenuItemRepository menuItemRepository;

    @Mock
    ImageService imageService;

    @InjectMocks
    MenuItemService menuItemService;

    Location location;
    MenuItem menuItem;
    MenuItem publishedMenuItem;
    List<MenuItem> publishedMenuItems;
    List<MenuItem> unpublishedMenuItems;
    List<MenuItem> menuItems;

    PersistLocationDTO persistLocationDTO;
    PersistMenuItemDTO persistMenuItemDTO;
    PersistMenuItemDTO persistPublishedMenuItemDTO;
    List<PersistMenuItemDTO> persistPublishedMenuItemDTOS;
    List<PersistMenuItemDTO> persistUnpublishedMenuItemDTOS;
    List<PersistMenuItemDTO> persistMenuItemDTOS;

    @BeforeEach
    public void init() {
        location = new Location();
        location.setId(1L);
        location.setName("location");

        menuItem = new MenuItem();
        menuItem.setId(2L);
        menuItem.setName("menu item");
        menuItem.setPrice(20.0);

        publishedMenuItem = new MenuItem();
        publishedMenuItem.setId(3L);
        publishedMenuItem.setName("published menu item");
        publishedMenuItem.setPrice(30.0);
        publishedMenuItem.setPublished(true);

        publishedMenuItems = List.of(publishedMenuItem);
        unpublishedMenuItems = List.of(menuItem);
        menuItems = List.of(menuItem, publishedMenuItem);

        persistLocationDTO = new PersistLocationDTO();
        persistLocationDTO.setId(1L);
        persistLocationDTO.setName("location");

        persistMenuItemDTO = new PersistMenuItemDTO();
        persistMenuItemDTO.setId(2L);
        persistMenuItemDTO.setName("menu item");
        persistMenuItemDTO.setPrice(20.0);

        persistPublishedMenuItemDTO = new PersistMenuItemDTO();
        persistPublishedMenuItemDTO.setId(3L);
        persistPublishedMenuItemDTO.setName("published menu item");
        persistPublishedMenuItemDTO.setPrice(30.0);
        persistPublishedMenuItemDTO.setPublished(true);

        persistPublishedMenuItemDTOS = List.of(persistPublishedMenuItemDTO);
        persistUnpublishedMenuItemDTOS = List.of(persistMenuItemDTO);
        persistMenuItemDTOS = List.of(persistMenuItemDTO, persistPublishedMenuItemDTO);
    }

    @Test
    @DisplayName("get all menu items")
    void getAllMenuItems() {
        when(menuItemRepository.findAll()).thenReturn(persistMenuItemDTOS);
        when(persistMenuItemDTOMapper.fromMultipleDTO(persistMenuItemDTOS)).thenReturn(menuItems);

        assertEquals(menuItems, menuItemService.getAllMenuItems());

        verify(menuItemRepository).findAll();
        verify(persistMenuItemDTOMapper).fromMultipleDTO(persistMenuItemDTOS);
    }

    @Test
    @DisplayName("get all published menu items")
    void getAllPublishedMenuItems() {
        when(menuItemRepository.getAllByPublishedIsTrue()).thenReturn(persistPublishedMenuItemDTOS);
        when(persistMenuItemDTOMapper.fromMultipleDTO(persistPublishedMenuItemDTOS)).thenReturn(publishedMenuItems);

        assertEquals(publishedMenuItems, menuItemService.getAllPublishedMenuItems());

        verify(menuItemRepository).getAllByPublishedIsTrue();
        verify(persistMenuItemDTOMapper).fromMultipleDTO(persistPublishedMenuItemDTOS);
    }

    @Test
    @DisplayName("get all unpublished menu items")
    void getAllUnPublishedMenuItems() {
        when(menuItemRepository.getAllByPublishedIsFalse()).thenReturn(persistUnpublishedMenuItemDTOS);
        when(persistMenuItemDTOMapper.fromMultipleDTO(persistUnpublishedMenuItemDTOS)).thenReturn(unpublishedMenuItems);

        assertEquals(unpublishedMenuItems, menuItemService.getAllUnPublishedMenuItems());

        verify(menuItemRepository).getAllByPublishedIsFalse();
        verify(persistMenuItemDTOMapper).fromMultipleDTO(persistUnpublishedMenuItemDTOS);
    }

    @Test
    @DisplayName("get menu item by id")
    void getMenuItemById() {
        when(menuItemRepository.getById(menuItem.getId())).thenReturn(persistMenuItemDTO);
        when(persistMenuItemDTOMapper.fromDTO(persistMenuItemDTO)).thenReturn(menuItem);

        assertEquals(menuItem, menuItemService.getMenuItemById(menuItem.getId()));

        verify(menuItemRepository).getById(menuItem.getId());
        verify(persistMenuItemDTOMapper).fromDTO(persistMenuItemDTO);
    }

    @Test
    @DisplayName("get menu item by name")
    void getMenuItemByName() {
        when(menuItemRepository.getByName(menuItem.getName())).thenReturn(persistMenuItemDTO);
        when(persistMenuItemDTOMapper.fromDTO(persistMenuItemDTO)).thenReturn(menuItem);

        assertEquals(menuItem, menuItemService.getMenuItemByName(menuItem.getName()));

        verify(menuItemRepository).getByName(menuItem.getName());
        verify(persistMenuItemDTOMapper).fromDTO(persistMenuItemDTO);
    }

    @Test
    @DisplayName("persist menu item")
    void persistMenuItem() {
        when(persistMenuItemDTOMapper.toDTO(menuItem)).thenReturn(persistMenuItemDTO);
        when(menuItemRepository.save(persistMenuItemDTO)).thenReturn(persistMenuItemDTO);
        when(persistMenuItemDTOMapper.fromDTO(persistMenuItemDTO)).thenReturn(menuItem);

        assertEquals(menuItem, menuItemService.persistMenuItem(menuItem));

        verify(persistMenuItemDTOMapper).toDTO(menuItem);
        verify(menuItemRepository).save(persistMenuItemDTO);
        verify(persistMenuItemDTOMapper).fromDTO(persistMenuItemDTO);
    }

    @Test
    @DisplayName("persist menu item image")
    void persistMenuItemImage() {
    }

    @Test
    @DisplayName("delete menu item")
    void deleteMenuItem() {
        when(persistMenuItemDTOMapper.toDTO(menuItem)).thenReturn(persistMenuItemDTO);

        assertTrue(menuItemService.deleteMenuItem(menuItem));

        verify(persistMenuItemDTOMapper).toDTO(menuItem);
        verify(menuItemRepository).delete(persistMenuItemDTO);
    }
}
