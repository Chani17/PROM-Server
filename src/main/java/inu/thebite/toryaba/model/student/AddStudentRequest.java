package inu.thebite.toryaba.model.student;


import lombok.Data;

@Data
public class AddStudentRequest {

    private String name;

    private String birth;

    private String etc;

    private String parentName;

    private String startDate;

}
