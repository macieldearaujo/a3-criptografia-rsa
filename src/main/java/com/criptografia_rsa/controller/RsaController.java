package com.criptografia_rsa.controller;

import com.criptografia_rsa.entity.EncryptRequest;
import com.criptografia_rsa.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rsa")
public class RsaController {

    private final RsaService rsaService;

    @Autowired
    public RsaController(RsaService rsaService) {
        this.rsaService = rsaService;
    }

    @PostMapping("/criptografar")
    public ResponseEntity<EncryptRequest> criptografar(@RequestBody EncryptRequest request) {
            String dadosCriptografados = rsaService.criptografar(request.getData());
            return ResponseEntity.ok(new EncryptRequest(dadosCriptografados));
    }
}
