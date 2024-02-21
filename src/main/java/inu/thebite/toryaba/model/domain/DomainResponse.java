package inu.thebite.toryaba.model.domain;

import lombok.Data;

@Data
public class DomainResponse {

    private Long id;

    private int templateNum;

    private String name;

    private String registerDate;

    private Long centerId;

    public static DomainResponse response(Long id, int templateNum, String name, String registerDate, Long centerId) {
        DomainResponse domainResponse = new DomainResponse();
        domainResponse.id = id;
        domainResponse.templateNum = templateNum;
        domainResponse.name = name;
        domainResponse.registerDate = registerDate;
        domainResponse.centerId = centerId;
        return domainResponse;
    }
}
