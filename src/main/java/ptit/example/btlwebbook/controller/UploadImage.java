package ptit.example.btlwebbook.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ptit.example.btlwebbook.dto.request.ImageDTO;
import ptit.example.btlwebbook.service.UploadImageFile;
import ptit.example.btlwebbook.utils.SlugUtils;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class UploadImage {
    private final UploadImageFile uploadImageFile;
    @PostMapping
    public ResponseEntity<ImageDTO> uploadImage(@RequestParam(name = "image") MultipartFile multipartFile) throws IOException {
           String imageUrl = uploadImageFile.uploadImage(multipartFile);
           String publicId = SlugUtils.getPublicId(imageUrl);
           ImageDTO imageDTO = ImageDTO.builder()
                   .imageUrl(imageUrl)
                   .publicId(publicId)
                   .build();
        return ResponseEntity.ok(imageDTO);
    }
}
