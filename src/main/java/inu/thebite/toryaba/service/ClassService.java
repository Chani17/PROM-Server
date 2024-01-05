package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Class;
import inu.thebite.toryaba.model.childClass.ClassRequest;

import java.util.List;

public interface ClassService {
    Class addClass(String userId, Long centerId, ClassRequest classRequest);

    boolean deleteClass(String userId, Long classId);

    List<Class> getClassListByCenter(String userId, Long centerId);

//    List<Class> getClassList(String userId);

    Class updateClass(String userId, Long classId, ClassRequest classRequest);
}
