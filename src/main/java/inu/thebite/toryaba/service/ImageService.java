package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Category;
import inu.thebite.toryaba.entity.Image;
import inu.thebite.toryaba.model.image.AddImageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ImageService {
    Image addImage(Long categoryId, AddImageRequest addImageRequest);

    void deleteImage(Long categoryId, String imageName);

    String uploadImage(MultipartFile image, Category category);

    String setImageName(Category category);

    String setName(Category category);

    List<Image> getImageList();
}
