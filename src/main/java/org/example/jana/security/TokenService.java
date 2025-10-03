package org.example.jana.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;

public class TokenService {
    private static final String secret = "minhaChaveSecretaSuperSecretaComTamanhoSuficiente123!";
    Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));


    public String generateToken(int userId){
        return Jwts.builder().setIssuedAt(Date.from(Instant.now()))
                .setSubject(String.valueOf(userId))
                .setExpiration(Date.from(Instant.now().plusMillis(1000 * 60 * 60 * 5)))
                .signWith(key)
                .compact();
    }
    public int verifyToken(String token){
        Jws<Claims> claimsClaims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        return Integer.parseInt(claimsClaims.getBody().getSubject());
    }
}
