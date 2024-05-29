package nl.hu.todds_backend.security.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PersistUserDTO, String> {

}
