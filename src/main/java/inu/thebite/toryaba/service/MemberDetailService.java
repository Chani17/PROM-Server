package inu.thebite.toryaba.service;

import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Spring Security에서 사용자 정보를 가져오는 interface
 */

@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 회원입니다."));

        // User parameter 다시 확인하기!
        log.trace("findMember.getAuth().toString() = " + findMember.getAuth().toString());
        return new User(findMember.getId(), findMember.getPassword(), Collections.singleton(new SimpleGrantedAuthority(findMember.getAuth().toString())));
    }
}