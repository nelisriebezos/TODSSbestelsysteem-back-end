package nl.hu.todds_backend.presentation;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.OrderNotificationService;
import nl.hu.todds_backend.application.VisitService;
import nl.hu.todds_backend.presentation.dto.mapper.order.PostOrderDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.visit.PostVisitDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.visit.VisitDTOMapper;
import nl.hu.todds_backend.presentation.dto.order.PostOrderDTO;
import nl.hu.todds_backend.presentation.dto.visit.PostVisitDTO;
import nl.hu.todds_backend.presentation.dto.visit.VisitDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/visit")
public class VisitController {
    private final VisitService visitService;
    private final VisitDTOMapper visitDTOMapper;
    private final PostVisitDTOMapper postVisitDTOMapper;
    private final PostOrderDTOMapper orderDTOMapper;
    private final OrderNotificationService orderNotificationService;

    @GetMapping("/{id}")
    public VisitDTO getVisitById(@PathVariable Long id) {
        return visitDTOMapper.toDTO(visitService.getVisitById(id));
    }

    @PostMapping
    public VisitDTO postVisit(@RequestBody PostVisitDTO postVisitDTO) {
        return visitDTOMapper.toDTO(visitService.persistVisit(postVisitDTOMapper.fromDTO(postVisitDTO)));
    }

    @PatchMapping("/{id}/table")
    public VisitDTO patchTableNumber(@PathVariable Long id, @RequestBody PostVisitDTO postVisitDTO) {
        return visitDTOMapper.toDTO(visitService.updateTableFromVisit(id, postVisitDTO.getTableNumber()));
    }

    @PatchMapping("/{id}/order")
    public VisitDTO patchOrder(@PathVariable Long id, @RequestBody PostOrderDTO postOrderDTO) {
        VisitDTO visit = visitDTOMapper.toDTO(visitService.addOrder(orderDTOMapper.fromDTO(postOrderDTO), id));
        return visitDTOMapper.toDTO(visitService.persistVisit(orderNotificationService.saveSendingOrders(visitDTOMapper.fromDTO(visit))));
    }
}
