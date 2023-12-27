package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.model.user.AddDirectorRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.service.MemberService;
import inu.thebite.toryaba.service.DirectorService;
import inu.thebite.toryaba.service.TherapistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {

    private final DirectorService directorService;
    private final TherapistService therapistService;
    private final MemberService memberService;

    // join principal user
    @PostMapping("/join")
    public String joinPrincipalUser(@RequestBody AddDirectorRequest addDirectorRequest) {
        String directorId = directorService.joinDirector(addDirectorRequest);
        return directorId;
    }

    // join therapist member
    @PostMapping("/{centerId}/join")
    public String joinTherapistUser(@PathVariable Long centerId, @RequestBody AddDirectorRequest addDirectorRequest) {
        String therapistId = therapistService.joinTherapist(centerId, addDirectorRequest);
        return therapistId;
    }

    // login user
    // need a security config
    // need to modify return value
    @GetMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        memberService.login(loginUserRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
