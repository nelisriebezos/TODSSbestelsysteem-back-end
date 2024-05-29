package nl.hu.todds_backend.presentation;

import nl.hu.todds_backend.application.LocationService;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.presentation.dto.location.LocationDTO;
import nl.hu.todds_backend.presentation.dto.location.PostLocationDTO;
import nl.hu.todds_backend.presentation.dto.location.PutLocationDTO;
import nl.hu.todds_backend.presentation.dto.mapper.location.LocationDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.location.PostLocationDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.location.PutLocationDTOMapper;
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
class LocationControllerTest {

    MockMvc mockMvc;

    @Mock
    LocationService locationService;

    @Mock
    LocationDTOMapper locationDTOMapper;

    @Mock
    PostLocationDTOMapper postLocationDTOMapper;

    @Mock
    PutLocationDTOMapper putLocationDTOMapper;

    @InjectMocks
    LocationController locationController;

    Location location;
    List<Location> locations;

    LocationDTO locationDTO;
    List<LocationDTO> locationDTOS;

    PostLocationDTO postLocationDTO;

    PutLocationDTO putLocationDTO;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();

        location = new Location();
        locationDTO = new LocationDTO(1L, "location");
        postLocationDTO = new PostLocationDTO();
        putLocationDTO = new PutLocationDTO();

        location.setId(1L);
        location.setName("location");

        postLocationDTO.setName("location");

        putLocationDTO.setId(1L);
        putLocationDTO.setName("location");

        locations = List.of(location);
        locationDTOS = List.of(locationDTO);
    }

    @Test
    @DisplayName("get locations")
    void getLocations() throws Exception {
        when(locationService.getAllLocations()).thenReturn(locations);
        when(locationDTOMapper.toMultipleDTO(locations)).thenReturn(locationDTOS);

        this.mockMvc.perform(get("/location")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)));

        verify(locationService).getAllLocations();
        verify(locationDTOMapper).toMultipleDTO(locations);
    }

    @Test
    @DisplayName("post location")
    void postLocation() throws Exception {
        when(postLocationDTOMapper.fromDTO(postLocationDTO)).thenReturn(location);
        when(locationService.persistLocation(location)).thenReturn(location);
        when(locationDTOMapper.toDTO(location)).thenReturn(locationDTO);

        String json = """
                {
                    "name": "location"
                }""";

        this.mockMvc.perform(post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("location")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

        verify(postLocationDTOMapper).fromDTO(postLocationDTO);
        verify(locationService).persistLocation(location);
        verify(locationDTOMapper).toDTO(location);
    }

    @Test
    @DisplayName("put location")
    void putLocation() throws Exception {
        when(putLocationDTOMapper.fromDTO(putLocationDTO)).thenReturn(location);
        when(locationService.persistLocation(location)).thenReturn(location);
        when(locationDTOMapper.toDTO(location)).thenReturn(locationDTO);

        String json = """
                {
                    "id": 1,
                    "name": "location"
                }""";

        this.mockMvc.perform(put("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("location")))
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)));

        verify(putLocationDTOMapper).fromDTO(putLocationDTO);
        verify(locationService).persistLocation(location);
        verify(locationDTOMapper).toDTO(location);
    }

    @Test
    @DisplayName("delete location")
    void deleteLocation() throws Exception {
        when(locationService.getLocationById(location.getId())).thenReturn(location);
        when(locationService.deleteLocation(location)).thenReturn(true);

        this.mockMvc.perform(delete("/location/{id}", location.getId()))
                .andExpect(status().isOk());

        verify(locationService).getLocationById(location.getId());
        verify(locationService).deleteLocation(location);
    }
}
