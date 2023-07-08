package com.colegios_peruanos.conectados.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.colegios_peruanos.conectados.modelos.Curso;
import com.colegios_peruanos.conectados.modelos.Docente;
import com.colegios_peruanos.conectados.modelos.Estudiante;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.servicio.gradoServicio;
import com.colegios_peruanos.conectados.servicio.cursoServicio;
import com.colegios_peruanos.conectados.servicio.docenteServicio;
import com.colegios_peruanos.conectados.servicio.estudianteServicio;

@Controller
public class controllerPrincipal {

	@Autowired
	private gradoServicio gradoservicio;

	@Autowired
	private cursoServicio cursoServicio;

	@Autowired
	private docenteServicio docenteServicio;

	@Autowired
	private estudianteServicio estudianteServicio;

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

	@GetMapping("/asignarAulaDocente")
	String asignarAulaDocente(Model model) {

		List<Curso> cursos = cursoServicio.listar();
		model.addAttribute("cursos", cursos);
		List<Docente> docentes = docenteServicio.listar();
		model.addAttribute("docentes", docentes);
		return "/asignarAulaDocente";
	}

	@GetMapping("/asignarAulaAlumno")
	String asignarAulaAlumno(Model model) {

		List<Curso> cursos = cursoServicio.listar();
		model.addAttribute("cursos", cursos);

		List<Estudiante> estudiantes = estudianteServicio.listar();
		model.addAttribute("estudiantes", estudiantes);
		return "/asignarAulaAlumno";
	}

	@GetMapping("/estudiante/guardarUserEstudiante")
	String guardarUserEstudiante() {
		return "/estudiante/guardarUserEstudiante";
	}

}
