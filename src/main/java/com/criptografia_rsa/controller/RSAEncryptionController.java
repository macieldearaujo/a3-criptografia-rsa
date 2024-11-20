package com.criptografia_rsa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rsa")
public class RSAEncryptionController {

    @PostMapping("/criptografar")
    public ResponseEntity<Encry> criptografar(@ResponseBody EncryptRequest request) {

    }

    @PostMapping("/descriptografar")
    public String descriptografar() {

    }
}
