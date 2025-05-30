package ptit.example.btlwebbook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImageFile {
    String uploadImage(MultipartFile multipartFile) throws IOException;

    String updateImage(MultipartFile multipartFile, String oldPublicId) throws IOException;

    void deleteImage(String oldPublicId) throws IOException;
}
