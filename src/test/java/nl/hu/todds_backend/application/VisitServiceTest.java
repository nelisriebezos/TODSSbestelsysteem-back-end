package nl.hu.todds_backend.application;

import nl.hu.todds_backend.data.VisitRepository;
import nl.hu.todds_backend.data.dto.*;
import nl.hu.todds_backend.data.dto.mapper.PersistVisitDTOMapper;
import nl.hu.todds_backend.domain.*;
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
class VisitServiceTest {

    @Mock
    PersistVisitDTOMapper persistVisitDTOMapper;

    @Mock
    MenuItemService menuItemService;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitService visitService;

    Location location;
    MenuItem menuItem;
    OrderItem orderItem;
    Order order;
    Visit visit;
    Order newOrder;
    OrderItem newOrderItem;

    PersistLocationDTO persistLocationDTO;
    PersistMenuItemDTO persistMenuItemDTO;
    PersistOrderItemDTO persistOrderItemDTO;
    PersistOrderDTO persistOrderDTO;
    PersistVisitDTO persistVisitDTO;
    PersistOrderDTO persistNewOrderDTO;
    PersistOrderItemDTO persistNewOrderItemDTO;

    @BeforeEach
    public void init() {
        location = new Location();
        menuItem = new MenuItem();
        orderItem = new OrderItem();
        order = new Order();
        visit = new Visit();
        newOrder = new Order();
        newOrderItem = new OrderItem();

        location.setId(1L);
        location.setName("location");

        menuItem.setId(2L);
        menuItem.setName("menu item");
        menuItem.setPrice(20.0);

        orderItem.setId(3L);
        orderItem.setAmount(3);
        orderItem.setMenuItem(menuItem);

        order.setId(4L);
        order.setStatus(OrderStatus.DONE);
        order.setOrderItems(List.of(orderItem));

        visit.setId(5L);
        visit.setTableNumber(8);
        visit.setOrders(List.of(order));

        newOrderItem.setId(7L);
        newOrderItem.setAmount(5);
        newOrderItem.setMenuItem(menuItem);

        newOrder.setId(6L);
        newOrder.setOrderItems(List.of(newOrderItem));

        persistLocationDTO = new PersistLocationDTO();
        persistMenuItemDTO = new PersistMenuItemDTO();
        persistOrderItemDTO = new PersistOrderItemDTO();
        persistOrderDTO = new PersistOrderDTO();
        persistVisitDTO = new PersistVisitDTO();
        persistNewOrderDTO = new PersistOrderDTO();
        persistNewOrderItemDTO = new PersistOrderItemDTO();

        persistLocationDTO.setId(1L);
        persistLocationDTO.setName("location");

        persistMenuItemDTO.setId(2L);
        persistMenuItemDTO.setName("menu item");
        persistMenuItemDTO.setPrice(20.0);

        persistOrderItemDTO.setId(3L);
        persistOrderItemDTO.setAmount(3);
        persistOrderItemDTO.setMenuItemName("menu item");

        persistOrderDTO.setId(4L);
        persistOrderDTO.setOrderStatus(OrderStatus.DONE);
        persistOrderDTO.setOrderItems(List.of(persistOrderItemDTO));

        persistVisitDTO.setId(5L);
        persistVisitDTO.setTableNumber(8);
        persistVisitDTO.setOrders(List.of(persistOrderDTO));

        persistNewOrderItemDTO.setId(7L);
        persistNewOrderItemDTO.setAmount(5);
        persistNewOrderItemDTO.setMenuItemName("menu item");

        persistNewOrderDTO.setId(6L);
        persistNewOrderDTO.setOrderItems(List.of(persistNewOrderItemDTO));
    }

    @Test
    @DisplayName("get visit by id")
    void getVisitById() {
        when(visitRepository.getById(visit.getId())).thenReturn(persistVisitDTO);
        when(persistVisitDTOMapper.fromDTO(persistVisitDTO)).thenReturn(visit);

        assertEquals(visit, visitService.getVisitById(visit.getId()));

        verify(visitRepository).getById(visit.getId());
        verify(persistVisitDTOMapper).fromDTO(persistVisitDTO);
    }

    @Test
    @DisplayName("persist visit")
    void persistVisit() {
        when(persistVisitDTOMapper.toDTO(visit)).thenReturn(persistVisitDTO);
        when(visitRepository.save(persistVisitDTO)).thenReturn(persistVisitDTO);
        when(persistVisitDTOMapper.fromDTO(persistVisitDTO)).thenReturn(visit);

        assertEquals(visit, visitService.persistVisit(visit));

        verify(persistVisitDTOMapper).toDTO(visit);
        verify(visitRepository).save(persistVisitDTO);
        verify(persistVisitDTOMapper).fromDTO(persistVisitDTO);
    }

    @Test
    @DisplayName("add order")
    void addOrder() {
    }

    @Test
    @DisplayName("remove order from visit")
    void removeOrderFromVisit() {
    }

    @Test
    @DisplayName("update table from visit")
    void updateTableFromVisit() {
        when(visitRepository.getById(visit.getId())).thenReturn(persistVisitDTO);
        when(persistVisitDTOMapper.toDTO(visit)).thenReturn(persistVisitDTO);
        when(visitRepository.save(persistVisitDTO)).thenReturn(persistVisitDTO);
        when(persistVisitDTOMapper.fromDTO(persistVisitDTO)).thenReturn(visit);

        assertEquals(8, visit.getTableNumber());
        assertEquals(visit, visitService.updateTableFromVisit(visit.getId(), 9));
        assertEquals(9, visit.getTableNumber());

        verify(visitRepository).getById(visit.getId());
        verify(persistVisitDTOMapper).toDTO(visit);
        verify(visitRepository).save(persistVisitDTO);
        verify(persistVisitDTOMapper, times(2)).fromDTO(persistVisitDTO);
    }
}
