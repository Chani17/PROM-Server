package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Principal;
import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.service.PrincipalService;
import inu.thebite.toryaba.repository.PrincipalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    private final PrincipalRepository principalRepository;

    @Transactional
    @Override
    public void joinPrincipalUser(AddUserRequest addUserRequest) {
        // Id duplicate check
        if(principalRepository.findById(addUserRequest.getId()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다. 다른 아이디를 사용하세요.");
        }

        Principal principal = Principal.createPrincipal(addUserRequest.getId(), addUserRequest.getPassword(), addUserRequest.getName(), addUserRequest.getEmail(), addUserRequest.getPhone());
        principalRepository.save(principal);
    }

}
