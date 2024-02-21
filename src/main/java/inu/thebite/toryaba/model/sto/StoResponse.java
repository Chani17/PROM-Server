package inu.thebite.toryaba.model.sto;

import inu.thebite.toryaba.entity.Point;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class StoResponse {

    private Long stoId;

    private int templateNum;

    private String status;

    private String name;

    private String contents;

    private int count;

    private int goal;

    private String goalType;

    private int goalAmount;

    private String achievementOrNot;

    private String urgeContent;

    private String enforceContent;

    private String memo;

    private int round;

    private String hitGoalDate;

    private String registerDate;

    private String delYN;

    private List<String> imageList;

    private List<Point> pointList;

    private String stressStatus;

    private String concentration;

    private String significant;

    private List<String> looseCannonList;

    private Long ltoId;


    public StoResponse(Long stoId, int templateNum, String status, String name, String contents, int count, int goal, String goalType, int goalAmount,
                       String urgeContent, String enforceContent, String memo, int round, String hitGoalDate, String registerDate, String delYN,
                       String stressStatus, String concentration, String significant, List<String> looseCannonList, Long ltoId) {
        this.stoId = stoId;
        this.templateNum = templateNum;
        this.status = status;
        this.name = name;
        this.contents = contents;
        this.count = count;
        this.goal = goal;
        this.goalType = goalType;
        this.goalAmount = goalAmount;
        this.urgeContent = urgeContent;
        this.enforceContent = enforceContent;
        this.memo = memo;
        this.round = round;
        this.hitGoalDate = hitGoalDate;
        this.registerDate = registerDate;
        this.delYN = delYN;
        this.stressStatus = stressStatus;
        this.concentration = concentration;
        this.significant = significant;
        this.looseCannonList = looseCannonList;
        this.ltoId = ltoId;
    }



    public static StoResponse stoResponse(Long id, int templateNum, String status, String name, String contents, int count, int goal, String goalType, int goalAmount,
                                          String urgeContent, String enforceContent, String memo, int round, String hitGoalDate,
                                          String registerDate, String delYN, List<String> imageList, List<Point> pointList,
                                          String stressStatus, String concentration, String significant, List<String> looseCannonList, Long LtoId) {
        StoResponse response = new StoResponse();
        response.stoId = id;
        response.templateNum = templateNum;
        response.status = status;
        response.name = name;
        response.contents = contents;
        response.count = count;
        response.goal = goal;
        response.goalType = goalType;
        response.goalAmount = goalAmount;
        response.urgeContent = urgeContent;
        response.enforceContent = enforceContent;
        response.memo = memo;
        response.round = round;
        response.hitGoalDate = hitGoalDate;
        response.registerDate = registerDate;
        response.delYN = delYN;
        response.imageList = imageList;
        response.pointList = pointList;
        response.stressStatus = stressStatus;
        response.concentration = concentration;
        response.significant = significant;
        response.looseCannonList = looseCannonList;
        response.ltoId = LtoId;
        return response;
    }


}
