package com.criptografia_rsa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EncryptResponse {
    private String encryptedUser;
    private String encryptedPassword;
}
