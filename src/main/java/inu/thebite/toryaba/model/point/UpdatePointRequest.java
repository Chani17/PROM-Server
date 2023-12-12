package inu.thebite.toryaba.model.point;


import lombok.Data;

import java.util.List;

@Data
public class UpdatePointRequest {

    private List<String> points;

    private String registrant;
}
