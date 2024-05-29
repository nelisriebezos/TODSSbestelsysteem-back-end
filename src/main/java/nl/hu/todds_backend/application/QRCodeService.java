package nl.hu.todds_backend.application;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import nl.hu.todds_backend.data.QRCodeRepository;
import nl.hu.todds_backend.domain.QRCode;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
@AllArgsConstructor
public class QRCodeService implements IQRCodeService {
    private final QRCodeRepository repository;

    @Override
    public List<QRCode> getQRCodeByTable(int tableNumber) {
        return this.repository.findAllByTableNumber(tableNumber);
    }

    @Override
    public QRCode getQRCodeByUUID(String UUID) {
        return this.repository.getById(UUID);
    }

    public List<QRCode> getAll() {
        return this.repository.findAll();
    }

    @Override
    public QRCode newQRCode(int tableNumber) {
        QRCode code = new QRCode(UUID.randomUUID().toString(), tableNumber);
        this.persistQRCode(code);
        return code;
    }

    @Override
    public byte[] generateQRCode(String UUID) throws WriterException, IOException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode("localhost:3000/qr?" + UUID, BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", byteArrayOutputStream);
        return Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());
    }

    @Override
    public void persistQRCode(QRCode code) {
        this.repository.save(code);
    }

    @Override
    public void deleteQRCode(String UUID) {
        this.repository.deleteById(UUID);
    }
}
