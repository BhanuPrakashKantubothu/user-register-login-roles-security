package com.example.springSecurity6.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static String SecretKey = "djbehjwbcehbcbcug7y87t26c76cv676g27v7c2ccuh873y2872csg7nshbhhshhh";
    private static long jwtExpirationDate = 3600000;


    public static String generateToken(Authentication authentication) {
        String token = Jwts.builder().
                setSubject(authentication.getName()).
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime() + jwtExpirationDate)).
                signWith(key(), SignatureAlgorithm.HS256).
                compact();

        return token;

    }

        public String getUsernameFromToken(String token) {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
    }




    private static Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SecretKey));
    }


    public boolean validateToken(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        Date expiration = claims.getExpiration();
        return !expiration.before(new Date());
    }
}
