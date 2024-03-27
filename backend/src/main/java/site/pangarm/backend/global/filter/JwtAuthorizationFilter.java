package site.pangarm.backend.global.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.global.jwt.TokenProvider;
import site.pangarm.backend.global.jwt.JwtToken;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider jwtProvider;
    private final ObjectMapper objectMapper;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, TokenProvider jwtProvider, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("start AuthorizationFilter");

        String token = request.getHeader(AUTHORIZATION_HEADER);

        //header 있는지 확인
        if (token == null || !token.startsWith(BEARER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        //Token 정상 여부 확인
        Claims claims = jwtProvider.validationAndparesClaims(token);
        Authentication authentication = jwtProvider.getAuthentication(claims);
        //JWT 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
        if(jwtProvider.validateAccessToken(claims)){
            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }else if(jwtProvider.validateRefreshToken(claims)){
            JwtToken newToken = jwtProvider.createToken(authentication);
            response.addHeader(AUTHORIZATION_HEADER,objectMapper.writeValueAsString(newToken));
        }

    }


}
