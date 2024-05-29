package nl.hu.todds_backend.presentation;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.LocationService;
import nl.hu.todds_backend.application.LongPollingSession;
import nl.hu.todds_backend.application.OrderNotificationService;
import nl.hu.todds_backend.presentation.dto.location.LocationDTO;
import nl.hu.todds_backend.presentation.dto.location.PostLocationDTO;
import nl.hu.todds_backend.presentation.dto.location.PutLocationDTO;
import nl.hu.todds_backend.presentation.dto.mapper.location.LocationDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.location.PostLocationDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.location.PutLocationDTOMapper;
import nl.hu.todds_backend.presentation.dto.order.OrderDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;
    private final LocationDTOMapper locationDTOMapper;
    private final PostLocationDTOMapper postLocationDTOMapper;
    private final PutLocationDTOMapper putLocationDTOMapper;
    private final OrderNotificationService orderNotificationService;

    @GetMapping
    @RequestMapping("/realtime")
    public DeferredResult<List<OrderDTO>> publisher(@RequestParam long locationId) {
        DeferredResult<List<OrderDTO>> orderOutput = new DeferredResult<>();
        orderNotificationService.getLongPollingQueue().add(new LongPollingSession(locationId, orderOutput));
        return orderOutput;
    }

    @GetMapping
    public List<LocationDTO> getLocations() {
        return this.locationDTOMapper.toMultipleDTO(this.locationService.getAllLocations());
    }

    @PostMapping
    public LocationDTO postLocation(@RequestBody PostLocationDTO locationDTO) {
        return this.locationDTOMapper.toDTO(this.locationService.persistLocation(this.postLocationDTOMapper.fromDTO(locationDTO)));
    }

    @PutMapping
    public LocationDTO putLocation(@RequestBody PutLocationDTO putLocationDTO) {
        return this.locationDTOMapper.toDTO(this.locationService.persistLocation(this.putLocationDTOMapper.fromDTO(putLocationDTO)));
    }

    @DeleteMapping("/{id}")
    public boolean deleteLocation(@PathVariable Long id) {
        return this.locationService.deleteLocation(this.locationService.getLocationById(id));
    }
}
