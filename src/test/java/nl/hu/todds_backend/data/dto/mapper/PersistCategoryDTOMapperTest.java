package nl.hu.todds_backend.data.dto.mapper;

import nl.hu.todds_backend.data.dto.PersistCategoryDTO;
import nl.hu.todds_backend.data.dto.PersistLocationDTO;
import nl.hu.todds_backend.data.dto.PersistMenuItemDTO;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.domain.Location;
import nl.hu.todds_backend.domain.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersistCategoryDTOMapperTest {
    PersistLocationDTOMapper persistLocationDTOMapper;
    PersistMenuItemDTOMapper persistMenuItemDTOMapper;
    PersistCategoryDTOMapper persistCategoryDTOMapper;
    Location location;
    MenuItem menuItem;
    Category category;
    PersistLocationDTO persistLocationDTO;
    PersistMenuItemDTO persistMenuItemDTO;
    PersistCategoryDTO persistCategoryDTO;

    @BeforeEach
    public void init() {
        persistLocationDTOMapper = new PersistLocationDTOMapper();
        persistMenuItemDTOMapper = new PersistMenuItemDTOMapper(persistLocationDTOMapper);
        persistCategoryDTOMapper = new PersistCategoryDTOMapper(persistMenuItemDTOMapper);

        location = new Location();
        menuItem = new MenuItem();
        category = new Category();

        persistLocationDTO = new PersistLocationDTO();
        persistMenuItemDTO = new PersistMenuItemDTO();
        persistCategoryDTO = new PersistCategoryDTO();

        location.setId(1L);
        location.setName("location");

        menuItem.setId(2L);
        menuItem.setName("menu item");
        menuItem.setPrice(20);
        menuItem.setLocation(location);

        category.setId(3L);
        category.setName("category");
        category.setDescription("test");
        category.addMenuItem(menuItem);

        persistLocationDTO.setId(1L);
        persistLocationDTO.setName("location");

        persistMenuItemDTO.setId(2L);
        persistMenuItemDTO.setName("menu item");
        persistMenuItemDTO.setPrice(20);
        persistMenuItemDTO.setLocation(persistLocationDTO);

        persistCategoryDTO.setId(3L);
        persistCategoryDTO.setName("category");
        persistCategoryDTO.setDescription("test");
        persistCategoryDTO.setMenuItems(List.of(persistMenuItemDTO));
    }

    @Test
    @DisplayName("map category object to category dto")
    void toDTO() {
        assertEquals(persistCategoryDTO, persistCategoryDTOMapper.toDTO(category));
    }

    @Test
    @DisplayName("map category dto to category object")
    void fromDTO() {
        assertEquals(category, persistCategoryDTOMapper.fromDTO(persistCategoryDTO));
    }
}
