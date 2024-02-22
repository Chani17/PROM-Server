package inu.thebite.toryaba.controller;


import inu.thebite.toryaba.config.LoginMember;
import inu.thebite.toryaba.config.jwt.TokenProvider;
import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.entity.Therapist;
import inu.thebite.toryaba.model.user.*;
import inu.thebite.toryaba.service.MemberService;
import inu.thebite.toryaba.service.DirectorService;
import inu.thebite.toryaba.service.TherapistService;
import inu.thebite.toryaba.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public boolean joinPrincipalUser(@RequestBody AddDirectorRequest addDirectorRequest) {
        return directorService.joinDirector(addDirectorRequest);
    }

    // join therapist member
    @PostMapping("/members/therapist/join")
    public boolean joinTherapistUser(@RequestBody AddTherapistRequest addTherapistRequest) {
        return therapistService.joinTherapist(addTherapistRequest);
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
        String authorization = headers.get("authorization");
        String token = authorization.substring(7);
        boolean result = tokenProvider.validToken(token);
        String memberId = tokenProvider.getMemberId(token);
        Member member = memberService.findById(memberId);
        ValidationTokenResponse response = new ValidationTokenResponse(member.getName(), result);
        return response;
    }

    // update password
    @PostMapping("/members/password")
    public boolean updatePassword(@LoginMember User user, @RequestBody UpdatePasswordRequest updatePasswordRequest) {
         return memberService.updatePassword(user, updatePasswordRequest);
    }

    // find user id
    @GetMapping("/members/find/id")
    public FindMemberIdResponse findMemberId(@RequestBody FindMemberIdRequest findMemberIdRequest) {
        return memberService.findMemberId(findMemberIdRequest);
    }

    // find user password
    @GetMapping("/members/find/password")
    public TemporaryPasswordResponse findMemberPassword(@RequestBody FindMemberPasswordRequest findMemberPasswordRequest) {
        return memberService.findMemberPassword(findMemberPasswordRequest);
    }

    // grant authority
    @PatchMapping("/therapists/{id}/auth")
    public void approveAuth(@PathVariable String id) {
        memberService.approveAuth(id);
    }

    // get outstanding authorization list
    @GetMapping("/{centerId}/outstanding/authorization")
    public List<Therapist> getOutstandingAuthorization(@PathVariable Long centerId) {
        return memberService.getOutstandingAuthorization(centerId);
    }

    // edit profile
    @PatchMapping("/edit/profile")
    public MemberResponse editProfile(@LoginMember User user, @RequestBody EditProfileRequest editProfileRequest) {
        return memberService.editProfile(user, editProfileRequest);
    }

    // get profile
    @GetMapping("/profile")
    public MemberResponse getProfile(@LoginMember User user) {
        return memberService.getProfile(user);
    }

    // refresh token을 기반으로 새로운 access token을 만들어주는 function
//    @PostMapping("/members/token")
//    public ResponseEntity<CreateAccessTokenResponse> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
//        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());
//        return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
//    }

}
