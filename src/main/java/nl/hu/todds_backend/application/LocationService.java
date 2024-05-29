package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.LocationRepository;
import nl.hu.todds_backend.data.dto.mapper.PersistLocationDTOMapper;
import nl.hu.todds_backend.domain.Location;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class LocationService implements ILocationService {
    private final PersistLocationDTOMapper persistLocationDTOMapper;
    private final LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return this.persistLocationDTOMapper.fromMultipleDTO(this.locationRepository.findAll());
    }

    @Override
    public Location getLocationById(Long id) {
        return this.persistLocationDTOMapper.fromDTO(this.locationRepository.getById(id));
    }

    @Override
    public Location persistLocation(Location location) {
        return this.persistLocationDTOMapper.fromDTO(this.locationRepository.save(this.persistLocationDTOMapper.toDTO(location)));
    }

    @Override
    public boolean deleteLocation(Location location) {
        this.locationRepository.delete(this.persistLocationDTOMapper.toDTO(location));
        return true;
    }
}
