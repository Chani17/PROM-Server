package inu.thebite.toryaba.service.serviceImpl;

import com.google.cloud.storage.*;
import inu.thebite.toryaba.entity.Category;
import inu.thebite.toryaba.entity.Image;
import inu.thebite.toryaba.model.image.AddImageRequest;
import inu.thebite.toryaba.repository.CategoryRepository;
import inu.thebite.toryaba.repository.ImageRepository;
import inu.thebite.toryaba.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Transactional
    @Override
    public Image addImage(Long categoryId, AddImageRequest addImageRequest) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("해당 카테고리가 존재하지 않습니다."));

        String imageName = setImageName(category);
        String imageUrl = uploadImage(addImageRequest.getImage(), category);
        Image image = Image.createImage(imageUrl, imageName, category);
        imageRepository.save(image);
        return image;
    }

    @Override
    public List<Image> getImageList() {
        List<Image> imageList = imageRepository.findAll();
        return imageList;
    }

    @Transactional
    @Override
    public void deleteImage(Long categoryId, String imageName) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("해당 카테고리가 존재하지 않습니다."));

        Storage storage = StorageOptions.getDefaultInstance().getService();

        if(imageRepository.findByName(imageName).isPresent()) {

            // delete image to gcs
            Blob image = storage.get(bucketName, category.getName() + "/" + imageName);
            if (image == null) {
                System.out.println("The image "+ imageName + " wasn't found in " + category.getName());
            }

            // Optional: set a generation-match precondition to avoid potential race
            // conditions and data corruptions. The request to upload returns a 412 error if
            // the object's generation number does not match your precondition.
            Storage.BlobSourceOption precondition = Storage.BlobSourceOption.generationMatch(image.getGeneration());
            storage.delete(bucketName, category.getName() + "/" + imageName, precondition);

            // delete image to DB
            imageRepository.deleteByName(imageName);

        } else {
            throw new IllegalStateException("해당하는 이미지가 존재하지 않습니다.");
        }
    }


    @Override
    public String uploadImage(MultipartFile image, Category category) {

        Storage storage = StorageOptions.getDefaultInstance().getService();
        String imageName = setName(category);
        String contentType = image.getContentType();

        // save image
        try {
            BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, imageName).setContentType(contentType).build();
            Blob uploadImage = storage.createFrom(blobInfo, image.getInputStream());
            String imageUrl = uploadImage.getMediaLink();
            return imageUrl;

        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    public String setImageName(Category category) {
        List<Image> result = imageRepository.findAllByCategoryId(category.getId());
        String number = Integer.toString(result.size() + 1);

        String name = category.getName() + "_" + number;
        return name;
    }

    @Override
    public String setName(Category category) {
        List<Image> result = imageRepository.findAllByCategoryId(category.getId());
        String number = Integer.toString(result.size() + 1);

        String name = category.getName() + "/" + category.getName() + "_" + number;
        return name;
    }
}
