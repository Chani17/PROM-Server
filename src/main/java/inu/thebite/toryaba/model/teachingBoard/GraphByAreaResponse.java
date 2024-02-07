package inu.thebite.toryaba.model.teachingBoard;

import lombok.Data;

import java.util.List;

@Data
public class GraphByAreaResponse {

    private String domainName;

    private List<Integer> count;
}
