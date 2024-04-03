package site.pangarm.backend.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.pangarm.backend.application.dto.request.MemberSignInRequest;
import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.ErrorResponse;
import site.pangarm.backend.global.jwt.TokenProvider;
import site.pangarm.backend.global.jwt.JwtToken;

import java.io.IOException;

@Slf4j
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final ObjectMapper objectMapper;

    public AuthenticationFilter(AuthenticationManager authenticationManager, TokenProvider tokenProvider, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/member/signin");
    }

    // login 요청을 하면 회원 정보 확인을 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("start attemptAuthentication");
        try{
            try {
                MemberSignInRequest signInRequest = objectMapper.readValue(request.getInputStream(), MemberSignInRequest.class);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword());

                // DetailService의 loadUserByUsername()이 실행됨.
                Authentication authentication = authenticationManager.authenticate(authenticationToken);
                log.info("authentication: " + authentication);
                return authentication;
            } catch (AuthenticationException e) {
                unsuccessfulAuthentication(request,response,e);
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException{
        //JWT 반환
        log.info("successfulAuthentication : 인증 완료");
        JwtToken token = tokenProvider.createToken(authentication);
        log.info("accessToken : {}, refreshToken : {}",token.getAccessToken(),token.getRefreshToken());
        response.addHeader(AUTHORIZATION_HEADER,objectMapper.writeValueAsString(token));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.error("unsuccessfulAuthentication : 인증 실패");
        ErrorResponse error =
                ErrorResponse.builder()
                        .errorMessage(ErrorCode.API_ERROR_NOT_FOUND.getMessage())
                        .businessCode(ErrorCode.API_ERROR_NOT_FOUND.getCode())
                        .build();
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
