package nl.hu.todds_backend.data;

import nl.hu.todds_backend.data.dto.PersistMenuItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<PersistMenuItemDTO, Long> {
    public List<PersistMenuItemDTO> getAllByPublishedIsTrue();
    public List<PersistMenuItemDTO> getAllByPublishedIsFalse();
    public PersistMenuItemDTO getByName(String name);
}
