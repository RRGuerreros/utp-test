package com.utp.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class UtpInfrastructureApplication{

	public static void main(String[] args) {
		SpringApplication.run(UtpInfrastructureApplication.class, args);
	}
}
