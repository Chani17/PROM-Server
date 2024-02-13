package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.jwt.TokenProvider;
import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.model.user.*;
import inu.thebite.toryaba.service.MemberService;
import inu.thebite.toryaba.service.DirectorService;
import inu.thebite.toryaba.service.TherapistService;
import inu.thebite.toryaba.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
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
    public void joinTherapistUser(@PathVariable Long centerId, @RequestBody AddTherapistRequest addTherapistRequest) {
        therapistService.joinTherapist(centerId, addTherapistRequest);
    }

    // login user
    @PostMapping("/members/login")
    public LoginResponse loginUser(@RequestBody LoginUserRequest loginUserRequest) {
        Member member = memberService.login(loginUserRequest);
        String token = tokenProvider.createToken(member);
        LoginResponse loginResponse = new LoginResponse(member.getName(), token);
        return loginResponse;
    }

    // validate token
    @PostMapping("/valid/token")
    public ValidationTokenResponse validationToken(@RequestHeader Map<String, String> headers) {
        log.info("headers = {}", headers);
        String authorization = headers.get("authorization");
        String token = authorization.substring(7);
        boolean result = tokenProvider.validToken(token);
        String memberId = tokenProvider.getMemberId(token);
        Member member = memberService.findById(memberId);
        ValidationTokenResponse response = new ValidationTokenResponse(member.getName(), result);
        return response;
    }

    // find user id
    @GetMapping("/members/find/id")
    public FindMemberIdResponse findMemberId(@RequestBody FindMemberIdRequest findMemberIdRequest) {
        return memberService.findMemberId(findMemberIdRequest);
    }

    
    // refresh token을 기반으로 새로운 access token을 만들어주는 function
//    @PostMapping("/members/token")
//    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
//        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
//        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
//    }

}
