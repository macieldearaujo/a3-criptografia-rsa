package com.criptografia_rsa.controller;

import lombok.Getter;

@Getter
public class EncryptRequest {
    private String encryptedData;

    public EncryptRequest(String encryptedData) {
        this.encryptedData = encryptedData;
    }
}
