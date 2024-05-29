package nl.hu.todds_backend.data;

import nl.hu.todds_backend.data.dto.PersistCategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<PersistCategoryDTO, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM category c WHERE c.upper_category_id IS NULL")
    List<PersistCategoryDTO> findAllUpperCategories();

    @Modifying
    @Query(nativeQuery = true,  value = "DELETE from category_sub_categories WHERE sub_categories_id = :id")
    void removeSubCategoryIds(Long id);
}
