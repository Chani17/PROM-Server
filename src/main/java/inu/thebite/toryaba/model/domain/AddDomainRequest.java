package inu.thebite.toryaba.model.domain;


import lombok.Data;

@Data
public class AddDomainRequest {

    private String name;

    private String type;

    private String contents;

}
