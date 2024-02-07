package inu.thebite.toryaba.service;

import inu.thebite.toryaba.model.teachingBoard.GraphByAreaResponse;

public interface TeachingBoardService {
    GraphByAreaResponse getGraphByArea(Long studentId);
}
