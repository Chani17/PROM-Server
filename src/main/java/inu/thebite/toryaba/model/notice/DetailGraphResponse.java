package inu.thebite.toryaba.model.notice;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class DetailGraphResponse {

    private List<Float> results;

    private List<String> dates;

    private Long ltoId;

    private Long stoId;

    private Long noticeId;

    public DetailGraphResponse(Long stoId, Long noticeId) {
        this.stoId = stoId;
        this.noticeId = noticeId;
    }

    public static DetailGraphResponse response(List<Float> results, List<String> dates, Long ltoId, Long stoId, Long noticeId) {
        DetailGraphResponse response = new DetailGraphResponse();
        response.results = results;
        response.dates = dates;
        response.ltoId = ltoId;
        response.stoId = stoId;
        response.noticeId = noticeId;
        return response;
    }
}