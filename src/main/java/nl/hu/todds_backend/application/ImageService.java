package nl.hu.todds_backend.application;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Transactional
@Service
@AllArgsConstructor
public class ImageService implements IImageService {

    @Override
    public String saveImage(byte[] image, String imageName) throws IOException {
        Path newImage = Paths.get(new File(ImageService.class.getResource("/").getFile()).toPath() + "/images/" + imageName + ".png");
        Files.createDirectories(newImage.getParent());
        Files.write(newImage, image);
        return newImage.toString();
    }

    @Override
    public byte[] getImage(String imageUri) throws IOException {
        File file = new File(imageUri);
        return Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));
    }
}
