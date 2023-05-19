package com.colegios_peruanos.conectados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class ConectadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConectadosApplication.class, args);
	}

	@GetMapping
	String Iniciar(){
		return "index";
	}

}
