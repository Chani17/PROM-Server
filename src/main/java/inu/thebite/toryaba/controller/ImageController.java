//package inu.thebite.toryaba.controller;
//
//
//import inu.thebite.toryaba.entity.Image;
//import inu.thebite.toryaba.model.image.AddImageRequest;
//import inu.thebite.toryaba.service.ImageService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequiredArgsConstructor
//public class ImageController {
//
//    private final ImageService imageService;
//
//    // add image (only one)
//    @PostMapping("/{categoryId}/images")
//    public ResponseEntity addImage(@PathVariable Long categoryId, AddImageRequest addImageRequest) {
//        Image image = imageService.addImage(categoryId, addImageRequest);
//        return ResponseEntity.ok(image);
//    }
//
//    // get all image list
//    @GetMapping("/images")
//    public List<Image> getImageList() {
//        List<Image> imageList = imageService.getImageList();
//        return imageList;
//    }
//
//    // delete image (only one)
//    @DeleteMapping("/{categoryId}/images/{imageName}")
//    public ResponseEntity deleteImage(@PathVariable Long categoryId, @PathVariable String imageName) {
//        imageService.deleteImage(categoryId, imageName);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//}
