package site.pangarm.backend.global.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtToken {

    private final String accessToken;

    private final String refreshToken;

    private JwtToken(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static JwtToken of(String accessToken, String refreshToken){
        return new JwtToken(accessToken, refreshToken);
    }
}
