package com.gft.gerenciadordeeventos;

import com.gft.gerenciadordeeventos.model.Usuario;
import com.gft.gerenciadordeeventos.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GerenciadorDeEventosApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(GerenciadorDeEventosApplication.class, args);

		System.out.println((new BCryptPasswordEncoder().encode("123")));

	}

}
