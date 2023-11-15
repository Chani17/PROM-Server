package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.entity.Class;
import inu.thebite.toryaba.model.childClass.ClassRequest;
import inu.thebite.toryaba.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    // add class
    @PostMapping("/{centerId}/classes")
    public Class addClass(@PathVariable Long centerId, @RequestBody ClassRequest classRequest) {
        Class newClass = classService.addClass(centerId, classRequest);
        return newClass;
    }

    // update class
    @PatchMapping("/classes/{classId}")
    public Class updateClass(@PathVariable Long classId, @RequestBody ClassRequest classRequest) {
        Class newClass = classService.updateClass(classId, classRequest);
        return newClass;
    }

    // get class list
    @GetMapping("/classes")
    public List<Class> getClassList() {
        List<Class> classList = classService.getClassList();
        return classList;
    }

    // delete class
    @DeleteMapping("/classes/{classId}")
    public ResponseEntity deleteClass(@PathVariable Long classId) {
        classService.deleteClass(classId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
