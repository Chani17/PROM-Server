package inu.thebite.toryaba.model.notice;

import lombok.Data;

import java.util.List;

@Data
public class ConvertPdfRequest {

    private String ltoName;

    private String ltoContent;

    private List<String> dates;

    private List<String> stoNames;
}
