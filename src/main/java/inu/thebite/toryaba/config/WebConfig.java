package inu.thebite.toryaba.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class WebConfig {

    private final UserDetailsService userDetailsService;

    /**
     * Spring Security 모든 기능 비활성화
     * 즉, 인증, 인가 서비스를 모든 곳에 적용하지는 않는다.
     * 일반적으로 정적 리소스(이미지, HTML 파일)에 설정한다. 즉, 정적 리소스만 spring security 사용을 비활성화
     * 현재는 사용하지 않을 것 같아 주석처리
     * @return
     */
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers("/static/**");
//    }


    /**
     * 특정 HTTP 요청에 대한 보안 구성
     * 해당 메서드에서는 인증/인가 및 로그인, 로그아웃 관련 설정을 할 수 있다.
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 인증, 인가 설정
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/members/login").permitAll()
                        .anyRequest().authenticated()
                )
                // 폼 기반 로그인 설정
                // defaultSuccessUrl 설정 추가 여부
                .formLogin(form -> form
                        .loginPage("/members/login")
                )
                // 로그아웃 설정
                .logout((logout) -> logout
                        .logoutSuccessUrl("/members/login")
                        .invalidateHttpSession(true)
                )
                // csrf 비활성화(나중에는 활성화 해야함)
                .csrf((csrf) -> csrf.disable())
                .build();
    }

}
