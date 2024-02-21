package inu.thebite.toryaba.model.notice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;


@Data
@RequiredArgsConstructor
public class DetailResponse {

    private Long id;

    private String comment;

    private Long ltoId;

    private Set<Long> stoId;

    private Long noticeId;

    public DetailResponse(Long id, String comment, Long ltoId, Set<Long> stoId, Long noticeId) {
        this.id = id;
        this.comment = comment;
        this.ltoId = ltoId;
        this.stoId = stoId;
        this.noticeId = noticeId;
    }

    public static DetailResponse response(Long id, String comment, Long ltoId, Set<Long> stoId, Long noticeId) {
        DetailResponse response = new DetailResponse();
        response.id = id;
        response.comment = comment;
        response.ltoId = ltoId;
        response.stoId = stoId;
        response.noticeId = noticeId;
        return response;
    }
}