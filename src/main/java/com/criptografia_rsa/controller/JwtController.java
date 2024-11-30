package com.criptografia_rsa.controller;

import com.criptografia_rsa.entity.JwtRequest;
import com.criptografia_rsa.entity.JwtResponse;
import com.criptografia_rsa.entity.JwtValidate;
import com.criptografia_rsa.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    private final JwtService jwtService;
    public static final Logger logger = LoggerFactory.getLogger(JwtController.class);

    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/auth/gerar")
    public ResponseEntity<JwtResponse> gerarToken(@RequestBody JwtRequest request) {
        try {
            String token = jwtService.gerarToken(request.getUser(), request.getSenha());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            logger.warn("Erro ao gerar chave token. Por favor, tente novamente.");
            return ResponseEntity.status(500).body(new JwtResponse("Erro ao gerar token"));
        }
    }

    @PostMapping("/auth/validar")
    public ResponseEntity<Map<String, Object>> validarToken(@RequestBody JwtValidate token) {
        Map<String, Object> response = jwtService.validarTokenDetalhado(token.getToken());
        if ((boolean) response.get("valid")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }
}
