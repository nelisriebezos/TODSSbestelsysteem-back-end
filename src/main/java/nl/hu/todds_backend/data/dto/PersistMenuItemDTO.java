package nl.hu.todds_backend.data.dto;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Menu_Item")
public class PersistMenuItemDTO extends DTO {
    @GeneratedValue
    @Id
    private Long id;
    private String name;
    private double price;
    private String features;
    @OneToOne
    private PersistLocationDTO location;
    private String pictureURI;
    private boolean published;

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersistMenuItemDTO that = (PersistMenuItemDTO) o;

        return Objects.equals(id, that.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return 1830887427;
    }
}
