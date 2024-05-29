package nl.hu.todds_backend.domain;

import lombok.*;
import nl.hu.todds_backend.domain.excpetion.DoubleCategoryException;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    private Long id;
    private String name;
    private String description;
    @ToString.Exclude
    @Builder.Default
    private List<MenuItem> menuItems = new ArrayList<>();
    @ToString.Exclude
    @Builder.Default
    private List<Category> subCategories = new ArrayList<>();
    private Long upperCategoryId;
    private String pictureURI;

    public Category addSubCategory(Category category) {
        if (category != this && !this.subCategories.contains(category)) this.subCategories.add(category);
        else throw new DoubleCategoryException("Category already has this as a sub category");
        return this;
    }

    public Category addMenuItem(MenuItem menuItem) {
        if (!this.menuItems.contains(menuItem)) this.menuItems.add(menuItem);
        return this;
    }

    public Category removeMenuItem(MenuItem menuItem) {
        this.menuItems.remove(menuItem);
        return this;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;

        return Objects.equals(id, category.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
