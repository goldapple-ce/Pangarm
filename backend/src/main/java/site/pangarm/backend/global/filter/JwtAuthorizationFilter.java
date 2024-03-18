package site.pangarm.backend.global.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import site.pangarm.backend.domain.auth.MemberDetails;
import site.pangarm.backend.domain.member.MemberRepository;
import site.pangarm.backend.global.jwt.JwtProvider;
import site.pangarm.backend.global.jwt.JwtToken;

import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider, MemberRepository memberRepository) {
        super(authenticationManager);
        this.jwtProvider = jwtProvider;
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {

            log.info("JwtAuthorizationFilter.doFilterInternal");


            String jwtToken = request.getHeader("Authorization");
            System.out.println("jwtHeader : " + jwtToken);

            //header 있는지 확인
            if (jwtToken == null || !jwtToken.startsWith("Bearer")) {
                chain.doFilter(request, response);
                return;
            }

            if (jwtProvider.validateAccessToken(jwtToken)) {
                //access라면
                System.out.println("ACCESS TOKEN!!");

                String userEmail = jwtProvider.getUserEmail(jwtToken);
                MemberDetails memberDetails = new MemberDetails(memberRepository.findByEmail(userEmail));

                //JWT 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberDetails, null, memberDetails.getAuthorities());

                // 강제로 시큐리티의 세션에 접근하여 Authentication 객체 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request, response);

            } else if (jwtProvider.validateRefreshToken(jwtToken)) {
                //refresh라면
                System.out.println("REFRESH TOKEN!!");
                jwtToken = jwtToken.replace("Bearer ","");
                String accessToken = jwtProvider.reCreateAccessToken(jwtToken);
                JwtToken token = JwtToken.builder().grantType("Bearer ").accessToken(accessToken).refreshToken(jwtToken).build();
                response.getWriter().write(new ObjectMapper().writeValueAsString(token));
            }
        }
        catch (JWTVerificationException e){
            throw new JWTVerificationException(e.getMessage());
        }
        catch (ExpiredJwtException e){
            throw new ExpiredJwtException(null,null,e.getMessage());
        }
    }


}
