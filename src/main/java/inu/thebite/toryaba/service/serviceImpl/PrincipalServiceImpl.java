package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Principal;
import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.service.PrincipalService;
import inu.thebite.toryaba.service.repository.PrincipalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    private final PrincipalRepository principalRepository;

    @Transactional
    @Override
    public Principal joinPrincipalUser(AddUserRequest addUserRequest) {
        // Id duplicate check
        Principal principalUser = principalRepository.findByMemberId(addUserRequest.getId())
                .orElseThrow(() -> new IllegalStateException("이미 존재하는 아이디입니다. 다른 아이디를 사용하세요."));

        Principal principal = Principal.createPrincipal(principalUser.getMemberId(), principalUser.getPassword(), principalUser.getName(), principalUser.getEmail(), principalUser.getPhone());
        principalRepository.save(principal);
        return principal;
    }

    @Override
    public Principal loginPrincipalUser(LoginUserRequest loginUserRequest) {
        // Id validation check
        Principal principal = principalRepository.findByMemberId(loginUserRequest.getId())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        // Verify the user password
        // Need a security config
        if(!principal.getPassword().equals(loginUserRequest.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }
        return principal;
    }
}
