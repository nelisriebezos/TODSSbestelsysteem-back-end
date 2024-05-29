package nl.hu.todds_backend.data;

import nl.hu.todds_backend.domain.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QRCodeRepository extends JpaRepository<QRCode, String> {
    public List<QRCode> findAllByTableNumber(int tableNumber);
}
