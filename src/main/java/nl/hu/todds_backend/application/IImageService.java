package nl.hu.todds_backend.application;

import java.io.IOException;

public interface IImageService {
    public String saveImage(byte[] image, String imageName) throws IOException;
    public byte[] getImage(String imageUri) throws IOException;
}
