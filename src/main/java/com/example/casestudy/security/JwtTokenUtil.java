package com.example.casestudy.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${app.jwt.secret}")
    private String secret;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
            .build();
        return verifier.verify(token).getSubject();
    }

    private Map<String, Claim> getAllClaimsFromToken(String token) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
            .build();

        DecodedJWT decoded = verifier.verify(token);
        return decoded.getClaims();
    }

    //check if the token has expired
    public Boolean isTokenExpired(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getExpiresAt()
            .before(new Date());
    }

    //generate token for user
    public String generateToken(SessionUserDetail userDetails) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        return doGenerateToken(claims, userDetails.getUsername(), userDetails.getUuid().toString());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String jti) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer(claims.toString())
            .withSubject(subject)
            .withIssuedAt(new Date(System.currentTimeMillis()))
            .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
            .sign(algorithm);

    }

    public Boolean validateToken(String token, UserDetails userDetails) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
            .build();
        DecodedJWT decoded = verifier.verify(token);
        return (decoded.getSubject().equals(userDetails.getUsername()));
    }
}