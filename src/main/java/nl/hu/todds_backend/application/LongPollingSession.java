package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@AllArgsConstructor
@Getter
public class LongPollingSession {
    private final long locationId;
    private final DeferredResult<List<OrderDTO>> deferredResult;
}
