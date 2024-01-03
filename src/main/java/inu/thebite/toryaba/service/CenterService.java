package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.model.center.CenterRequest;

import java.util.List;

public interface CenterService {
    Center addCenter(String userId, CenterRequest centerRequest);

    void deleteCenter(String userId, Long centerId);

    List<Center> getCenterList(String id);

    Center updateCenter(String userId, Long centerId, CenterRequest centerRequest);
}
