package nl.hu.todds_backend.application;

import nl.hu.todds_backend.data.CategoryRepository;
import nl.hu.todds_backend.data.dto.PersistCategoryDTO;
import nl.hu.todds_backend.data.dto.PersistMenuItemDTO;
import nl.hu.todds_backend.data.dto.mapper.PersistCategoryDTOMapper;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.domain.MenuItem;
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
class CategoryServiceTest {

    @Mock
    PersistCategoryDTOMapper persistCategoryDTOMapper;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    MenuItemService menuItemService;

    @InjectMocks
    CategoryService categoryService;

    Category category;
    Category subCategory;
    List<Category> categories;

    PersistCategoryDTO persistCategoryDTO;
    PersistCategoryDTO subCategoryDTO;
    List<PersistCategoryDTO> persistCategoryDTOS;

    MenuItem menuItem;
    PersistMenuItemDTO persistMenuItemDTO;

    @BeforeEach
    public void init() {
        category = new Category();
        category.setId(1L);
        category.setName("category");
        category.setDescription("test");

        subCategory = new Category();
        subCategory.setId(2L);
        subCategory.setName("category");
        subCategory.setDescription("test");

        categories = List.of(category);

        menuItem = new MenuItem();
        menuItem.setId(3L);
        menuItem.setName("menu item");
        menuItem.setPrice(20.0);

        persistCategoryDTO = new PersistCategoryDTO();
        persistCategoryDTO.setId(1L);
        persistCategoryDTO.setName("category");
        persistCategoryDTO.setDescription("test");

        subCategoryDTO = new PersistCategoryDTO();
        subCategoryDTO.setId(2L);
        subCategoryDTO.setName("category");
        subCategory.setDescription("test");

        persistMenuItemDTO = new PersistMenuItemDTO();
        persistMenuItemDTO.setId(3L);
        persistMenuItemDTO.setName("menu item");
        persistMenuItemDTO.setPrice(20.0);

        persistCategoryDTOS = List.of(persistCategoryDTO);
    }

    @Test
    @DisplayName("get all categories")
    void getAllCategories() {
        when(categoryRepository.findAllUpperCategories()).thenReturn(persistCategoryDTOS);
        when(persistCategoryDTOMapper.fromMultipleDTO(persistCategoryDTOS)).thenReturn(categories);

        assertEquals(categories, categoryService.getAllUpperCategories());

        verify(categoryRepository).findAllUpperCategories();
        verify(persistCategoryDTOMapper).fromMultipleDTO(persistCategoryDTOS);
    }

    @Test
    @DisplayName("get category by id")
    void getCategoryById() {
        when(categoryRepository.getById(category.getId())).thenReturn(persistCategoryDTO);
        when(persistCategoryDTOMapper.fromDTO(persistCategoryDTO)).thenReturn(category);

        assertEquals(category, categoryService.getCategoryById(category.getId()));

        verify(categoryRepository).getById(category.getId());
        verify(persistCategoryDTOMapper).fromDTO(persistCategoryDTO);
    }

    @Test
    @DisplayName("persist category")
    void persistCategory() {
        when(persistCategoryDTOMapper.toDTO(category)).thenReturn(persistCategoryDTO);
        when(categoryRepository.save(persistCategoryDTO)).thenReturn(persistCategoryDTO);
        when(persistCategoryDTOMapper.fromDTO(persistCategoryDTO)).thenReturn(category);

        assertEquals(category, categoryService.persistCategory(category));

        verify(persistCategoryDTOMapper).toDTO(category);
        verify(categoryRepository).save(persistCategoryDTO);
        verify(persistCategoryDTOMapper).fromDTO(persistCategoryDTO);
    }

    @Test
    @DisplayName("persist sub category")
    void persistSubCategory() {
        when(categoryRepository.getById(category.getId())).thenReturn(persistCategoryDTO);
        when(categoryRepository.getById(subCategory.getId())).thenReturn(subCategoryDTO);

        when(persistCategoryDTOMapper.toDTO(subCategory)).thenReturn(subCategoryDTO);
        when(categoryRepository.save(subCategoryDTO)).thenReturn(subCategoryDTO);
        when(persistCategoryDTOMapper.fromDTO(subCategoryDTO)).thenReturn(subCategory);

        persistCategoryDTO.setSubCategories(List.of(subCategoryDTO));

        when(persistCategoryDTOMapper.toDTO(category)).thenReturn(persistCategoryDTO);
        when(categoryRepository.save(persistCategoryDTO)).thenReturn(persistCategoryDTO);
        when(persistCategoryDTOMapper.fromDTO(persistCategoryDTO)).thenReturn(category);

        assertEquals(1, categoryService.addSubCategory(category.getId(), subCategory.getId()).getSubCategories().size());

        verify(categoryRepository).getById(category.getId());
        verify(categoryRepository).getById(subCategory.getId());

        verify(persistCategoryDTOMapper).toDTO(subCategory);
        verify(categoryRepository).save(subCategoryDTO);
        verify(persistCategoryDTOMapper, times(2)).fromDTO(subCategoryDTO);

        verify(persistCategoryDTOMapper).toDTO(category);
        verify(categoryRepository).save(persistCategoryDTO);
        verify(persistCategoryDTOMapper, times(2)).fromDTO(persistCategoryDTO);
    }

    @Test
    @DisplayName("add menu item")
    void addMenuItem() {
        when(categoryRepository.getById(category.getId())).thenReturn(persistCategoryDTO);
        when(persistCategoryDTOMapper.fromDTO(persistCategoryDTO)).thenReturn(category);
        when(persistCategoryDTOMapper.toDTO(category)).thenReturn(persistCategoryDTO);
        when(categoryRepository.save(persistCategoryDTO)).thenReturn(persistCategoryDTO);

        when(menuItemService.getMenuItemById(menuItem.getId())).thenReturn(menuItem);

        assertEquals(1, categoryService.addMenuItem(category.getId(), menuItem.getId()).getMenuItems().size());

        verify(categoryRepository).getById(category.getId());
        verify(persistCategoryDTOMapper, times(2)).fromDTO(persistCategoryDTO);
        verify(persistCategoryDTOMapper).toDTO(category);
        verify(categoryRepository).save(persistCategoryDTO);
    }
}
