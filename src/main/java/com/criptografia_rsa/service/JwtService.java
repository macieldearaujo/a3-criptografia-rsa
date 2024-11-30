package com.criptografia_rsa.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    public static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    public final KeyPair keyPair;

    public JwtService(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String gerarToken(String user, String senha) throws Exception {
        long initialTime = new Date().getTime();

        String payload = String.format("{\"user\":\"%s\", \"senha\":\"%s\"}", user, senha);

        String token = Jwts.builder()
                .setSubject("autenticacao")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .claim("data", payload)
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();

        long executionTime = new Date().getTime() - initialTime;
        logger.info("Token gerado com sucesso! Tempo de execução total: {}ms.", executionTime);

        return token;
    }

    public Map<String, Object> validarTokenDetalhado(String token) {
        long initialTime = new Date().getTime();

        Map<String, Object> response = new HashMap<>();
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(keyPair.getPublic())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            response.put("valid", true);
            response.put("subject", claims.getSubject());
            response.put("issuedAt", claims.getIssuedAt());
            response.put("expiration", claims.getExpiration());

            long executionTime = new Date().getTime() - initialTime;
            logger.info("Token validado com sucesso! Tempo de execução total: {}ms.", executionTime);
        } catch (JwtException e) {
            response.put("valid", false);
            response.put("error", e.getMessage());
        }
        return response;
    }
}
