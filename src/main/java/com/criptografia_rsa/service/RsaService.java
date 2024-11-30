package com.criptografia_rsa.service;

import com.criptografia_rsa.entity.EncryptRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.util.Base64;
import java.util.Date;

@Service
public class RsaService {

    public static final Logger logger = LoggerFactory.getLogger(RsaService.class);

    public final KeyPair keyPair;

    public RsaService(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    public EncryptRequest criptografar(EncryptRequest request) throws Exception {
        long initialTime = new Date().getTime();

        String userCriptografado = criptografarCampo(request.getUser());
        String passwordCriptografado = criptografarCampo(request.getPassword());

        long executionTime = new Date().getTime() - initialTime;
        logger.info("Usuário criptografado com sucesso! Tempo de execução total: {}ms.", executionTime);

        return new EncryptRequest(userCriptografado, passwordCriptografado);
    }

    private String criptografarCampo(String campo) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] dadosCriptografados = cipher.doFinal(campo.getBytes());
        return Base64.getEncoder().encodeToString(dadosCriptografados);
    }

    public EncryptRequest descriptografar(EncryptRequest request) throws Exception {
        long initialTime = new Date().getTime();

        String userDescriptografado = descriptografarCampo(request.getUser());
        String passwordDescriptografado = descriptografarCampo(request.getPassword());

        long executionTime = new Date().getTime() - initialTime;
        logger.info("Usuário descriptografado com sucesso! Tempo de execução total: {}ms.", executionTime);

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
