package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.repository.DirectorRepository;
import inu.thebite.toryaba.repository.TherapistRepository;
import inu.thebite.toryaba.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final DirectorRepository directorRepository;

    private final TherapistRepository therapistRepository;

    @Override
    public void login(LoginUserRequest loginUserRequest) {

        if(directorRepository.findById(loginUserRequest.getId()).isPresent()) {
            if(!directorRepository.findById(loginUserRequest.getId()).get().getPassword().equals(loginUserRequest.getPassword())) {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
            }
        } else if(therapistRepository.findById(loginUserRequest.getId()).isPresent()) {
            if(!therapistRepository.findById(loginUserRequest.getId()).get().getPassword().equals(loginUserRequest.getPassword())) {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new IllegalStateException("해당 계정이 존재하지 않습니다.");
        }

    }
}
