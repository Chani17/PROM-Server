package inu.thebite.toryaba.model.teachingBoard;

import lombok.Data;

import java.util.List;

@Data
public class GraphByAreaResponse {

    private String domainName;

    private List<Integer> count;

    public static GraphByAreaResponse response(String domainName, List<Integer> count) {
        GraphByAreaResponse graphByAreaResponse = new GraphByAreaResponse();
        graphByAreaResponse.domainName = domainName;
        graphByAreaResponse.count = count;
        return graphByAreaResponse;
    }
}
