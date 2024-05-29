package nl.hu.todds_backend.data.dto;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Category")
public class PersistCategoryDTO extends DTO {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    private List<PersistMenuItemDTO> menuItems = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<PersistCategoryDTO> subCategories = new ArrayList<>();
    private Long upperCategoryId;
    private String pictureURI;

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersistCategoryDTO that = (PersistCategoryDTO) o;

        return Objects.equals(id, that.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return 1562923598;
    }
}
