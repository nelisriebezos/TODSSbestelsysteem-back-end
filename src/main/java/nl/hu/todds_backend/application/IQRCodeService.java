package nl.hu.todds_backend.application;

import com.google.zxing.WriterException;
import nl.hu.todds_backend.domain.QRCode;

import java.io.IOException;
import java.util.List;

public interface IQRCodeService {
    public List<QRCode> getQRCodeByTable(int tableNumber);
    public QRCode getQRCodeByUUID(String UUID);
    public QRCode newQRCode(int tableNumber);
    public byte[] generateQRCode(String UUID) throws WriterException, IOException;
    public void persistQRCode(QRCode code);
    public void deleteQRCode(String UUID);
}
