package inu.thebite.toryaba.model.sto;


import lombok.Data;

@Data
public class UpdateStoRequest {

    private String name;

    private String contents;

    private int count;

    private int goal;

    private int goalAmount;

    private String urgeContent;

    private String enforceContent;

    private String memo;

}
