package inu.thebite.toryaba.model.lto;

import lombok.Data;

@Data
public class LtoRequest {

    // Lto 이름
    private String name;

    // Lto 내용
    private String contents;

    // 선택한 게임
    private String game;
}
