package nl.hu.todds_backend.application;

import nl.hu.todds_backend.data.LocationRepository;
import nl.hu.todds_backend.data.dto.PersistLocationDTO;
import nl.hu.todds_backend.data.dto.mapper.PersistLocationDTOMapper;
import nl.hu.todds_backend.domain.Location;
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
class LocationServiceTest {

    @Mock
    PersistLocationDTOMapper persistLocationDTOMapper;

    @Mock
    LocationRepository locationRepository;

    @InjectMocks
    LocationService locationService;

    Location location;
    List<Location> locations;

    PersistLocationDTO persistLocationDTO;
    List<PersistLocationDTO> persistLocationDTOS;

    @BeforeEach
    public void init() {
        location = new Location();
        location.setId(1L);
        location.setName("location");

        locations = List.of(location);

        persistLocationDTO = new PersistLocationDTO();
        persistLocationDTO.setId(1L);
        persistLocationDTO.setName("location");

        persistLocationDTOS = List.of(persistLocationDTO);
    }

    @Test
    @DisplayName("get all locations")
    void getAllLocations() {
        when(locationRepository.findAll()).thenReturn(persistLocationDTOS);
        when(persistLocationDTOMapper.fromMultipleDTO(persistLocationDTOS)).thenReturn(locations);

        assertEquals(locations, locationService.getAllLocations());

        verify(locationRepository).findAll();
        verify(persistLocationDTOMapper).fromMultipleDTO(persistLocationDTOS);
    }

    @Test
    @DisplayName("get location by id")
    void getLocationById() {
        when(locationRepository.getById(location.getId())).thenReturn(persistLocationDTO);
        when(persistLocationDTOMapper.fromDTO(persistLocationDTO)).thenReturn(location);

        assertEquals(location, locationService.getLocationById(location.getId()));

        verify(locationRepository).getById(location.getId());
        verify(persistLocationDTOMapper).fromDTO(persistLocationDTO);
    }

    @Test
    @DisplayName("persist location")
    void persistLocation() {
        when(persistLocationDTOMapper.toDTO(location)).thenReturn(persistLocationDTO);
        when(locationRepository.save(persistLocationDTO)).thenReturn(persistLocationDTO);
        when(persistLocationDTOMapper.fromDTO(persistLocationDTO)).thenReturn(location);

        assertEquals(location, locationService.persistLocation(location));

        verify(persistLocationDTOMapper).toDTO(location);
        verify(locationRepository).save(persistLocationDTO);
        verify(persistLocationDTOMapper).fromDTO(persistLocationDTO);
    }
}
