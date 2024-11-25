package com.criptografia_rsa.controller;

import com.criptografia_rsa.entity.JwtResponse;
import com.criptografia_rsa.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
public class JwtController {

    private final RsaService rsaService;

    @Autowired
    public JwtController(RsaService rsaService) {
        this.rsaService = rsaService;
    }

    @PostMapping("/gerar")
    public ResponseEntity<JwtResponse> gerarToken(@RequestParam String subject) {
        try {
            String token = rsaService.gerarToken(subject);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new JwtResponse("Erro ao gerar token"));
        }
    }

    @GetMapping("/validar")
    public ResponseEntity<String> validarToken(@RequestParam String token) {
        boolean valido = rsaService.validarToken(token);
        if (valido) {
            return ResponseEntity.ok("Token válido");
        } else {
            return ResponseEntity.status(401).body("Token inválido");
        }
    }
}
