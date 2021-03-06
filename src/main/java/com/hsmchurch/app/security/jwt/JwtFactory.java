package com.hsmchurch.app.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsmchurch.app.account.domain.Role;
import com.hsmchurch.app.security.oauth.PostAuthorizationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
public class JwtFactory {

    @Autowired
    private ObjectMapper objectMapper;

    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public Long getIdFromToken(String token) {
        return Long.valueOf(getClaimFromToken(token, Claims::getSubject));
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Role getUserRole(String token) {
        return Role.valueOf(getClaimFromToken(token, "authority"));
    }

    public String getClaimFromToken(String token, String claimName) {
        final Claims claims = getAllClaimsFromToken(token);
        log.info("auth token : {}", claims.get(claimName, String.class));
        return claims.get(claimName, String.class);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(clock.now());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(Authentication auth) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        log.info("roles : {}", auth.getAuthorities());
        return Jwts.builder()
                .setIssuer(issuer)
                .setSubject(String.valueOf(((PostAuthorizationToken)auth).getId()))
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .claim("userId", ((PostAuthorizationToken) auth).getUserName())
                .claim("authority", ((List)auth.getAuthorities()).get(0).toString())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean isValidateToken(String token) {
        log.info("request jwt token : {}", token);
        final Long id = getIdFromToken(token);
        if (id == null) {
            throw new RuntimeException("토큰의 계정 이름이 담겨있지 않습니다.");
        }
        if (isTokenExpired(token)) {
            throw new RuntimeException("토큰이 만료되었습니다.");
        }
        return true;
    }

    private Date calculateExpirationDate(Date createdDate) {
        log.info("date is null? : {}", createdDate);
        return new Date(createdDate.getTime() + expiration * 1000);
    }
}
