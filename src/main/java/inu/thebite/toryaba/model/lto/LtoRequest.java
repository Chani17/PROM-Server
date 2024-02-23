package inu.thebite.toryaba.model.lto;

import lombok.Data;

import java.util.List;

@Data
public class LtoRequest {

    // Lto 이름
    private String name;

    // Lto 내용
    private String contents;

    // 발달 타입
    private List<String> developType;

}
