package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.model.user.AddDirectorRequest;
import inu.thebite.toryaba.model.user.CreateAccessTokenRequest;
import inu.thebite.toryaba.model.user.CreateAccessTokenResponse;
import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.service.MemberService;
import inu.thebite.toryaba.service.DirectorService;
import inu.thebite.toryaba.service.TherapistService;
import inu.thebite.toryaba.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final DirectorService directorService;
    private final TherapistService therapistService;
    private final MemberService memberService;
    private final TokenService tokenService;

    // join principal user
    @PostMapping("/members/join")
    public String joinPrincipalUser(@RequestBody AddDirectorRequest addDirectorRequest) {
        String directorId = directorService.joinDirector(addDirectorRequest);
        return directorId;
    }

    // join therapist member
    @PostMapping("/members/{centerId}/join")
    public String joinTherapistUser(@PathVariable Long centerId, @RequestBody AddDirectorRequest addDirectorRequest) {
        String therapistId = therapistService.joinTherapist(centerId, addDirectorRequest);
        return therapistId;
    }

    // login user
    @GetMapping("/members/login")
    public ResponseEntity loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        memberService.login(loginUserRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // refresh token을 기반으로 새로운 access token을 만들어주는 function
    @PostMapping("/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
    }

}
