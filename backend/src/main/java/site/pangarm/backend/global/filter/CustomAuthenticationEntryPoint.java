package site.pangarm.backend.global.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import site.pangarm.backend.domain.auth.AuthException;
import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HandlerExceptionResolver resolver;

    public CustomAuthenticationEntryPoint(
            @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) {
        log.error("entryPoint: {}",authException.getMessage());
        resolver.resolveException(
                request,
                response,
                null,
                AuthException.of(ErrorCode.API_ERROR_AUTH_BY_AUTHORIZATION_IS_NECESSARY));
    }
}