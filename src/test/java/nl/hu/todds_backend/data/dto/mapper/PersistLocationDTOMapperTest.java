package nl.hu.todds_backend.data.dto.mapper;

import nl.hu.todds_backend.data.dto.PersistLocationDTO;
import nl.hu.todds_backend.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersistLocationDTOMapperTest {
    PersistLocationDTOMapper persistLocationDTOMapper;
    Location location;
    PersistLocationDTO persistLocationDTO;

    @BeforeEach
    public void init() {
        persistLocationDTOMapper = new PersistLocationDTOMapper();

        location = new Location();

        persistLocationDTO = new PersistLocationDTO();

        location.setId(1L);
        location.setName("location");

        persistLocationDTO.setId(1L);
        persistLocationDTO.setName("location");
    }

    @Test
    @DisplayName("map location object to location dto")
    void toDTO() {
        assertEquals(persistLocationDTO, persistLocationDTOMapper.toDTO(location));
    }

    @Test
    @DisplayName("map location dto to location object")
    void fromDTO() {
        assertEquals(location, persistLocationDTOMapper.fromDTO(persistLocationDTO));
    }
}
