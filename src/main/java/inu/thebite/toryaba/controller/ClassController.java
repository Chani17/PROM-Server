package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.entity.Class;
import inu.thebite.toryaba.model.childClass.ClassRequest;
import inu.thebite.toryaba.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    // add class
    @PostMapping("/{centerId}/classes")
    public Class addClass(@LoginMember User user, @PathVariable Long centerId, @RequestBody ClassRequest classRequest) {
        String userId = user.getUsername();
        Class newClass = classService.addClass(userId, centerId, classRequest);
        return newClass;
    }

    // update class
    @PatchMapping("/classes/{classId}")
    public Class updateClass(@LoginMember User user, @PathVariable Long classId, @RequestBody ClassRequest classRequest) {
        String userId = user.getUsername();
        Class newClass = classService.updateClass(userId, classId, classRequest);
        return newClass;
    }

    // get class list
    @GetMapping("/{centerId}/classes")
    public List<Class> getClassListByCenter(@LoginMember User user, @PathVariable Long centerId) {
        String userId = user.getUsername();
        List<Class> classList = classService.getClassListByCenter(userId, centerId);
        return classList;
    }

    @GetMapping("/classes")
    public List<Class> getClassList(@LoginMember User user) {
        String userId = user.getUsername();
        List<Class> classList = classService.getClassList(userId);
        return classList;
    }

    // delete class
    @DeleteMapping("/classes/{classId}")
    public ResponseEntity deleteClass(@LoginMember User user, @PathVariable Long classId) {
        String userId = user.getUsername();
        classService.deleteClass(userId, classId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
