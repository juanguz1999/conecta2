package com.colegios_peruanos.conectados.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controllerPrincipal {
	@GetMapping("/")
	String Index() {
		return "index";
	}

	@GetMapping("/vistaprincipal")
	String vistaprincipal() {
		return "vistaprincipal";
	}

	@GetMapping("/vistaprincipalalumno")
	String vistaprincipalalumno() {
		return "estudiante/vistaprincipalalumno";
	}

	@GetMapping("/vistaprincipaldocente")
	String vistaprincipaldocente() {
		return "docente/vistaprincipaldocente";
	}

	@GetMapping("/prueba")
	String prueba() {
		return "prueba";
	}

	@GetMapping("/perfil")
	String perfil() {
		return "perfil";
	}

	// @GetMapping("/administrarPerfil")
	// String administrarPerfil(){
	// return "administrarPerfil";
	// }

	@GetMapping("/guardarUser")
	String guardarUser() {
		return "guardarUser";
	}

	@GetMapping("/verificaUser")
	String verificaUser() {
		return "verificaUser";
	}

}
