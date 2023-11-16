package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Director;
import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void joinPrincipalUser(AddUserRequest addUserRequest) {
        // Id duplicate check
        if(memberRepository.findById(addUserRequest.getId()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다. 다른 아이디를 사용하세요.");
        }

        Director director = new Director(addUserRequest.getId(), addUserRequest.getPassword(), addUserRequest.getName(), addUserRequest.getEmail(), addUserRequest.getPhone());
        memberRepository.save(director);
    }

}
