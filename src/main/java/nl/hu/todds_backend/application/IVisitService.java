package nl.hu.todds_backend.application;

import nl.hu.todds_backend.domain.Order;
import nl.hu.todds_backend.domain.Visit;

import java.util.List;

public interface IVisitService {
    public Visit getVisitById(Long id);
    public Visit persistVisit(Visit visit);

    Long getVisitIdFromOrderId(Long orderId);

    public Visit updateTableFromVisit(Long visitId, int tableNumber);
    public Visit addOrder(Order order, Long id);
    public List<Visit> removeOrderFromVisit(Order order);
}
