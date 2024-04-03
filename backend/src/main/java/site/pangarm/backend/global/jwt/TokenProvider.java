package site.pangarm.backend.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import site.pangarm.backend.domain.auth.AuthException;
import site.pangarm.backend.global.error.ErrorCode;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String ACCESS = "access";
    private static final String REFRESH = "refresh";
    private static final String TOKENTYPE = "type";

    @Value("${spring.jwt.accessExpTime}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${spring.jwt.refreshExpTime}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    private final SecretKey SECRET_KEY;

    public TokenProvider(@Value("${spring.jwt.secret}")String key) {
        this.SECRET_KEY = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    public JwtToken createToken(Authentication auth){
        String authorities =
                auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(","));
        log.info("start createToken : {}",authorities);
        long now = (new Date().getTime());

        String accessToken = createAccessToken(auth.getName(),now,authorities);
        String refreshToken = createRefreshToken(auth.getName(),now,authorities);

        return JwtToken.of(accessToken,refreshToken);
    }

    private String createAccessToken(String userId, long now,String authorities) {
        return Jwts.builder()
                .setSubject(userId)
                .claim(AUTHORITIES_KEY, authorities)
                .claim(TOKENTYPE,ACCESS)
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    private String createRefreshToken(String userId, long now,String authorities) {
        return Jwts.builder()
                .setSubject(userId)
                .claim(AUTHORITIES_KEY, authorities)
                .claim(TOKENTYPE,REFRESH)
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Authentication getAuthentication(Claims claims) {
        List<SimpleGrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Claims validationAndparesClaims(String token) {
        try {
            token = token.replace("Bearer ","");
            Claims claims = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
            if (claims.get(AUTHORITIES_KEY) == null) {
                throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_AUTHORIZATION_INFORMATION);
            }
            return claims;
        }catch (SignatureException | SecurityException | MalformedJwtException e) {
            throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_JWT_SIGNATURE_INVALID);
        } catch (ExpiredJwtException e) {
            throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_JWT_KEY_EXPIERD);
        } catch (UnsupportedJwtException e) {
            throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_JWT_NOT_SUPPORT);
        } catch (IllegalArgumentException e) {
            throw AuthException.of(ErrorCode.API_ERROR_AUTH_BY_JWT_KEY_INVALID);
        }
    }

    public boolean validateAccessToken(Claims claims){
        return claims.get(TOKENTYPE).toString().equals(ACCESS);
    }

    public boolean validateRefreshToken(Claims claims){
        return claims.get(TOKENTYPE).toString().equals(REFRESH);
    }

    @Deprecated
    public String reCreateAccessToken(String refreshToken) {
        long now = (new Date().getTime());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        String userId = (String) claims.get("sub");
        return createAccessToken(userId, now,claims.get(AUTHORITIES_KEY).toString());
    }

    @Deprecated
    public List<String> getUserRole(String jwt) {
        jwt = jwt.replace("Bearer ","");
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return (List<String>)claims.get("role");
    }

}
