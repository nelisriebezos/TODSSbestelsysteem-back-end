package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.CategoryRepository;
import nl.hu.todds_backend.data.dto.mapper.PersistCategoryDTOMapper;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.domain.MenuItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final PersistCategoryDTOMapper persistCategoryDTOMapper;
    private final CategoryRepository categoryRepository;
    private final MenuItemService menuItemService;
    private final ImageService imageService;

    @Override
    public List<Category> getAllUpperCategories() {
        return this.persistCategoryDTOMapper.fromMultipleDTO(this.categoryRepository.findAllUpperCategories());
    }

    @Override
    public List<Category> getAllCategories() {
        return this.persistCategoryDTOMapper.fromMultipleDTO(this.categoryRepository.findAll());
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.persistCategoryDTOMapper.fromDTO(this.categoryRepository.getById(id));
    }

    @Override
    public Category persistCategory(Category category) {
        return this.persistCategoryDTOMapper.fromDTO(this.categoryRepository.save(this.persistCategoryDTOMapper.toDTO(category)));
    }

    @Override
    public Category persistCategoryImage(Category category, MultipartFile image) throws IOException {
        category.setPictureURI(imageService.saveImage(image.getBytes(), category.getId().toString()));
        return this.persistCategory(category);
    }

    @Override
    public List<Category> removeMenuItemFromCategory(MenuItem menuItem) {
        List<Category> categoryList = getAllCategories();
        categoryList.forEach(category -> {category.removeMenuItem(menuItem); this.persistCategory(category);});
        return categoryList;
    }

    @Override
    public Category addSubCategory(Long upperId, Long lowerId) {
        Category upper = this.getCategoryById(upperId);
        Category lower = this.getCategoryById(lowerId);
        lower.setUpperCategoryId(upperId);
        this.persistCategory(upper.addSubCategory(lower));
        this.persistCategory(lower);
        return upper;
    }

    @Override
    public Category addMenuItem(Long categoryId, Long menuItemId) {
        Category category = this.getCategoryById(categoryId).addMenuItem(this.menuItemService.getMenuItemById(menuItemId));
        this.persistCategory(category);
        return category;
    }

    @Override
    public boolean deleteCategory(Category category) {
        this.categoryRepository.removeSubCategoryIds(category.getId());
        this.categoryRepository.delete(this.persistCategoryDTOMapper.toDTO(category));
        return true;
    }
}
