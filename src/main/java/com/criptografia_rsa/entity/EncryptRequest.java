package com.criptografia_rsa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EncryptRequest {
    private String data;
}
