package inu.thebite.toryaba.model.lto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LtoResponse {

    private Long ltoId;

    private int templateNum;

    private String status;

    private String name;

    private String contents;

    private String achieveDate;

    private String registerDate;

    private String delYN;

    private Long domainId;

    private Long studentId;

    public LtoResponse(Long ltoId, int templateNum, String status, String name, String contents, String achieveDate, String registerDate, String delYN, Long domainId, Long studentId) {
        this.ltoId = ltoId;
        this.templateNum = templateNum;
        this.status = status;
        this.name = name;
        this.contents = contents;
        this.achieveDate = achieveDate;
        this.registerDate = registerDate;
        this.delYN = delYN;
        this.domainId = domainId;
        this.studentId = studentId;
    }

    public static LtoResponse createLtoResponse(Long id, int templateNum, String status, String name,
                                                String contents, String achieveDate,
                                                String registerDate, String delYN, Long domainId, Long studentId) {
        LtoResponse response = new LtoResponse();
        response.ltoId = id;
        response.templateNum = templateNum;
        response.status = status;
        response.name = name;
        response.contents = contents;
        response.achieveDate = achieveDate;
        response.registerDate = registerDate;
        response.delYN = delYN;
        response.domainId = domainId;
        response.studentId = studentId;
        return response;
    }

}
