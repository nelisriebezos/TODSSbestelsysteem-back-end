package nl.hu.todds_backend.presentation;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.CategoryService;
import nl.hu.todds_backend.application.LocationService;
import nl.hu.todds_backend.application.MenuItemService;
import nl.hu.todds_backend.domain.MenuItem;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.AllMenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.MenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.PostMenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.mapper.menuItem.PutMenuItemDTOMapper;
import nl.hu.todds_backend.presentation.dto.menuItem.AllMenuItemDTO;
import nl.hu.todds_backend.presentation.dto.menuItem.MenuItemDTO;
import nl.hu.todds_backend.presentation.dto.menuItem.PostMenuItemDTO;
import nl.hu.todds_backend.presentation.dto.menuItem.PutMenuItemDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/menuitem")
public class MenuItemController {
    private final MenuItemService menuItemService;
    private final AllMenuItemDTOMapper allMenuItemDTOMapper;
    private final MenuItemDTOMapper menuItemDTOMapper;
    private final PostMenuItemDTOMapper postMenuItemDTOMapper;
    private final PutMenuItemDTOMapper putMenuItemDTOMapper;
    private final LocationService locationService;
    private final CategoryService categoryService;

    @GetMapping
    public List<AllMenuItemDTO> getAllMenuItems() {
        return allMenuItemDTOMapper.toMultipleDTO(menuItemService.getAllMenuItems());
    }

    @GetMapping("/published")
    public List<AllMenuItemDTO> getAllPublishedMenuItems() {
        return allMenuItemDTOMapper.toMultipleDTO(menuItemService.getAllPublishedMenuItems());
    }

    @GetMapping("/hidden")
    public List<AllMenuItemDTO> getALlUnPublishedMenuItems() {
        return allMenuItemDTOMapper.toMultipleDTO(menuItemService.getAllUnPublishedMenuItems());
    }

    @GetMapping("/{id}")
    public MenuItemDTO getMenuItemById(@PathVariable Long id) {
        return menuItemDTOMapper.toDTO(menuItemService.getMenuItemById(id));
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public MenuItemDTO postMenuItem(@ModelAttribute PostMenuItemDTO postMenuItemDTO) throws IOException {
        MenuItemDTO menuItemDTO = menuItemDTOMapper.toDTO(menuItemService.persistMenuItem(postMenuItemDTOMapper.fromDTO(postMenuItemDTO)));
        if (postMenuItemDTO.getImage() == null) return menuItemDTO;
        return menuItemDTOMapper.toDTO(menuItemService.persistMenuItemImage(menuItemDTOMapper.fromDTO(menuItemDTO), postMenuItemDTO.getImage()));
    }

    @PutMapping
    public MenuItemDTO putMenuItem(@RequestBody PutMenuItemDTO putMenuItemDTO) {
        MenuItem menuItem = putMenuItemDTOMapper.fromDTO(putMenuItemDTO);
        menuItem.setLocation(locationService.getLocationById(putMenuItemDTO.getLocationId()));
        menuItem.setPictureURI(this.menuItemService.getMenuItemById(putMenuItemDTO.getId()).getPictureURI());
        return menuItemDTOMapper.toDTO(menuItemService.persistMenuItem(menuItem));
    }

    @PatchMapping("/publish")
    public MenuItemDTO publishMenuItem(@RequestParam Long id) {
        return menuItemDTOMapper.toDTO(menuItemService.persistMenuItem(menuItemService.getMenuItemById(id).publish()));
    }

    @PatchMapping("/hide")
    public MenuItemDTO hideMenuItem(@RequestParam Long id) {
        return menuItemDTOMapper.toDTO(menuItemService.persistMenuItem(menuItemService.getMenuItemById(id).publish()));
    }

    @DeleteMapping("/{id}")
    public boolean deleteMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemService.getMenuItemById(id);
        categoryService.removeMenuItemFromCategory(menuItem);
        return menuItemService.deleteMenuItem(menuItem);
    }
}
