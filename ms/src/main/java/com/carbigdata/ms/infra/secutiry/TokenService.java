package com.carbigdata.ms.infra.secutiry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.carbigdata.ms.domain.client.entities.Client;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    private static String SERVICE = "ms-service";
    public String generateToken(Client user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                .withIssuer(SERVICE)
                .withSubject(user.getCpf())
                .withExpiresAt(this.generateExpirationDate())
                .sign(algorithm);

            return token;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while aithentication");
        }   
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);


            return JWT.require(algorithm)
            .withIssuer(SERVICE)
            .build()
            .verify(token)
            .getSubject();

        } catch (JWTVerificationException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(4).toInstant( ZoneOffset.of("-03:00"));
    }
}
