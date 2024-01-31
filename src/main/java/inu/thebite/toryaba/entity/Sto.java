package inu.thebite.toryaba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_sto")
public class Sto extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sto_seq", length = 11, nullable = false)
    private Long id;

    // 템플릿 번호
    @Column(name = "tmpl_seq", length = 11)
    private int templateNum;

    // 단기 목표 상태
    @Column(name = "sto_status", nullable = false, length = 11)
    private String status;

    // 단기 목표 이름
    @Column(name = "sto_name", nullable = false, length = 200)
    private String name;

    // 단기 목표 내용
    @Column(name = "sto_contents", length = 200)
    private String contents;

    // 단기 목표 시도 수(array)
    @Column(name = "sto_trial_cnt", nullable = false, length = 11)
    private int count;

    // 단기 목표 도달 기준
    @Column(name = "sto_arr_std_cnt", nullable = false, length = 11)
    private int goal;

    // 단기 목표 준거도달기준 -> 90%로 고정
    @Column(name = "sto_arr_std_pst", nullable = false, length = 11)
    private int goalPercent;

    // 단기 목표 도달 여부
    @Column(name = "sto_arr_yn", nullable = false, length = 1)
    private String achievementOrNot;

    // 촉구 타입
    @Column(name = "sto_urge_tp_cd", length = 3)
    private String urgeType;

    // 촉구 내용
    @Column(name = "sto_urge_contents", length = 500)
    private String urgeContent;

    // 강화 내용
    @Column(name = "sto_enforce_contents", length = 500)
    private String enforceContent;

    // 메모 내용
    @Column(name = "sto_memo_contents", length = 500)
    private String memo;

    // 회차 정보
    @Column(name = "sto_round", length = 10)
    private int round;

    // 단기 목표 도달 일자
    @Column(name = "sto_arr_dt")
    private String hitGoalDate;

    // 등록 일자
    @Column(name = "sto_reg_dt", nullable = false)
    private String registerDate;

    // 삭제 여부
    @Column(name = "del_yn", nullable = false, length = 1)
    private String delYN;

    // 사진
    @Column(name = "sto_image_list")
    @ElementCollection
    private List<String> imageList = new ArrayList<>();

    // 포인트
    @JsonManagedReference
    @Column(name = "sto_point_list")
    @OneToMany(mappedBy = "sto", cascade = CascadeType.ALL)
    private List<Point> pointList = new ArrayList<>();

    // 스트레스 상태
    @Column(name = "sto_stress_status")
    private String stressStatus;

    // 집중도
    @Column(name = "sto_concentration")
    private String concentration;

    // 돌발 행동 리스트
    @Column(name = "sto_lc_list")
    @ElementCollection
    private List<String> looseCannonList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lto_seq")
    private Lto lto;

    public static Sto createSto(int templateNum, String name, String content, int count, int goal, String urgeType, String urgeContent, String enforceContent, String memo, Lto lto) {
        Sto sto  = new Sto();
        sto.templateNum = templateNum;
        sto.status = "READY";
        sto.name = name;
        sto.contents = content;
        sto.count = count;
        sto.goal = goal;
        sto.goalPercent = 90;
        sto.achievementOrNot = "N";
        sto.urgeType = urgeType;
        sto.urgeContent = urgeContent;
        sto.enforceContent = enforceContent;
        sto.memo = memo;
        sto.round = 1;
        sto.hitGoalDate = "NOT YET";
        sto.registerDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        sto.delYN = "N";
        sto.lto = lto;
        return sto;
    }

    // update STO status when sto status is "stop" or "in progress"
    public void updateStoStatus(String status) {
        this.status = status;
    }

    // update STO status when sto status is "hit"
    public void updateStoHitStatus(String status) {
        this.status = status;
        this.hitGoalDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        this.achievementOrNot = "Y";
    }

    // update STO contents
    public void updateSto(String name, String contents, int count,int goal, String urgeType, String urgeContent, String enforceContent, String memo) {
        this.name = name;
        this.contents = contents;
        this.count = count;
        this.goal = goal;
        this.urgeType = urgeType;
        this.urgeContent = urgeContent;
        this.enforceContent = enforceContent;
        this.memo = memo;
    }

    // update STO image list
    public void updateImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    // update STO round
    public void updateStoRound(int round) {
        this.round = round + 1;
    }

    // update stress status
    public void updateStressStatus(String status) {
        this.stressStatus = status;
    }

    // update concentration
    public void updateConcentration(String status) {
        this.concentration = status;
    }

    // update loose cannon
    public void updateLooseCannon(String action) {
        this.looseCannonList.add(action);
    }
}