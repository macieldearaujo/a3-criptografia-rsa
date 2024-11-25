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

    public EncryptRequest criptografar(EncryptRequest request) throws Exception {
        String userCriptografado = criptografarCampo(request.getUser());
        String passwordCriptografado = criptografarCampo(request.getPassword());

        return new EncryptRequest(userCriptografado, passwordCriptografado);
    }

    private String criptografarCampo(String campo) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] dadosCriptografados = cipher.doFinal(campo.getBytes());
        return Base64.getEncoder().encodeToString(dadosCriptografados);
    }

    public EncryptRequest descriptografar(EncryptRequest request) throws Exception {
        String userDescriptografado = descriptografarCampo(request.getUser());
        String passwordDescriptografado = descriptografarCampo(request.getPassword());

        return new EncryptRequest(userDescriptografado, passwordDescriptografado);
    }

    private String descriptografarCampo(String campoCriptografado) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] dadosDecodificados = Base64.getDecoder().decode(campoCriptografado);
        byte[] dadosDescriptografados = cipher.doFinal(dadosDecodificados);
        return new String(dadosDescriptografados);
    }
}
