package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.jwt.TokenProvider;
import inu.thebite.toryaba.entity.Member;
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
    private final TokenProvider tokenProvider;

    // join principal user
    @PostMapping("/members/join")
    public void joinPrincipalUser(@RequestBody AddDirectorRequest addDirectorRequest) {
        directorService.joinDirector(addDirectorRequest);
    }

    // join therapist member
    @PostMapping("/members/{centerId}/join")
    public void joinTherapistUser(@PathVariable Long centerId, @RequestBody AddDirectorRequest addDirectorRequest) {
        therapistService.joinTherapist(centerId, addDirectorRequest);
    }

    // login user
    @PostMapping("/members/login")
    public String loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        Member member = memberService.login(loginUserRequest);
        String token = tokenProvider.createToken(member);
        return token;
    }

    // refresh token을 기반으로 새로운 access token을 만들어주는 function
    @PostMapping("/members/token")
    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
    }

}
