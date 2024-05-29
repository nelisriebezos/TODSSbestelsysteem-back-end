package nl.hu.todds_backend.application;

import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.domain.MenuItem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    public List<Category> getAllUpperCategories();

    List<Category> getAllCategories();

    public Category getCategoryById(Long id);
    public Category persistCategory(Category category);
    public Category persistCategoryImage(Category category, MultipartFile image) throws IOException;
    public List<Category> removeMenuItemFromCategory(MenuItem menuItem);
    public Category addSubCategory(Long upperId, Long lowerId);
    public Category addMenuItem(Long categoryId, Long menuItemId);
    public boolean deleteCategory(Category category);
}
