package nl.hu.todds_backend.presentation;

import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import nl.hu.todds_backend.application.QRCodeService;
import nl.hu.todds_backend.presentation.dto.qr.QRCodeDTO;
import nl.hu.todds_backend.presentation.dto.qr.TableNumberDTO;
import nl.hu.todds_backend.presentation.dto.qr.UUIDDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/qr")
public class QRCodeController {
    private final QRCodeService qrCodeService;

    @PostMapping("/{tableNumber}")
    public UUIDDTO generateNewQRCode(@PathVariable int tableNumber) {
        return new UUIDDTO(this.qrCodeService.newQRCode(tableNumber).getUUID());
    }

    @GetMapping()
    public List<QRCodeDTO> getAll() {
        return this.qrCodeService.getAll().stream().map(qrCode -> new QRCodeDTO(qrCode.getTableNumber(), qrCode.getUUID())).collect(Collectors.toList());
    }

    @GetMapping("/tableNumber/{tableNumber}")
    public List<UUIDDTO> getUUID(@PathVariable int tableNumber) {
        return this.qrCodeService.getQRCodeByTable(tableNumber).stream().map(qrCode -> new UUIDDTO(qrCode.getUUID())).collect(Collectors.toList());
    }

    @GetMapping("/uuid/{UUID}")
    public TableNumberDTO getTableNumber(@PathVariable String UUID) {
        return new TableNumberDTO(this.qrCodeService.getQRCodeByUUID(UUID).getTableNumber());
    }

    @GetMapping(value = "/{UUID}", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQRCode(@PathVariable String UUID) throws IOException, WriterException {
        return this.qrCodeService.generateQRCode(UUID);
    }
    
    @DeleteMapping(value = "/{UUID}")
    public void deleteQRCode(@PathVariable String UUID) {
        this.qrCodeService.deleteQRCode(UUID);
    }
}
