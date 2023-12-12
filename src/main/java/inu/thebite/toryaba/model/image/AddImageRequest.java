package inu.thebite.toryaba.model.image;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddImageRequest {

    private MultipartFile image;
}
