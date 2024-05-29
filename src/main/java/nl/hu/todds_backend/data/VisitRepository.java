package nl.hu.todds_backend.data;

import nl.hu.todds_backend.data.dto.PersistVisitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<PersistVisitDTO, Long> {
    @Query(nativeQuery = true, value = "SELECT persist_visitdto_id FROM visit_orders vo WHERE vo.orders_id = :id")
    List<Long> getAllVisitIdWithOrderId(Long id);

    @Query(nativeQuery = true, value = "SELECT persist_visitdto_id FROM visit_orders vo WHERE vo.orders_id = :id")
    Long getVisitIdWithOrderId(Long id);
}
