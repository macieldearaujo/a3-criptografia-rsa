package com.criptografia_rsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CriptografiaRsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriptografiaRsaApplication.class, args);
	}

}