package nl.hu.todds_backend.security.data;

import lombok.*;
import nl.hu.todds_backend.utils.DTO;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "security_user")
public class PersistUserDTO extends DTO {
    @Id
    private String username;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PersistUserDTO that = (PersistUserDTO) o;

        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return 355345175;
    }
}
