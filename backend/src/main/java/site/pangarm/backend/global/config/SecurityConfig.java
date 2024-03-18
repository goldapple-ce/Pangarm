package site.pangarm.backend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import site.pangarm.backend.domain.member.MemberRepository;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.global.filter.*;
import site.pangarm.backend.global.jwt.JwtProvider;

@EnableWebSecurity //스프링 시큐리티 필터(SecurityConfig)가 스프링 필터체인에 등록됨.
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // AuthenticationManager 생성
        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);

        // userDetailService 주입
        sharedObject.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());

        AuthenticationManager authenticationManager = sharedObject.build();

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session) ->  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests
//                            .requestMatchers("/member/test").authenticated()
                            .requestMatchers("/member/test/**").hasAnyRole("USER")
                            .anyRequest().permitAll();
                })
                .authenticationManager(authenticationManager)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(customAuthenticationEntryPoint)) // 로그인이 안되어 있을 때
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(customAccessDeniedHandler)) // 토근은 있는 데 권한이 없을 때
                .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtProvider))
                .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtProvider, memberRepository))
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthorizationFilter.class)

                .build();

    }

}
