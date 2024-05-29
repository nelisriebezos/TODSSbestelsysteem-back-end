package nl.hu.todds_backend.application;

import nl.hu.todds_backend.domain.MenuItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMenuItemService {
    public List<MenuItem> getAllMenuItems();
    List<MenuItem> getAllPublishedMenuItems();
    List<MenuItem> getAllUnPublishedMenuItems();
    public MenuItem getMenuItemById(Long id);
    MenuItem getMenuItemByName(String menuItemName);
    public MenuItem persistMenuItem(MenuItem menuItem);
    public MenuItem persistMenuItemImage(MenuItem menuItem, MultipartFile image) throws IOException;
    public boolean deleteMenuItem(MenuItem menuItem);
}
