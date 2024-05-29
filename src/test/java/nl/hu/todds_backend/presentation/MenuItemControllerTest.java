package nl.hu.todds_backend.presentation;

import nl.hu.todds_backend.application.CategoryService;
import nl.hu.todds_backend.application.LocationService;
import nl.hu.todds_backend.application.MenuItemService;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.presentation.dto.location.LocationDTO;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.AllMenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.MenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.PostMenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.PutMenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.menuItem.AllMenuItemDTO;
import nl.hu.todds_backend.presentation.dto.menuItem.MenuItemDTO;
import nl.hu.todds_backend.presentation.dto.menuItem.PutMenuItemDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MenuItemControllerTest {

    MockMvc mockMvc;

    @Mock
    MenuItemService menuItemService;

    @Mock
    AllMenuItemDTOMapper allMenuItemDTOMapper;

    @Mock
    MenuItemDTOMapper menuItemDTOMapper;

    @Mock
    PostMenuItemDTOMapper postMenuItemDTOMapper;

    @Mock
    PutMenuItemDTOMapper putMenuItemDTOMapper;

    @Mock
    LocationService locationService;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    MenuItemController menuItemController;

    Location location;
    MenuItem menuItem;
    MenuItem publishedMenuItem;
    List<MenuItem> publishedMenuItems;
    List<MenuItem> unpublishedMenuItems;
    List<MenuItem> menuItems;

    LocationDTO locationDTO;
    MenuItemDTO menuItemDTO;
    AllMenuItemDTO allMenuItemDTO;
    AllMenuItemDTO allPublishedMenuItemDTO;
    List<AllMenuItemDTO> allPublishedMenuItemDTOS;
    List<AllMenuItemDTO> allUnpublishedMenuItemDTOS;
    List<AllMenuItemDTO> allMenuItemDTOS;

    PutMenuItemDTO putMenuItemDTO;

    Category category;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(menuItemController).build();

        location = new Location();
        location.setId(1L);
        location.setName("location");

        menuItem = new MenuItem();
        menuItem.setId(2L);
        menuItem.setName("menu item");
        menuItem.setPrice(20.0);
        menuItem.setPictureURI("");
        menuItem.setFeatures("f");
        menuItem.setLocation(location);

        publishedMenuItem = new MenuItem();
        publishedMenuItem.setId(3L);
        publishedMenuItem.setName("published menu item");
        publishedMenuItem.setPrice(30.0);
        publishedMenuItem.setPublished(true);
        menuItem.setPictureURI("");
        menuItem.setFeatures("");
        menuItem.setLocation(location);

        publishedMenuItems = List.of(publishedMenuItem);
        unpublishedMenuItems = List.of(menuItem);
        menuItems = List.of(menuItem, publishedMenuItem);

        locationDTO = new LocationDTO(1L, "location");
        menuItemDTO = new MenuItemDTO(2L, "menu item", 20.0, "", false, "f", locationDTO);
        allMenuItemDTO = new AllMenuItemDTO(2L, "menu item", 20.0, "", false, locationDTO);
        allPublishedMenuItemDTO = new AllMenuItemDTO(3L, "published menu item", 30.0, "", true, locationDTO);

        allPublishedMenuItemDTOS = List.of(allPublishedMenuItemDTO);
        allUnpublishedMenuItemDTOS = List.of(allMenuItemDTO);
        allMenuItemDTOS = List.of(allMenuItemDTO, allPublishedMenuItemDTO);

        putMenuItemDTO = new PutMenuItemDTO();
        putMenuItemDTO.setId(2L);
        putMenuItemDTO.setName("menu item");
        putMenuItemDTO.setPrice(20.0);
        putMenuItemDTO.setLocationId(1L);
        putMenuItemDTO.setPublished(false);
        putMenuItemDTO.setFeatures("f");

        category = new Category();
        category.setId(4L);
        category.setName("category");
        category.setDescription("test");
    }

    @Test
    @DisplayName("get all menu items")
    void getAllMenuItems() throws Exception {
        when(menuItemService.getAllMenuItems()).thenReturn(menuItems);
        when(allMenuItemDTOMapper.toMultipleDTO(menuItems)).thenReturn(allMenuItemDTOS);

        this.mockMvc.perform(get("/menuitem")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

        verify(menuItemService).getAllMenuItems();
        verify(allMenuItemDTOMapper).toMultipleDTO(menuItems);
    }

    @Test
    @DisplayName("get all published menu items")
    void getAllPublishedMenuItems() throws Exception {
        when(menuItemService.getAllPublishedMenuItems()).thenReturn(publishedMenuItems);
        when(allMenuItemDTOMapper.toMultipleDTO(publishedMenuItems)).thenReturn(allPublishedMenuItemDTOS);

        this.mockMvc.perform(get("/menuitem/published")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)));

        verify(menuItemService).getAllPublishedMenuItems();
        verify(allMenuItemDTOMapper).toMultipleDTO(publishedMenuItems);
    }

    @Test
    @DisplayName("get all unpublished menu items")
    void getALlUnPublishedMenuItems() throws Exception {
        when(menuItemService.getAllUnPublishedMenuItems()).thenReturn(unpublishedMenuItems);
        when(allMenuItemDTOMapper.toMultipleDTO(unpublishedMenuItems)).thenReturn(allUnpublishedMenuItemDTOS);

        this.mockMvc.perform(get("/menuitem/hidden")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)));

        verify(menuItemService).getAllUnPublishedMenuItems();
        verify(allMenuItemDTOMapper).toMultipleDTO(unpublishedMenuItems);
    }

    @Test
    @DisplayName("get menu item by id")
    void getMenuItemById() throws Exception {
        when(menuItemService.getMenuItemById(menuItem.getId())).thenReturn(menuItem);
        when(menuItemDTOMapper.toDTO(menuItem)).thenReturn(menuItemDTO);

        this.mockMvc.perform(get("/menuitem/{id}", menuItem.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(2)))
                .andExpect(jsonPath("$.name", Matchers.is("menu item")))
                .andExpect(jsonPath("$.price", Matchers.is(20.0)))
                .andExpect(jsonPath("$.published", Matchers.is(false)))
                .andExpect(jsonPath("$.*", Matchers.hasSize(7)));

        verify(menuItemService).getMenuItemById(menuItem.getId());
        verify(menuItemDTOMapper).toDTO(menuItem);
    }

    @Test
    @DisplayName("post menu item")
    void postMenuItem() throws Exception {
    }

    @Test
    @DisplayName("put menu item")
    void putMenuItem() throws Exception {
    }

    @Test
    @DisplayName("publish menu item")
    void publishMenuItem() throws Exception {
    }

    @Test
    @DisplayName("hide menu item")
    void hideMenuItem() throws Exception {
    }

    @Test
    @DisplayName("delete menu item")
    void deleteMenuItem() throws Exception {
        when(menuItemService.getMenuItemById(menuItem.getId())).thenReturn(menuItem);
        when(categoryService.removeMenuItemFromCategory(menuItem)).thenReturn(List.of(category));
        when(menuItemService.deleteMenuItem(menuItem)).thenReturn(true);

        this.mockMvc.perform(delete("/menuitem/{id}", menuItem.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(menuItemService).getMenuItemById(menuItem.getId());
        verify(categoryService).removeMenuItemFromCategory(menuItem);
        verify(menuItemService).deleteMenuItem(menuItem);
    }
}
