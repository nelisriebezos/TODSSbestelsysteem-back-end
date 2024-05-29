package nl.hu.todds_backend.presentation.dto.qr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QRCodeDTO {
    private int tableNumber;
    private String UUID;
}
