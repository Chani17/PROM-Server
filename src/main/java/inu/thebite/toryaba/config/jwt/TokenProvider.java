package inu.thebite.toryaba.config.jwt;

import inu.thebite.toryaba.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * Token을 생성하고 올바른 Token인지 유효성 검사를 하고, Token에서 필요한 정보를 가져오는 class
 */

@RequiredArgsConstructor
@Service
public class TokenProvider {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret_key}")
    private String secretKey;

    private Long tokenValidMilliseconds = 1000L * 60 * 60;      // 1시간 유효

    /**
     * JWT Token 생성 메서드
     * @param member
     * @return token
     */
    public String createToken(Member member) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)                       // header typ: JWT
                .setIssuer(issuer)                                                  // 내용 iss : yml에서 설정한 값
                .setIssuedAt(now)                                                   // 내용 iat : 현재 시간
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))    // 내용 exp : 유효 시간
                .setSubject(member.getEmail())                                      // 내용 sub : user email
                .claim("id", member.getId())                                  // 클레임 id : user ID
                .signWith(SignatureAlgorithm.HS256, secretKey)                      // signature : 비밀값과 함께 해시값을 HS256 방식으로 암호화
                .compact();
    }


    /**
     * JWT Token 유효성 검증 메서드
     * 검사하는 내용
     * 1. secretKey를 통해서 jwt를 parsing하는데 만약 서버가 가지고 있는 secretKey로 parsing이 안된다면 변조가 되었거나 다른 서버의 jwt이므로 예외가 발생
     * 2. jwt Token의 유효 시간과 서버 시간을 비교해서 유효 시간이 서버 시간보다 전이라면 false -> token이 만료되었다는 의미
     * @param token
     * @return boolean value
     */
    public boolean validToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)           // 비밀값으로 복호화
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {         // 복호화 과정에서 에러가 나면 유효하지 않은 Token
            return false;
        }
    }


    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("LEVEL4"));

        return new UsernamePAsswordAuthenticationToken(new )
    }

    /**
     * Claim(payload)을 가져오는 메서드
     * @param token
     * @return Claims 정보
     */
    private Claims getClaims(String token) {
        return Jwts.parser()                    // Claims 조회
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
