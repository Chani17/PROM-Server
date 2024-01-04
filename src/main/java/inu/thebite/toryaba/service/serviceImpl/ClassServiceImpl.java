package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Class;
import inu.thebite.toryaba.model.childClass.ClassRequest;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.ClassRepository;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final CenterRepository centerRepository;

    @Transactional
    @Override
    public Class addClass(String userId, Long centerId, ClassRequest classRequest) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("해당하는 센터가 존재하지 않습니다."));

        if(!center.getDirector().equals(userId)) {
            throw new IllegalStateException("해당 센터의 소유자가 아닙니다.");
        }

        Class newClass = Class.createClass(classRequest.getName(), center);
        classRepository.save(newClass);
        return newClass;
    }

    @Transactional
    @Override
    public Class updateClass(String userId, Long classId, ClassRequest classRequest) {
        Class newClass = classRepository.findById(classId)
                .orElseThrow(() -> new IllegalStateException("해당 반이 존재하지 않습니다."));

        newClass.updateClass(classRequest.getName());
        return newClass;
    }

    @Override
    public List<Class> getClassListByCenter(String userId, Long centerId) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("해당하는 센터가 존재하지 않습니다."));

        if(!center.getDirector().equals(userId)) {
            throw new IllegalStateException("해당 센터의 소유자가 아닙니다.");
        }

        List<Class> classList = classRepository.findAllByCenterId(center.getId());
        return classList;
    }

    @Override
    public List<Class> getClassList(String userId) {
        List<Class> classList = classRepository.findAll();
        return classList;
    }

    @Transactional
    @Override
    public void deleteClass(String userId, Long classId) {
        Class findClass = classRepository.findById(classId)
                .orElseThrow(() -> new IllegalStateException("해당 반이 존재하지 않습니다."));

        classRepository.deleteById(findClass.getId());
    }
}
