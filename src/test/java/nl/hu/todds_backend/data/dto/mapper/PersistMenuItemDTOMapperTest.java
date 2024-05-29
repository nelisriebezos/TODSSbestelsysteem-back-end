package nl.hu.todds_backend.data.dto.mapper;

import nl.hu.todds_backend.data.dto.PersistLocationDTO;
import nl.hu.todds_backend.data.dto.PersistMenuItemDTO;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.domain.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersistMenuItemDTOMapperTest {
    PersistLocationDTOMapper persistLocationDTOMapper;
    PersistMenuItemDTOMapper persistMenuItemDTOMapper;
    PersistCategoryDTOMapper persistCategoryDTOMapper;
    Location location;
    MenuItem menuItem;
    PersistLocationDTO persistLocationDTO;
    PersistMenuItemDTO persistMenuItemDTO;

    @BeforeEach
    public void init() {
        persistLocationDTOMapper = new PersistLocationDTOMapper();
        persistMenuItemDTOMapper = new PersistMenuItemDTOMapper(persistLocationDTOMapper);
        persistCategoryDTOMapper = new PersistCategoryDTOMapper(persistMenuItemDTOMapper);

        location = new Location();
        menuItem = new MenuItem();

        persistLocationDTO = new PersistLocationDTO();
        persistMenuItemDTO = new PersistMenuItemDTO();

        location.setId(1L);
        location.setName("location");

        menuItem.setId(2L);
        menuItem.setName("menu item");
        menuItem.setPrice(20);
        menuItem.setLocation(location);

        persistLocationDTO.setId(1L);
        persistLocationDTO.setName("location");

        persistMenuItemDTO.setId(2L);
        persistMenuItemDTO.setName("menu item");
        persistMenuItemDTO.setPrice(20);
        persistMenuItemDTO.setLocation(persistLocationDTO);
    }

    @Test
    @DisplayName("map menu item object to menu item dto")
    void toDTO() {
        assertEquals(persistMenuItemDTO, persistMenuItemDTOMapper.toDTO(menuItem));
    }

    @Test
    @DisplayName("map menu item dto to menu item object")
    void fromDTO() {
        assertEquals(menuItem, persistMenuItemDTOMapper.fromDTO(persistMenuItemDTO));
    }
}
