package inu.thebite.toryaba.config;

import inu.thebite.toryaba.config.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";
    @Value("${jwt.secret_key}")
    private String secretKey;

    /**
     * Access Token값이 담긴 Authorization 헤더값을 가져온 뒤 Access Token이 유효하다면 인증 정보를 설정한다.
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 요청 header의 Authorization 키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        log.info("Authorization = {}" + authorizationHeader);

        // 가져온 값에서 접두사 제거
        String token = resolveToken(authorizationHeader);
        log.info("dofilter의 token = {}" + token);
        log.trace("dofilter의 token = {}" + token);

        checkTokenPermissions(token);
        verifyTokenAndGetAuthorities(token);

        boolean result = tokenProvider.validToken(token);
        log.info("validToken result = {}", result);
        log.trace("validToken result = {}", result);

        // 가져온 token이 유효한지 확인하고, 유효한 때는 인증 정보 설정
        if(result) {
            log.info("토큰이 있고, 유효함을 인증");
            // token이 유효하다면 인증 객체를 만드는 메서드 호출
            Authentication authentication = tokenProvider.getAuthentication(token);

            // 만들어온 인증 객체를 스레드 로컬(저장소)의 Security Context Holder에 넣는다. -> 인징이 완료된 것이다!
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("sc = {}", SecurityContextHolder.getContext());
        } else {
            log.info("토큰이 없거나 유효하지 않음");
        }
        filterChain.doFilter(request, response);
        log.info("doFilterInternal 끝");
    }

//    public String resolveToken(HttpServletRequest request) {
//        /**
//         * HttpServletRequest의 헤더에서 Authorization이라는 헤더의 값을 가져온다.
//         * 가져온 결과는 Bearer + JWT token
//         * token이 존재한다면 bearer를 제거하고 반환
//         */
//        String header = request.getHeader(HEADER_AUTHORIZATION);
//
//        if(StringUtils.hasText(header) && header.startsWith("Bearer ")) {
//            return header.substring(7);
//        }
//        return null;
//    }

    private String resolveToken(String authorizationHeader) {
        log.info("resolveToken 끝");
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    // 토큰에서 권한 확인
    private void checkTokenPermissions(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("your-secret-key")  // 사용한 시크릿 키를 설정
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            // 권한 정보 확인
            String authorities = (String) claims.get("authorities");
            System.out.println("권한 정보: " + authorities);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 위의 코드를 활용하여 실제로 토큰에서 권한 확인
    public void verifyTokenAndGetAuthorities(String token) {
        log.info("토큰 검증 및 권한 확인 시작");
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)  // 사용한 시크릿 키를 설정
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            // 권한 정보 확인
            String authorities = (String) claims.get("authorities");
            log.info("토큰 권한 정보: {}", authorities);
        } catch (Exception e) {
            log.error("토큰 검증 및 권한 확인 중 오류 발생", e);
        }
        log.info("토큰 검증 및 권한 확인 종료");
    }
}
