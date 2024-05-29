package nl.hu.todds_backend.presentation;

import nl.hu.todds_backend.application.VisitService;
import nl.hu.todds_backend.domain.*;
import nl.hu.todds_backend.presentation.dto.location.LocationDTO;
import nl.hu.todds_backend.presentation.dto.mapper.order.PostOrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.visit.PostVisitDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.visit.VisitDTOMapper;
import nl.hu.todds_backend.presentation.dto.menuItem.MenuItemDTO;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;
import nl.hu.todds_backend.presentation.dto.order.PostOrderDTO;
import nl.hu.todds_backend.presentation.dto.orderitem.OrderItemDTO;
import nl.hu.todds_backend.presentation.dto.orderitem.PostOrderItemDTO;
import nl.hu.todds_backend.presentation.dto.visit.PostVisitDTO;
import nl.hu.todds_backend.presentation.dto.visit.VisitDTO;
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
class VisitControllerTest {

    MockMvc mockMvc;

    @Mock
    VisitService visitService;

    @Mock
    VisitDTOMapper visitDTOMapper;

    @Mock
    PostVisitDTOMapper postVisitDTOMapper;

    @Mock
    PostOrderDTOMapper postOrderDTOMapper;

    @InjectMocks
    VisitController visitController;

    Location location;
    MenuItem menuItem;
    OrderItem orderItem;
    Order order;
    Visit visit;

    OrderItem newOrderItem;
    Order newOrder;

    LocationDTO locationDTO;
    MenuItemDTO menuItemDTO;
    OrderItemDTO orderItemDTO;
    OrderDTO orderDTO;
    VisitDTO visitDTO;

    PostOrderItemDTO postOrderItemDTO;
    PostOrderDTO postOrderDTO;
    PostVisitDTO postVisitDTO;

    PostOrderItemDTO newPostOrderItemDTO;
    PostOrderDTO newPostOrderDTO;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();

        location = new Location();
        menuItem = new MenuItem();
        orderItem = new OrderItem();
        order = new Order();
        visit = new Visit();
        newOrderItem = new OrderItem();
        newOrder = new Order();
        locationDTO = new LocationDTO(1L, "location");
        menuItemDTO = new MenuItemDTO(2L, "menu item", 20.0, "", true, "", locationDTO);
        orderItemDTO = new OrderItemDTO(3L, 3, menuItemDTO);
        orderDTO = new OrderDTO(4L, OrderStatus.DONE, List.of(orderItemDTO));
        visitDTO = new VisitDTO(5L, 8, List.of(orderDTO));
        postOrderItemDTO = new PostOrderItemDTO();
        postOrderDTO = new PostOrderDTO();
        postVisitDTO = new PostVisitDTO();
        newPostOrderItemDTO = new PostOrderItemDTO();
        newPostOrderDTO = new PostOrderDTO();

        location.setId(1L);
        location.setName("location");

        menuItem.setId(2L);
        menuItem.setName("menu item");
        menuItem.setPrice(20.0);
        menuItem.setPictureURI("");
        menuItem.setPublished(true);
        menuItem.setFeatures("");
        menuItem.setLocation(location);

        orderItem.setId(3L);
        orderItem.setAmount(3);
        orderItem.setMenuItem(menuItem);

        order.setId(4L);
        order.setStatus(OrderStatus.DONE);
        order.setOrderItems(List.of(orderItem));

        visit.setId(5L);
        visit.setTableNumber(8);
        visit.setOrders(List.of(order));

        newOrderItem.setId(6L);
        newOrderItem.setAmount(5);
        newOrderItem.setMenuItem(menuItem);

        newOrder.setId(7L);
        newOrder.setStatus(OrderStatus.SENDING);
        newOrder.setOrderItems(List.of(newOrderItem));

        postOrderItemDTO.setAmount(3);
        postOrderItemDTO.setMenuItemId(2L);

        postOrderDTO.setOrderItems(List.of(postOrderItemDTO));

        postVisitDTO.setTableNumber(8);

        newPostOrderItemDTO.setAmount(5);
        newPostOrderItemDTO.setMenuItemId(1L);

        newPostOrderDTO.setOrderItems(List.of(newPostOrderItemDTO));
    }

    @Test
    @DisplayName("get visit by id")
    void getVisitById() throws Exception {
        when(visitService.getVisitById(visit.getId())).thenReturn(visit);
        when(visitDTOMapper.toDTO(visit)).thenReturn(visitDTO);

        this.mockMvc.perform(get("/visit/{id}", visit.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(5)))
                .andExpect(jsonPath("$.tableNumber", Matchers.is(8)))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));

        verify(visitService).getVisitById(visit.getId());
        verify(visitDTOMapper).toDTO(visit);
    }

    @Test
    @DisplayName("post visit")
    void postVisit() throws Exception {
        when(postVisitDTOMapper.fromDTO(postVisitDTO)).thenReturn(visit);
        when(visitService.persistVisit(visit)).thenReturn(visit);
        when(visitDTOMapper.toDTO(visit)).thenReturn(visitDTO);

        String json = """
                {
                    "tableNumber": 8
                }""";

        this.mockMvc.perform(post("/visit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(5)))
                .andExpect(jsonPath("$.tableNumber", Matchers.is(8)))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));

        verify(postVisitDTOMapper).fromDTO(postVisitDTO);
        verify(visitService).persistVisit(visit);
        verify(visitDTOMapper).toDTO(visit);
    }

    @Test
    @DisplayName("patch table number")
    void patchTableNumber() throws Exception {
        visit.setTableNumber(9);
        visitDTO.setTableNumber(9);
        when(visitService.updateTableFromVisit(visit.getId(), 9)).thenReturn(visit);
        when(visitDTOMapper.toDTO(visit)).thenReturn(visitDTO);

        String json = """
                {
                    "tableNumber": 9
                }""";

        this.mockMvc.perform(patch("/visit/{id}/table", visit.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(5)))
                .andExpect(jsonPath("$.tableNumber", Matchers.is(9)))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));

        verify(visitService).updateTableFromVisit(visit.getId(), 9);
        verify(visitDTOMapper).toDTO(visit);
    }
}
