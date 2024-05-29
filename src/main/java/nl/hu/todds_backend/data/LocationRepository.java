package nl.hu.todds_backend.data;

import nl.hu.todds_backend.data.dto.PersistLocationDTO;
import nl.hu.todds_backend.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<PersistLocationDTO, Long> {
}
