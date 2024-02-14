package inu.thebite.toryaba.service.serviceImpl;

import inu.thebite.toryaba.entity.Center;
import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.entity.Therapist;
import inu.thebite.toryaba.model.user.*;
import inu.thebite.toryaba.repository.CenterRepository;
import inu.thebite.toryaba.repository.MemberRepository;
import inu.thebite.toryaba.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final CenterRepository centerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String admin;

    @Override
    public Member login(LoginUserRequest loginUserRequest) {
        Member member = memberRepository.findById(loginUserRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if(!bCryptPasswordEncoder.matches(loginUserRequest.getPassword(), member.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        if(!member.getApprovalYN().equals("Y")) throw new IllegalStateException("해당 센터 원장님의 인증이 필요합니다.");

        return member;
    }

    @Override
    public Member findById(String id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected Member"));
    }

    @Override
    public FindMemberIdResponse findMemberId(FindMemberIdRequest findMemberIdRequest) {
        Member findMember = memberRepository.findByNameAndPhoneAndEmail(findMemberIdRequest.getName(), findMemberIdRequest.getPhone(), findMemberIdRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되어 있지 않은 회원입니다."));

        return FindMemberIdResponse.response(findMember.getId());
    }

    @Override
    public String findMemberPassword(FindMemberPasswordRequest findMemberPasswordRequest) {
        Member findMember = memberRepository.findByIdAndPhoneAndName(findMemberPasswordRequest.getId(), findMemberPasswordRequest.getPhone(), findMemberPasswordRequest.getName())
                .orElseThrow(() -> new IllegalArgumentException("입력한 정보를 다시 한번 확인해주세요."));

        // create temporary password
        String newPassword = createRandomPassword();

        // update password in DB
        changePassword(findMember, newPassword);

        // send temporary password mail
        sendEmail(findMember, newPassword);

        return "가입했던 메일로 임시 비밀번호를 발송해드렸습니다. 확인해주세요.";
    }

    @Override
    public boolean updatePassword(User user, UpdatePasswordRequest updatePasswordRequest) {
        Member findMember = memberRepository.findById(user.getUsername())
                .orElseThrow(() -> new IllegalStateException("회원정보가 일치하지 않습니다."));

        if(!bCryptPasswordEncoder.matches(findMember.getPassword(), updatePasswordRequest.getBeforePassword())) throw new IllegalStateException("기존의 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");

        changePassword(findMember, bCryptPasswordEncoder.encode(updatePasswordRequest.getAfterPassword()));

        return true;
    }

    public String createRandomPassword() {
        String newPassword = "";

        // create temporary password
        for(int i = 0; i < 10; i++) {
            int randomValue = (int) (Math.random() * 3);

            switch(randomValue) {
                case 0:
                    // uppercase
                    newPassword += (char) ((Math.random() * 26) + 65);
                    break;
                case 1:
                    // lowercase
                    newPassword += (char) ((Math.random() * 26) + 97);
                    break;
                case 2:
                    // number
                    newPassword += (char) ((Math.random() * 26) + 48);
                    break;
                default:
                    break;
            }
        }
        return newPassword;
    }

    @Transactional
    public void changePassword(Member member, String password) {
        member.updatePassword(password);
    }

    public void sendEmail(Member member, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(admin);
        message.setTo(member.getEmail());
        message.setSubject(member.getName() + "님, FROM 임시 비밀번호를 발송해 드립니다.");
        message.setText("안녕하세요. " + member.getName() + "님.\n\n 임시 비밀번호는 " + password + "입니다.\n 로그인 후 임시 비밀번호를 새로운 비밀번호로 재설정해주세요.");
        javaMailSender.send(message);
    }

    @Transactional
    @Override
    public void approveAuth(String id) {
        Therapist findTherapist = (Therapist) memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        findTherapist.approveTherapist();
    }

    @Override
    public List<Therapist> getOutstandingAuthorization(Long centerId) {
        Center center = centerRepository.findById(centerId)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 센터입니다."));

        return memberRepository.findByCenterIdAndAuth(centerId);
    }
}
