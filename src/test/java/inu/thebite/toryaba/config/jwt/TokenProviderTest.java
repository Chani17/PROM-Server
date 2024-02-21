package inu.thebite.toryaba.config.jwt;

import inu.thebite.toryaba.entity.Director;
import inu.thebite.toryaba.repository.MemberRepository;
import io.jsonwebtoken.Jwts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TokenProviderTest {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberRepository memberRepository;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret_key}")
    private String secret_key;

    /**
     * generateToken() 검증 테스트
     * 우선, Director 계정만 가정
     */
    @DisplayName("generateToken() : 유저 정보와 만료 기간을 전달해 token을 만들 수 있다.")
    @Test
    void generateToken() {
        // given
        List<String> qualification = List.of("BCBA", "QBA", "KABA");
        Director testUser = new Director("test1", "test1", "테스트1", "test1@gmail.com", "010-1234-5678", "미술 치료", qualification);
        memberRepository.save(testUser);

        // when
        String token = tokenProvider.createToken(testUser);

        // then
        String memberId = Jwts.parser()
                .setSigningKey(secret_key)
                .parseClaimsJws(token)
                .getBody()
                .get("id", String.class);

        assertThat(memberId).isEqualTo(testUser.getId());
    }


    /**
     * invalidToken() 검증 테스트
     */
    @DisplayName("invalidToken() : 만료된 token인 때에 유효성 검증에 실패한다.")
    @Test
    void validToken_invalidToken() {
        // given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build()
                .createToken(issuer, secret_key);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        assertThat(result).isFalse();
    }

    /**
     * validToken() 검증 테스트
     */
    @DisplayName("validToken() : 유효한 token인 때에 유효성 검증에 성공한다.")
    @Test
    void validToken_validToken() {
        // given
        String token = JwtFactory.withDefaultValues()
                .createToken(issuer, secret_key);

        // when
        boolean result = tokenProvider.validToken(token);

        // then
        assertThat(result).isTrue();
    }

    /**
     * getAuthentication() 검증 테스트
     */
    @DisplayName("getAuthentication() : token 기반으로 인증 정보를 가져올 수 있다.")
    @Test
    void getAuthentication() {
        // given
        String userId = "test1";
        String token = JwtFactory.builder()
                .subject(userId)
                .build()
                .createToken(issuer, secret_key);

        // when
        Authentication authentication = tokenProvider.getAuthentication(token);

        // then
        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userId);
    }

    /**
     * getUserId() 검증 테스트
     */
    @DisplayName("getUserId() : token으로 유저 ID를 가져올 수 있다.")
    @Test
    void getUserId() {
        // given
        String userId = "test1";
        String token = JwtFactory.builder()
                .claims(Map.of("id", userId))
                .build()
                .createToken(issuer, secret_key);

        // when
        String memberIdByToken = tokenProvider.getMemberId(token);

        // then
        assertThat(memberIdByToken).isEqualTo(userId);
    }
}
