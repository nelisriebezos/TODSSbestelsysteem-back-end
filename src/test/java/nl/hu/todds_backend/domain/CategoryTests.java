package nl.hu.todds_backend.domain;

import nl.hu.todds_backend.domain.excpetion.DoubleCategoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryTests {
    Category category1;
    Category category2;
    MenuItem menuItem1;

    @BeforeEach
    public void init() {
        category1 = new Category();
        category2 = new Category();
        menuItem1 = new MenuItem();

        category1.setId(1L);
        category1.setName("one");
        category1.setDescription("test");
        category2.setId(2L);
        category2.setName("two");
        category2.setDescription("test");
        menuItem1.setId(3L);
        menuItem1.setName("apple");
        menuItem1.setPrice(2.0);
    }

    @Test
    @DisplayName("add subcategory")
    public void addSubCategory() {
        assertEquals(1, category1.addSubCategory(category2).getSubCategories().size());
    }

    @Test
    @DisplayName("add already added subcategory")
    public void addAddedSubCategory() {
        category1.addSubCategory(category2);
        assertThrows(DoubleCategoryException.class, () -> category1.addSubCategory(category2));
    }

    @Test
    @DisplayName("add .this as subcategory")
    public void addThisSubCategory() {
        assertThrows(DoubleCategoryException.class, () -> category1.addSubCategory(category1));
    }

    @Test
    @DisplayName("add menuItem")
    public void addMenuItem() {
        assertEquals(1, category1.addMenuItem(menuItem1).getMenuItems().size());
    }

    @Test
    @DisplayName("add already added menuItem")
    public void addAddedMenuItem() {
        category1.addMenuItem(menuItem1);
        assertEquals(1, category1.addMenuItem(menuItem1).getMenuItems().size());
    }
}
