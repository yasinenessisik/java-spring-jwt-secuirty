package com.javaspringjwt.javaspringjwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.javaspringjwt.javaspringjwt.exception.GenericException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class TokenGenerator {

    @Value("${jwt-variables.key}")
    private String KEY;
    @Value("${jwt-variables.key}")
    private String ISSUER;
    @Value("${jwt-variables.expries_access_minute}")
    private int expries_access_minute;


    public String generateToken(Authentication authentication){
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis()+ (expries_access_minute * 60 *1000)))
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(KEY.getBytes()));
    }

    public DecodedJWT verifyToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try{
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT;
        }catch(Exception e){
            throw GenericException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
        }

    }


}
