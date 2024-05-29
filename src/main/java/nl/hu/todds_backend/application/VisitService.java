package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.VisitRepository;
import nl.hu.todds_backend.data.dto.mapper.PersistVisitDTOMapper;
import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.domain.Visit;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static nl.hu.todds_backend.domain.OrderStatus.SENDING;

@Transactional
@Service
@AllArgsConstructor
public class VisitService implements IVisitService {
    private final PersistVisitDTOMapper persistVisitDTOMapper;
    private final MenuItemService menuItemService;
    private final VisitRepository visitRepository;

    @Override
    public Visit getVisitById(Long id) {
        return this.persistVisitDTOMapper.fromDTO(this.visitRepository.getById(id));
    }

    @Override
    public Visit persistVisit(Visit visit) {
        return this.persistVisitDTOMapper.fromDTO(this.visitRepository.save(this.persistVisitDTOMapper.toDTO(visit)));
    }

    @Override
    public Visit addOrder(Order order, Long id) {
        Visit visit = this.getVisitById(id);
        order.getOrderItems().forEach(item -> item.setMenuItem(this.menuItemService.getMenuItemById(item.getMenuItem().getId())));
        order.setStatus(SENDING);
        visit.addOrder(order);
        return this.persistVisit(visit);
    }

    @Override
    public List<Visit> removeOrderFromVisit(Order order) {
        List<Visit> visits = persistVisitDTOMapper.fromMultipleDTO(visitRepository.getAllVisitIdWithOrderId(order.getId())
                .stream().map(visitRepository::getById).collect(Collectors.toList()));
        for (Visit visit : visits) {
            visit.removeOrder(order);
            this.persistVisit(visit);
        }
        return visits;
    }

    @Override
    public Long getVisitIdFromOrderId(Long orderId) {
        return this.visitRepository.getVisitIdWithOrderId(orderId);
    }

    @Override
    public Visit updateTableFromVisit(Long visitId, int tableNumber) {
        Visit visit = this.getVisitById(visitId);
        visit.setTableNumber(tableNumber);
        return this.persistVisit(visit);
    }
}
