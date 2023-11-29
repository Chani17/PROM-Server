package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.model.user.AddUserRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.service.LoginService;
import inu.thebite.toryaba.service.DirectorService;
import inu.thebite.toryaba.service.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberController {

    private final DirectorService directorService;
    private final TherapistService therapistService;
    private final LoginService loginService;

    // join principal user
    // need a security config
    @PostMapping("/join")
    public ResponseEntity joinPrincipalUser(@RequestBody AddUserRequest addUserRequest) {
        directorService.joinPrincipalUser(addUserRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // join therapist member
    // need a security config
    @PostMapping("/{centerId}/join")
    public ResponseEntity joinTherapistUser(@PathVariable Long centerId, @RequestBody AddUserRequest addUserRequest) {
        therapistService.joinTherapistUser(centerId, addUserRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // login user
    // need a security config
    // need to modify return value
    @GetMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        loginService.login(loginUserRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
