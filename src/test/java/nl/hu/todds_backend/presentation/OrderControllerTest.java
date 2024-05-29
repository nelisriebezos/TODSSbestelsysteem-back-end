package nl.hu.todds_backend.presentation;

import nl.hu.todds_backend.application.OrderService;
import nl.hu.todds_backend.application.VisitService;
import nl.hu.todds_backend.domain.*;
import nl.hu.todds_backend.presentation.dto.location.LocationDTO;
import nl.hu.todds_backend.presentation.dto.mapper.order.OrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.order.PutOrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.menuItem.MenuItemDTO;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;
import nl.hu.todds_backend.presentation.dto.order.PutOrderDTO;
import nl.hu.todds_backend.presentation.dto.orderitem.OrderItemDTO;
import nl.hu.todds_backend.presentation.dto.orderitem.PutOrderItemDTO;
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
class OrderControllerTest {

    MockMvc mockMvc;

    @Mock
    OrderService orderService;

    @Mock
    OrderDTOMapper orderDTOMapper;

    @Mock
    PutOrderDTOMapper putOrderDTOMapper;

    @Mock
    VisitService visitService;

    @InjectMocks
    OrderController orderController;

    Location location;
    MenuItem menuItem;
    OrderItem orderItem;
    Order order;
    Order activeOrder;
    List<Order> orders;
    List<Order> activeOrders;
    Visit visit;
    List<Visit> visits;

    LocationDTO locationDTO;
    MenuItemDTO menuItemDTO;
    OrderItemDTO orderItemDTO;
    OrderDTO orderDTO;
    OrderDTO activeOrderDTO;
    List<OrderDTO> orderDTOS;
    List<OrderDTO> activeOrderDTOS;

    PutOrderItemDTO putOrderItemDTO;
    PutOrderDTO putOrderDTO;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

        location = new Location();
        menuItem = new MenuItem();
        orderItem = new OrderItem();
        order = new Order();
        activeOrder = new Order();
        visit = new Visit();
        locationDTO = new LocationDTO(1L, "location");
        menuItemDTO = new MenuItemDTO(2L, "menu item", 20.0, "", true, "", locationDTO);
        orderItemDTO = new OrderItemDTO(3L, 3, menuItemDTO);
        orderDTO = new OrderDTO(4L, OrderStatus.DONE, List.of(orderItemDTO));
        activeOrderDTO = new OrderDTO(5L, OrderStatus.PREPARING, List.of(orderItemDTO));
        putOrderItemDTO = new PutOrderItemDTO();
        putOrderDTO = new PutOrderDTO();

        location.setId(1L);
        location.setName("location");

        menuItem.setId(2L);
        menuItem.setName("menu item");
        menuItem.setPrice(20.0);
        menuItem.setFeatures("");
        menuItem.setLocation(location);
        menuItem.setPictureURI("");
        menuItem.setPublished(true);

        orderItem.setId(3L);
        orderItem.setAmount(3);
        orderItem.setMenuItem(menuItem);

        order.setId(4L);
        order.setStatus(OrderStatus.DONE);
        order.setOrderItems(List.of(orderItem));

        activeOrder.setId(5L);
        activeOrder.setStatus(OrderStatus.PREPARING);
        activeOrder.setOrderItems(List.of(orderItem));

        orders = List.of(order, activeOrder);
        activeOrders = List.of(activeOrder);

        visit.setId(6L);
        visit.setTableNumber(8);
        visit.setOrders(activeOrders);

        visits = List.of(visit);

        orderDTOS = List.of(orderDTO, activeOrderDTO);
        activeOrderDTOS = List.of(activeOrderDTO);

        putOrderItemDTO.setAmount(3);
        putOrderItemDTO.setMenuItemId(2L);

        putOrderDTO.setId(4L);
        putOrderDTO.setOrderItems(List.of(putOrderItemDTO));
    }

    @Test
    @DisplayName("get all active orders from location")
    void getAllActiveOrdersFromLocation() throws Exception {
        when(orderService.findAllActiveOrdersByLocationId(location.getId())).thenReturn(activeOrders);
        when(orderDTOMapper.toMultipleDTO(activeOrders)).thenReturn(activeOrderDTOS);

        this.mockMvc.perform(get("/order/active/{locationId}", location.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)));

        verify(orderService).findAllActiveOrdersByLocationId(location.getId());
        verify(orderDTOMapper).toMultipleDTO(activeOrders);
    }

    @Test
    @DisplayName("get all orders from location")
    void getAllOrdersFromLocation() throws Exception {
        when(orderService.findAllOrdersByLocationId(location.getId())).thenReturn(orders);
        when(orderDTOMapper.toMultipleDTO(orders)).thenReturn(orderDTOS);

        this.mockMvc.perform(get("/order/all/{locationId}", location.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

        verify(orderService).findAllOrdersByLocationId(location.getId());
        verify(orderDTOMapper).toMultipleDTO(orders);
    }

    @Test
    @DisplayName("get order by id")
    void getOrderById() throws Exception {
        when(orderService.getOrderById(order.getId())).thenReturn(order);
        when(orderDTOMapper.toDTO(order)).thenReturn(orderDTO);

        this.mockMvc.perform(get("/order/{id}", order.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(4)))
                .andExpect(jsonPath("$.status", Matchers.is("DONE")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(3)));

        verify(orderService).getOrderById(order.getId());
        verify(orderDTOMapper).toDTO(order);
    }

    @Test
    @DisplayName("put order")
    void putOrder() throws Exception {
        when(putOrderDTOMapper.fromDTO(putOrderDTO)).thenReturn(order);
        when(orderService.persistOrder(order)).thenReturn(order);

        String json = """
                {
                    "id": 4,
                    "orderItems": [
                        {
                            "amount": 3,
                            "menuItemId": 2
                        }
                    ]
                }""";

        this.mockMvc.perform(put("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("delete order")
    void deleteOrder() throws Exception {
        when(orderService.getOrderById(order.getId())).thenReturn(order);
        when(visitService.removeOrderFromVisit(order)).thenReturn(visits);
        when(orderService.deleteOrder(order)).thenReturn(true);

        this.mockMvc.perform(delete("/order/{id}", order.getId()))
                .andExpect(status().isOk());

        verify(orderService).getOrderById(order.getId());
        verify(visitService).removeOrderFromVisit(order);
        verify(orderService).deleteOrder(order);
    }
}
