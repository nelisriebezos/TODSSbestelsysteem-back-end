package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.MenuItemRepository;
import nl.hu.todds_backend.data.dto.PersistMenuItemDTO;
import nl.hu.todds_backend.data.dto.mapper.PersistMenuItemDTOMapper;
import nl.hu.todds_backend.domain.MenuItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class MenuItemService implements IMenuItemService {
    private final PersistMenuItemDTOMapper persistMenuItemDTOMapper;
    private final MenuItemRepository menuItemRepository;
    private final ImageService imageService;

    @Override
    public List<MenuItem> getAllMenuItems() {
        return this.persistMenuItemDTOMapper.fromMultipleDTO(this.menuItemRepository.findAll());
    }

    @Override
    public List<MenuItem> getAllPublishedMenuItems() {
        return this.persistMenuItemDTOMapper.fromMultipleDTO(this.menuItemRepository.getAllByPublishedIsTrue());
    }

    @Override
    public List<MenuItem> getAllUnPublishedMenuItems() {
        return this.persistMenuItemDTOMapper.fromMultipleDTO(this.menuItemRepository.getAllByPublishedIsFalse());
    }

    @Override
    public MenuItem getMenuItemById(Long id) {
        return this.persistMenuItemDTOMapper.fromDTO(this.menuItemRepository.getById(id));
    }

    @Override
    public MenuItem getMenuItemByName(String menuItemName) {
        PersistMenuItemDTO menuItemFromDB = this.menuItemRepository.getByName(menuItemName);
        if (menuItemFromDB == null) {
            MenuItem menuItemNameHolder = new MenuItem();
            menuItemNameHolder.setName(menuItemName);
            return menuItemNameHolder;
        }
        return this.persistMenuItemDTOMapper.fromDTO(menuItemFromDB);
    }

    @Override
    public MenuItem persistMenuItem(MenuItem menuItem) {
        return this.persistMenuItemDTOMapper.fromDTO(this.menuItemRepository.save(this.persistMenuItemDTOMapper.toDTO(menuItem)));
    }

    @Override
    public MenuItem persistMenuItemImage(MenuItem menuItem, MultipartFile image) throws IOException {
        menuItem.setPictureURI(imageService.saveImage(image.getBytes(), menuItem.getId().toString()));
        return this.persistMenuItem(menuItem);
    }

    @Override
    public boolean deleteMenuItem(MenuItem menuItem) {
        this.menuItemRepository.delete(this.persistMenuItemDTOMapper.toDTO(menuItem));
        return true;
    }
}
