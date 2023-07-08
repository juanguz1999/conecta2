package com.colegios_peruanos.conectados.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.colegios_peruanos.conectados.modelos.Curso;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.servicio.gradoServicio;
import com.colegios_peruanos.conectados.servicio.cursoServicio;

@Controller
public class controllerPrincipal {

	@Autowired
	private gradoServicio gradoservicio;

	@Autowired
	private cursoServicio cursoServicio;

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
	String asignarAula(Model model) {

		List<Curso> cursos = cursoServicio.listar();
		model.addAttribute("cursos", cursos);
		return "/asignarAula";
	}

	@GetMapping("/estudiante/guardarUserEstudiante")
	String guardarUserEstudiante() {
		return "/estudiante/guardarUserEstudiante";
	}

}
