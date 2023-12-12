package inu.thebite.toryaba.model.sto;


import inu.thebite.toryaba.entity.Image;
import lombok.Data;

import java.util.List;

@Data
public class UpdateImageList {

    private List<String> imageList;
}
