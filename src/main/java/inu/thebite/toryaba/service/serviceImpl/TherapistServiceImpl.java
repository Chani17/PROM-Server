package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Therapist;
import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TherapistServiceImpl implements TherapistService {

    private final CenterRepository centerRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void joinTherapistUser(Long centerId, AddUserRequest addUserRequest) {

        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));

        if(memberRepository.findById(addUserRequest.getId()).isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        Therapist therapist = new Therapist(addUserRequest.getId(), addUserRequest.getPassword(), addUserRequest.getName(), addUserRequest.getEmail(), addUserRequest.getPhone(), center);
        memberRepository.save(therapist);
    }

}
