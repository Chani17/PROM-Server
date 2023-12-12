package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Class;
import inu.thebite.toryaba.model.childClass.ClassRequest;

import java.util.List;

public interface ClassService {
    Class addClass(Long centerId, ClassRequest classRequest);

    void deleteClass(Long classId);

    List<Class> getClassListByCenter(Long centerId);

    List<Class> getClassList();

    Class updateClass(Long classId, ClassRequest classRequest);
}
