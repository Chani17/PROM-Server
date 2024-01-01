package inu.thebite.toryaba.config.jwt;

import inu.thebite.toryaba.entity.Member;
import inu.thebite.toryaba.service.MemberDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    private Long tokenValidMilliseconds = 1000L * 60 * 5;      // 5분 유효
    private final MemberDetailService memberDetailService;


    /**
     * JWT Token 생성 메서드
     * @param member
     * @return token
     */
    public String createToken(Member member) {
        Date now = new Date();

        return Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)                       // header typ: JWT
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
            // PAYLOAD
            Jwts.parser()
                    .setSigningKey(secretKey)           // 비밀값으로 복호화
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {         // 복호화 과정에서 에러가 나면 유효하지 않은 Token
            return false;
        }
    }

    /**
     * Token 기반으로 인증 정보(객체)를 가져오는 메서드
     * @param token
     * @return
     */
    public Authentication getAuthentication(String token) {

//        Claims claims = getClaims(token);
//        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("LEVEL4"));
//
//        // getClaims()을 통해 claim 반환받아 사용자 이메일이 들어 있는 token 제목 sub와 token 기반으로 인증 정보 생성
//        return new UsernamePasswordAuthenticationToken(new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities), token, authorities);

        /**
         * getMemberId을 호출하여 Token에 저장되어 있는 회원의 정보를 가져온다.
         * userDetailsService.loadUserByUsername(회원 id)를 호출하여 UserDetails 타입을 객체에 반환
         */
        UserDetails userDetails = memberDetailService.loadUserByUsername(getMemberId(token));

        /**
         * 반환받은 UserDetails 타입의 객체를 이용하여 Authentication(interface)을 구현하는 UsernamePasswordAuthenticationToken을 생성하여 반환
         * 이때, 인증 객체의 principal에는 userdetails가 들어가게 됨.
         * 결국, Authentication -> principal(user 타입) -> username이 회원의 정보, user객체에서 회원의 정보를 찾아낼 수 있다.
         */
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
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

    /**
     * Token 기반으로 user ID를 가져오는 메서드
     * @param token
     * @return
     */
    public String getMemberId(String token) {
        Claims claims = getClaims(token);

        // claims에서 id 키로 저장된 값을 가져와 반환
        return claims.get("id", String.class);
    }
}
