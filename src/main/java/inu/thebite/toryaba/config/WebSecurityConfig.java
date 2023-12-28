package inu.thebite.toryaba.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailsService userService;

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
//                .requestMatchers("/static/**")
//                .requestMatchers("/h2-console/**");
//    }


    /**
     * 특정 HTTP 요청에 대한 보안 구성
     * 해당 메서드에서는 인증/인가 및 로그인, 로그아웃 관련 설정을 할 수 있다.
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        return http
                // 인증, 인가 설정
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(new MvcRequestMatcher(introspector, "/members/login")).permitAll()
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


    /**
     * 인증 관리자 관련 설정
     * 사용자 정보를 가져올 서비스를 재정의하거나, 인증 방법(LDAP, JDGC 기반 인증 등)을 설정할 때 사용
     * @param http
     * @param bCryptPasswordEncoder
     * @param userDetailsService
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
       return http
               .getSharedObject(AuthenticationManagerBuilder.class)
               .userDetailsService(userService)
               .passwordEncoder(bCryptPasswordEncoder)
               // 다른 방법 찾아보기
               .and()
               .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
