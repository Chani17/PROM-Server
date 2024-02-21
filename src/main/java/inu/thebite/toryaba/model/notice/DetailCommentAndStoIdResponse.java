package inu.thebite.toryaba.model.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class DetailCommentAndStoIdResponse {

    private String comment;

    private Set<Long> stoId;

    public DetailCommentAndStoIdResponse(String comment, Set<Long> stoId) {
        this.comment = comment;
        this.stoId = stoId;
    }

}
