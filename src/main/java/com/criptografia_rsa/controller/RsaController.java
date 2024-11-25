package com.criptografia_rsa.controller;

import com.criptografia_rsa.entity.EncryptRequest;
import com.criptografia_rsa.entity.EncryptResponse;
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
        try {
            EncryptRequest dadosCriptografados = rsaService.criptografar(request);
            return ResponseEntity.ok(dadosCriptografados);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/descriptografar")
    public ResponseEntity<EncryptRequest> descriptografar(@RequestBody EncryptRequest request) {
        try {
            EncryptRequest dadosDescriptografados = rsaService.descriptografar(request);
            return ResponseEntity.ok(dadosDescriptografados);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
