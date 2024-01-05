package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Point;
import inu.thebite.toryaba.entity.Sto;
import inu.thebite.toryaba.model.lto.LtoGraphResponse;
import inu.thebite.toryaba.model.point.AddPointRequest;
import inu.thebite.toryaba.model.point.UpdatePointRequest;
import inu.thebite.toryaba.repository.PointRepository;
import inu.thebite.toryaba.repository.StoRepository;
import inu.thebite.toryaba.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final StoRepository stoRepository;


    @Transactional
    @Override
    public void addPoint(Long stoId, AddPointRequest addPointRequest) {
        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Point point = pointRepository.findByStoIdAndRound(stoId, sto.getRound())
                .orElseThrow(() -> new IllegalStateException("해당 STO에 대한 point list가 존재하지 않습니다."));

        point.addPoint(addPointRequest.getResult(), addPointRequest.getRegistrant());
    }

    @Transactional
    @Override
    public void updatePoint(Long stoId, UpdatePointRequest updatePointRequest) {
        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Point point = pointRepository.findByStoIdAndRound(stoId, sto.getRound())
                .orElseThrow(() -> new IllegalStateException("해당 STO에 대한 point list가 존재하지 않습니다."));

        point.updatePoint(updatePointRequest.getPoints(), updatePointRequest.getRegistrant());
    }

    @Transactional
    @Override
    public void deletePoint(Long stoId) {
        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Point point = pointRepository.findByStoIdAndRound(stoId, sto.getRound())
                .orElseThrow(() -> new IllegalStateException("해당 STO에 대한 point list가 존재하지 않습니다."));

        int size = point.getPoints().size();
        point.getPoints().remove(size-1);
        point.updatePoint(point.getPoints(), "테스트");
    }

    @Override
    public List<String> getPointList(Long stoId) {

        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Point point = pointRepository.findByStoIdAndRound(stoId, sto.getRound())
                .orElseThrow(() -> new IllegalStateException("조건에 해당하는 point row가 존재하지 않습니다."));

        List<String> pointList = pointRepository.findPointsByStoIdAndRound(point.getId());

        return pointList;
    }

    @Override
    public LtoGraphResponse getGraphValue(Long stoId) {
        stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        List<Float> rateList = new ArrayList<>();
        List<String> date = new ArrayList<>();
        List<Point> points = pointRepository.findAllByStoId(stoId);

        for(int i=0; i<points.size()-1; i++) {
            rateList.add(points.get(i).getPlusRate());
            rateList.add(points.get(i).getMinusRate());

            date.add(points.get(i).getRegisterDate().substring(5, 10));
        }

        LtoGraphResponse response = LtoGraphResponse.response(stoId, rateList, date);

        return response;
    }

    @Override
    public void insertValue(Long stoId, Float plusRate, Float minusRate) {
        Sto sto = stoRepository.findById(stoId)
                .orElseThrow(() -> new IllegalStateException("해당하는 STO가 존재하지 않습니다."));

        Point point = pointRepository.findByStoIdAndRound(stoId, sto.getRound())
                .orElseThrow(() -> new IllegalStateException("해당하는 Point가 존재하지 않습니다."));

        point.updateValue(plusRate, minusRate);
    }
}
