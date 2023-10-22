package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.entity.Principal;
import inu.thebite.toryaba.model.AddUserRequest;
import inu.thebite.toryaba.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PrincipalController {

    private final PrincipalService principalService;

    // join principal user
    // need a security config
    @PostMapping("/join")
    public ResponseEntity joinPrincipalUser(@RequestBody AddUserRequest addUserRequest) {
        Principal principal = principalService.joinPrincipalUser(addUserRequest);
        return ResponseEntity.ok(principal);
    }

    
}
