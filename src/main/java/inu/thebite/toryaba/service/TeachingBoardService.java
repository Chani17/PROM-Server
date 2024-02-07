package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.teachingBoard.GraphByAreaResponse;

import java.util.List;

public interface TeachingBoardService {
    List<GraphByAreaResponse> getGraphByArea(Long studentId);
}
