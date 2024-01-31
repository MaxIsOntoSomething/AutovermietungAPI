package com.SalerProjekt.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.*;
import java.security.Key;
import java.util.function.Function;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public String generateToken(UserDetails userdetails) {
        return generateToken(new HashMap<>(), userdetails);
    }
    public boolean isTokenValid(String token, UserDetails userdetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userdetails.getUsername()) && !isTokenExpired(token));
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private String generateToken(Map<String, Object> extraClaims, UserDetails userdetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userdetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    private String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userdetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userdetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("Z3JIRlhxOGElN3I0cHV5JkB0TnA=");
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
