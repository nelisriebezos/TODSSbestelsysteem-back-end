package nl.hu.todds_backend.domain;

import lombok.*;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItem {
    private Long id;
    private String name;
    private double price;
    private String features;
    private Location location;
    private String pictureURI;
    private boolean published = false;

    public MenuItem publish() {
        this.published = true;
        return this;
    }

    public MenuItem hide() {
        this.published = false;
        return this;
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MenuItem menuItem = (MenuItem) o;

        return Objects.equals(id, menuItem.id);
    }

    @Override
    @ExcludeFromJacocoGeneratedReport
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
