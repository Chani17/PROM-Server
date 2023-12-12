package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.lto.LtoGraphResponse;
import inu.thebite.toryaba.model.point.AddPointRequest;
import inu.thebite.toryaba.model.point.DeletePointRequest;
import inu.thebite.toryaba.model.point.UpdatePointRequest;

import java.util.List;

public interface PointService {
    void addPoint(Long stoId, AddPointRequest addPointRequest);

    List<String> getPointList(Long stoId);

    void updatePoint(Long stoId, UpdatePointRequest updatePointRequest);

    LtoGraphResponse getGraphValue(Long stoId);

    void deletePoint(Long stoId);

    void insertValue(Long stoId, Float plusRate, Float minusRate);
}
