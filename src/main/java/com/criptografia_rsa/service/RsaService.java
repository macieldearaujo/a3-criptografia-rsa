package com.criptografia_rsa.service;

import com.criptografia_rsa.entity.EncryptRequest;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.util.Base64;

public class RsaService {

    public final KeyPair keyPair;

    public RsaService(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String criptografar(String dados) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] dadosCriptografados = cipher.doFinal(dados.getBytes());
        return Base64.getEncoder().encodeToString(dadosCriptografados);
    }
}
