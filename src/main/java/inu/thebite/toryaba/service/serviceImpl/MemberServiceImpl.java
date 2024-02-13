package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.model.user.FindMemberIdRequest;
import inu.thebite.toryaba.model.user.FindMemberIdResponse;
import inu.thebite.toryaba.model.user.FindMemberPasswordRequest;
import inu.thebite.toryaba.model.user.LoginUserRequest;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member login(LoginUserRequest loginUserRequest) {
        Member member = memberRepository.findById(loginUserRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if(!bCryptPasswordEncoder.matches(loginUserRequest.getPassword(), member.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    @Override
    public Member findById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected Member"));
    }

    @Override
    public FindMemberIdResponse findMemberId(FindMemberIdRequest findMemberIdRequest) {
        String findUserId = memberRepository.findByNameAndPhoneAndEmail(findMemberIdRequest.getName(), findMemberIdRequest.getPhone(), findMemberIdRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되어 있지 않은 회원입니다."));

        return FindMemberIdResponse.response(findUserId);
    }

    @Override
    public void findMemberPassword(FindMemberPasswordRequest findMemberPasswordRequest) {
        Member findMember = memberRepository.findByIdAndPhoneAndEmail(findMemberPasswordRequest.getId(), findMemberPasswordRequest.getPhone(), findMemberPasswordRequest.getName())
                .orElseThrow(() -> new IllegalArgumentException("입력한 정보를 다시 한번 확인해주세요."));

        
    }
}
