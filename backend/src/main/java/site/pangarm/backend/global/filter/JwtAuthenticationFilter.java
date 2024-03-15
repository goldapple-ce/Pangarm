package site.pangarm.backend.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.member.MemberDetails;
import site.pangarm.backend.global.jwt.JwtProvider;
import site.pangarm.backend.global.jwt.JwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        setFilterProcessesUrl("/api/member/login");
    }

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter -> attemptAuthentication");
        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            String email = member.getEmail();
            String password = member.getPassword();

            log.info("memberEmail: " + email);
            log.info("password: " + password);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            System.out.println("authentication: " + authentication);
            return authentication;


        }catch (Exception e) {
            e.printStackTrace();
        }
        //세션에 담는다. -> 컨트롤러에서 받는다.
        return null;


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //JWT 반환
        log.info("successfulAuthentication : 인증 완료");
        MemberDetails memberDetails = (MemberDetails) authResult.getPrincipal();

        //Access Token 생성
        String accessToken = tokenProvider.createAccessToken(memberDetails.getUsername(),memberDetails.getAuthorities());

        // Refresh Token 생성
        String refreshToken = tokenProvider.createRefreshToken(memberDetails.getUsername(),memberDetails.getAuthorities());

        JwtToken token = JwtToken.builder()
                .grantType("Bearer ")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        response.getWriter().write(new ObjectMapper().writeValueAsString(token));

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

    }
}
