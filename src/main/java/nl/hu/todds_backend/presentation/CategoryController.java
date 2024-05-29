package nl.hu.todds_backend.presentation;

import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.CategoryService;
import nl.hu.todds_backend.domain.Category;
import nl.hu.todds_backend.presentation.dto.category.*;
import nl.hu.todds_backend.presentation.dto.mapper.category.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final AllCategoryDTOMapper allCategoryDTOMapper;
    private final CategoryDTOMapper categoryDTOMapper;
    private final PostCategoryDTOMapper postCategoryDTOMapper;
    private final NameAndIdCategoryDTOMapper nameAndIdCategoryDTOMapper;
    @GetMapping
    public List<AllCategoryDTO> getAllUpperCategories() {
        return allCategoryDTOMapper.toMultipleDTO(categoryService.getAllUpperCategories());
    }

    @GetMapping("/all")
    public List<NameAndIdCategoryDTO> getAllCategories() {
        return nameAndIdCategoryDTOMapper.toMultipleDTO(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable Long id) {
        return this.categoryDTOMapper.toDTO(this.categoryService.getCategoryById(id));
    }

    @PostMapping
    public CategoryDTO postCategory(@ModelAttribute PostCategoryDTO postCategoryDTO) throws IOException {
        CategoryDTO categoryDTO = categoryDTOMapper.toDTO(categoryService.persistCategory(postCategoryDTOMapper.fromDTO(postCategoryDTO)));
        if (postCategoryDTO.getImage() == null) return categoryDTO;
        return categoryDTOMapper.toDTO(categoryService.persistCategoryImage(categoryDTOMapper.fromDTO(categoryDTO), postCategoryDTO.getImage()));
    }

    @PatchMapping
    public CategoryDTO addSubCategory(@RequestParam Long upperId, @RequestParam Long lowerId) {
        return this.categoryDTOMapper.toDTO(this.categoryService.addSubCategory(upperId, lowerId));
    }

    @PatchMapping("/menuitem")
    public CategoryDTO addMenuItem(@RequestParam Long categoryId, @RequestParam Long menuItemId) {
        return this.categoryDTOMapper.toDTO(this.categoryService.addMenuItem(categoryId, menuItemId));
    }

    @PatchMapping("/update")
    public CategoryDTO patchCategory(@RequestBody PatchCategoryDTO patchCategoryDTO) {
        Category categoryToUpdate = this.categoryService.getCategoryById(patchCategoryDTO.getId());
        categoryToUpdate.setName(patchCategoryDTO.getName());
        categoryToUpdate.setDescription(patchCategoryDTO.getDescription());
        if (patchCategoryDTO.getUpperCategoryId() != null) categoryToUpdate.setUpperCategoryId(patchCategoryDTO.getUpperCategoryId());
        return this.categoryDTOMapper.toDTO(this.categoryService.persistCategory(categoryToUpdate));
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable Long id) {
        return this.categoryService.deleteCategory(this.categoryService.getCategoryById(id));
    }
}
