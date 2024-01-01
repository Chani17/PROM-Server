package inu.thebite.toryaba.config;

import inu.thebite.toryaba.config.jwt.TokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

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
        System.out.println("doFilterInternal 들어옴");
        // 요청 header의 Authorization 키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        System.out.println("Authorization = " + authorizationHeader);
        // 가져온 값에서 접두사 제거
        String token = getAccessToken(authorizationHeader);
        System.out.println("dofilter의 token = " + token);

        // 가져온 token이 유효한지 확인하고, 유효한 때는 인증 정보 설정
        if(StringUtils.hasText(token) && tokenProvider.validToken(token)) {
            System.out.println("토큰이 있고, 유효함을 인증");
            // token이 유효하다면 인증 객체를 만드는 메서드 호출
            Authentication authentication = tokenProvider.getAuthentication(token);

            // 만들어온 인증 객체를 스레드 로컬(저장소)의 Security Context Holder에 넣는다. -> 인징이 완료된 것이다!
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
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

    private String getAccessToken(String authorizationHeader) {
        if(authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
