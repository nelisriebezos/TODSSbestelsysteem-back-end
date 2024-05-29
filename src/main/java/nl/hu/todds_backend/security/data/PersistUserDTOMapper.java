package nl.hu.todds_backend.security.data;

import nl.hu.todds_backend.security.domain.User;
import nl.hu.todds_backend.utils.DTOMapper;
import nl.hu.todds_backend.utils.ExcludeFromJacocoGeneratedReport;
import org.springframework.stereotype.Component;

@Component
@ExcludeFromJacocoGeneratedReport
public class PersistUserDTOMapper implements DTOMapper<PersistUserDTO, User> {
    @Override
    public PersistUserDTO toDTO(User o) {
        return PersistUserDTO.builder().username(o.getUsername()).password(o.getPassword()).build();
    }

    @Override
    public User fromDTO(PersistUserDTO o) {
        return User.builder().username(o.getUsername()).password(o.getPassword()).build();
    }
}
