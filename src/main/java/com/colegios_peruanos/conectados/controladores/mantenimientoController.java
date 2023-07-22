package com.colegios_peruanos.conectados.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.colegios_peruanos.conectados.modelos.Mantenimiento;
import com.colegios_peruanos.conectados.modelos.Usuario;
import com.colegios_peruanos.conectados.modelos.AsignacionDocente;
import com.colegios_peruanos.conectados.modelos.AsignacionEstudiante;
import com.colegios_peruanos.conectados.servicio.asignacionDocenteServicio;
import com.colegios_peruanos.conectados.servicio.asignacionEstudianteServicio;
import com.colegios_peruanos.conectados.servicio.mantenimientoServicio;
import com.colegios_peruanos.conectados.servicio.usuarioServicio;

@Controller
public class mantenimientoController {

    @Autowired
    private mantenimientoServicio mantServ;

    @Autowired
    private usuarioServicio usuarioservicio;

    @Autowired
    private asignacionDocenteServicio asignacionDocenteServicio;

    @Autowired
    private asignacionEstudianteServicio asignacionEstudianteServicio;

    @GetMapping("/mantenimiento")
    public String ListaMantenimiento(Model model, HttpServletRequest request) {

        List<Mantenimiento> operaciones = mantServ.listar();
        model.addAttribute("operaciones", operaciones);
        model.addAttribute("url", getFullUrl(request));

        return "mantenimiento/administrarMantenimiento";

    }

    public String getFullUrl(HttpServletRequest request) {

        if (request.getQueryString() == null) {
            return request.getRequestURI();
        }

        return request.getRequestURI() + "?" + request.getQueryString();
    }

    @GetMapping("/agregarMantenimiento")
    public String agregarMantenimiento(Mantenimiento mantenimiento, Model model) {

        List<Usuario> usuariosadm = usuarioservicio.usuariosPorTipo("administracion");
        model.addAttribute("usuarios", usuariosadm);

        return "mantenimiento/editarMantenimiento";
    }

    @PostMapping("/guardarMantenimiento")
    public String guardarMantenimiento(Mantenimiento mantenimiento) {

        if (mantenimiento.getId() == null) {
            mantenimiento.setId(0);
            mantServ.guardar(mantenimiento);
            System.out.println("Se guardo nueva programación de mantenimiento");

        } else {
            mantServ.guardar(mantenimiento);
            System.out.println("Se modifico operación de mantenimiento");
        }

        return "redirect:mantenimiento";
    }

    @GetMapping("/editarMantenimiento/{id}")
    public String editarMantenimiento(Mantenimiento mantenimiento, Model model) {

        List<Usuario> usuariosadm = usuarioservicio.usuariosPorTipo("administracion");
        model.addAttribute("usuarios", usuariosadm);

        mantenimiento = mantServ.buscar(mantenimiento);
        model.addAttribute("mantenimiento", mantenimiento);

        return "mantenimiento/editarMantenimiento";
    }

    @GetMapping("/eliminarMantenimiento")
    public String eliminarMantenimiento(Mantenimiento mantenimiento) {

        mantServ.eliminar(mantenimiento);

        return "redirect:mantenimiento";
    }

    @PostMapping("/guardarAsignacionDocente")
    public String guardarAsignacionDocente(@RequestParam("docente") Integer docenteId,
            @RequestParam("curso") Integer cursoId) {
        // Crear una instancia de AsignacionDocente
        AsignacionDocente asignacionDocente = new AsignacionDocente();
        asignacionDocente.setDocenteID(docenteId);
        asignacionDocente.setCursoID(cursoId);

        // Guardar la asignacionDocente en la base de datos
        asignacionDocenteServicio.guardar(asignacionDocente);

        return "redirect:/vistaprincipal";
    }

    @PostMapping("/guardarAsignacionEstudiante")
    public String guardarAsignacionEstudiante(@RequestParam("estudiante") Integer estudianteId,
            @RequestParam("curso") Integer cursoId) {
        // Crear una instancia de AsignacionDocente
        AsignacionEstudiante asignacionEstudiante = new AsignacionEstudiante();
        asignacionEstudiante.setEstudianteID(estudianteId);
        asignacionEstudiante.setCursoID(cursoId);

        // Guardar la asignacionDocente en la base de datos
        asignacionEstudianteServicio.guardar(asignacionEstudiante);

        return "redirect:/vistaprincipal";
    }

    @GetMapping("/administrarAsignacionAlumnos")
    public String mostrarAsignacionesAlumnos(Model model, HttpServletRequest request) {
        List<AsignacionEstudiante> asignaciones = asignacionEstudianteServicio.listar(); // Obtener las asignaciones
                                                                                         // de alumnos a cursos
        model.addAttribute("asignaciones", asignaciones); // Agregar las asignaciones al modelo
        model.addAttribute("url", getFullUrl(request));
        return "administrarAsignacionAlumnos";
    }

}