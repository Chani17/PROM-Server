package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Therapist;
import inu.thebite.toryaba.model.user.AddDirectorRequest;
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
    public void joinTherapistUser(Long centerId, AddDirectorRequest addDirectorRequest) {

        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));

        if(memberRepository.findById(addDirectorRequest.getId()).isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }

        Therapist therapist = new Therapist(addDirectorRequest.getId(), addDirectorRequest.getPassword(), addDirectorRequest.getName(), addDirectorRequest.getEmail(), addDirectorRequest.getPhone(), center);
        memberRepository.save(therapist);
    }

}
