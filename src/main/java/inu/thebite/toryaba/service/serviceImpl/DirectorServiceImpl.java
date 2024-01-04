package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Director;
import inu.thebite.toryaba.model.user.AddDirectorRequest;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public String joinDirector(AddDirectorRequest addDirectorRequest) {
        if(memberRepository.existsById(addDirectorRequest.getId())) {
            throw new IllegalStateException("이미 존재하는 아이디입니다. 다른 아이디로 변경해주세요.");
        }

        Director director = new Director(addDirectorRequest.getId(), bCryptPasswordEncoder.encode(addDirectorRequest.getPassword()), addDirectorRequest.getName(), addDirectorRequest.getEmail(), addDirectorRequest.getPhone());
        Director savedDirector = memberRepository.save(director);
        return savedDirector.getId();
    }


}
