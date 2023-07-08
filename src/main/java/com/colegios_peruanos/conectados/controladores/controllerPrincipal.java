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

	@GetMapping("/verificaUser")
	String verificaUser() {
		return "verificaUser";
	}

	@GetMapping("/guardarUser")
	String guardarUser() {
		return "guardarUser";
	}

	@GetMapping("/docente/guardarUserDocente")
	String guardarUserDocente() {
		return "/docente/guardarUserDocente";
	}

	@GetMapping("/asignarAula")
	String asignarAula() {
		return "/asignarAula";
	}

	@GetMapping("/estudiante/guardarUserEstudiante")
	String guardarUserEstudiante() {
		return "/estudiante/guardarUserEstudiante";
	}

}
