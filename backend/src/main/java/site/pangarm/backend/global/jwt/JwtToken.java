package site.pangarm.backend.global.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtToken {
    private String grantType;
    private final String accessToken;
    private final String refreshToken;
}
