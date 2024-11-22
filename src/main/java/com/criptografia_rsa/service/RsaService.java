package com.criptografia_rsa.service;

import com.criptografia_rsa.entity.EncryptRequest;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class RsaService {

    public final KeyPair keyPair;

    public RsaService(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public String criptografar(String dados) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
            byte[] dadosCriptografados = cipher.doFinal(dados.getBytes());

            return Base64.getEncoder().encodeToString(dadosCriptografados);
        } catch (NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException("Erro ao criptografar dados. Detalhes: " + e.getMessage());
        }
    }
}
