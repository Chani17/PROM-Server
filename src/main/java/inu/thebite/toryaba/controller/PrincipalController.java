package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.entity.Principal;
import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/principal")
public class PrincipalController {

    private final PrincipalService principalService;

    // join principal user
    // need a security config
    @PostMapping("/join")
    public ResponseEntity joinPrincipalUser(@RequestBody AddUserRequest addUserRequest) {
        Principal principal = principalService.joinPrincipalUser(addUserRequest);
        return ResponseEntity.ok(principal);
    }

    // login principal user
    @GetMapping("/login")
    public ResponseEntity loginPrincipalUser(@RequestBody LoginUserRequest loginUserRequest) {
        Principal principal = principalService.loginPrincipalUser(loginUserRequest);
        return ResponseEntity.ok(principal);
    }

}
