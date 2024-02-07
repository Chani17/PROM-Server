package inu.thebite.toryaba.controller;

import inu.thebite.toryaba.model.teachingBoard.GraphByAreaResponse;
import inu.thebite.toryaba.service.TeachingBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/teachingboard")
public class TeachingBoardController {

    private final TeachingBoardService teachingBoardService;

    // graph by area
    @GetMapping("/{studentId}")
    public List<GraphByAreaResponse> getGraphByArea(@PathVariable Long studentId) {
        return teachingBoardService.getGraphByArea(studentId);
    }
}
