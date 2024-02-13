package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Therapist;
import inu.thebite.toryaba.model.user.AddDirectorRequest;
import inu.thebite.toryaba.model.user.AddTherapistRequest;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TherapistServiceImpl implements TherapistService {

    private final CenterRepository centerRepository;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public String joinTherapist(Long centerId, AddTherapistRequest addTherapistRequest) {

        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));

        if(memberRepository.findById(addTherapistRequest.getId()).isPresent()) {
            throw new IllegalStateException("이미 가입된 아이디입니다. 다른 아이디로 시도해주세요.");
        }

        Therapist therapist = new Therapist(addTherapistRequest.getId(), bCryptPasswordEncoder.encode(addTherapistRequest.getPassword()), addTherapistRequest.getName(), addTherapistRequest.getEmail(), addTherapistRequest.getPhone(), addTherapistRequest.getForte(), addTherapistRequest.getQualification(), center);
        Therapist savedTherapist = memberRepository.save(therapist);
        return savedTherapist.getId();
    }

}
