package site.pangarm.backend.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtProvider {

    @Value("${spring.jwt.accessExpTime}")
    long accessExpTime;

    @Value("${spring.jwt.refreshExpTime}")
    long refreshExpTime;

    private final SecretKey SECRET_KEY;

    public JwtProvider(@Value("${spring.jwt.secret}")String key) {

        this.SECRET_KEY = new SecretKeySpec(key.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }

    public String createAccessToken(String email, Collection<? extends GrantedAuthority> role) {
        String accessToken = Jwts.builder()
                .claim("email",email)
                .claim("type","access")
                .claim("role",role)
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpTime))
                .signWith(SECRET_KEY)
                .compact();
        return accessToken;
    }

    public String reCreateAccessToken(String refreshToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(refreshToken)
                .getBody();

        String email = (String) claims.get("email");
        return createAccessToken(email, (Collection<? extends GrantedAuthority>) claims.get("role"));
    }

    public String createRefreshToken(String email, Collection<? extends GrantedAuthority> role) {
        String refreshToken = Jwts.builder()
                .claim("email",email)
                .claim("type","refresh")
                .claim("role",role)
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpTime))
                .signWith(SECRET_KEY)
                .compact();
        return refreshToken;
    }

    public Boolean validateAccessToken(String accessToken) {
        log.info("access check");
        accessToken = accessToken.replace("Bearer ","");
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
        String type = (String)claims.get("type");
        return "access".equals(type);
    }

//    public Boolean validateRefreshToken(String refreshToken) {
//        System.out.println("refresh check");
//        refreshToken = refreshToken.replace("Bearer ","");
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(SECRET_KEY)
//                .build()
//                .parseClaimsJws(refreshToken)
//                .getBody();
//        String type = (String)claims.get("type");
//        if (type.equals("refresh")) {
//            System.out.println("create refresh");
//           ValueOperations<String, String> stringValueOperations = redisTemplate.opsForValue();
//            String redisValue = stringValueOperations.get(String.valueOf(claims.get("id")));
//            if (redisValue != null) {
//                return claims.getExpiration().after(new Date());
//            }
//        }
//        System.out.println("failed");
//        return false;
//    }

    public int getUserEmail(String jwt) {
        jwt = jwt.replace("Bearer ","");
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return (int)claims.get("email");
    }

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
