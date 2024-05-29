package nl.hu.todds_backend.data;

import nl.hu.todds_backend.data.dto.PersistOrderNotificationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderNotificationRepository extends JpaRepository<PersistOrderNotificationDTO, Long> {
    List<PersistOrderNotificationDTO> findAllByLocationId(Long id);
}
