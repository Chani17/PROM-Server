package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public void login(LoginUserRequest loginUserRequest) {

        if(memberRepository.findById(loginUserRequest.getId()).isPresent()) {
            if(!memberRepository.findById(loginUserRequest.getId()).get().getPassword().equals(loginUserRequest.getPassword())) {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
            }
        } else if(memberRepository.findById(loginUserRequest.getId()).isPresent()) {
            if(!memberRepository.findById(loginUserRequest.getId()).get().getPassword().equals(loginUserRequest.getPassword())) {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalStateException("해당 계정이 존재하지 않습니다.");
        }

    }
}