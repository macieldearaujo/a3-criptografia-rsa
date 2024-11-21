package com.criptografia_rsa.controller;

import com.criptografia_rsa.entity.EncryptRequest;
import com.criptografia_rsa.service.RsaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rsa")
public class RsaController {

    private RsaService rsaService;

    @PostMapping("/criptografar")
    public ResponseEntity<EncryptRequest> criptografar(@RequestBody EncryptRequest request) {
        try {
            String dadosCriptografados = rsaService.criptografar(request.getData());
            return ResponseEntity.ok(new EncryptRequest(dadosCriptografados));
        } catch(Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
