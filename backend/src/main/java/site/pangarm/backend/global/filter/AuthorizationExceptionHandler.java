package site.pangarm.backend.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import site.pangarm.backend.domain.auth.AuthException;
import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.ErrorResponse;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
public class AuthorizationExceptionHandler extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 다음 filter Chain에 대한 실행 (filter-chain의 마지막에는 Dispatcher Servlet이 실행된다.)
            log.info("start AuthorizationExceptionHandler");
            filterChain.doFilter(request, response);
        } catch (BusinessException e){
            log.error("authorizationFail : {}",e.getMessage());
            ErrorResponse error =
                    ErrorResponse.builder()
                            .errorMessage(e.getErrorCode().getMessage())
                            .businessCode(e.getErrorCode().getCode())
                            .build();
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(error));
        }catch (Exception e){
            log.error("authorizationFail : {}",e.getMessage());
            ErrorResponse error =
                    ErrorResponse.builder()
                            .errorMessage(e.getMessage())
                            .build();
            response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(error));
        }
    }

}
