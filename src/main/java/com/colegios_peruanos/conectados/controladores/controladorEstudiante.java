package com.colegios_peruanos.conectados.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.colegios_peruanos.conectados.modelos.Asistencia;
import com.colegios_peruanos.conectados.modelos.Estudiante;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.modelos.Seccion;
import com.colegios_peruanos.conectados.servicio.asistenciaServicio;
import com.colegios_peruanos.conectados.servicio.estudianteServicio;
import com.colegios_peruanos.conectados.servicio.gradoServicio;

@Controller
public class controladorEstudiante {

    @Autowired
    private gradoServicio gradoservicio;

    @Autowired
    private estudianteServicio estudianteservicio;

    @Autowired
    private asistenciaServicio asistenciaservicio;

    @GetMapping("/registrarEstudiante")
    String registroEstudiante(Model model) {

        List<Grado> grados = gradoservicio.listar();
        model.addAttribute("grados", grados);
        return "docente/asistenciaEstudiante";
    }

    @GetMapping("/obtenerSecciones")
    @ResponseBody
    public List<Seccion> obtenerSecciones(@RequestParam("gradoId") Integer gradoId) {
        Grado grado = gradoservicio.buscar(gradoId);
        if (grado != null) {
            return grado.getSeccionList();
        }
        return new ArrayList<>();
    }

    @GetMapping("/obtenerEstudiantes")
    @ResponseBody
    public List<Estudiante> obtenerEstudiantesPorGradoYSeccion(@RequestParam("gradoId") Integer gradoId,
            @RequestParam("seccionId") Integer seccionId) {

        if (gradoId != null && seccionId != null) {
            return estudianteservicio.buscarGradoSeccion(gradoId, seccionId);
        }
        return new ArrayList<>();
    }

    @GetMapping("/obtenerAsistencia")
    @ResponseBody
    public Asistencia obtenerAsistenciaporEstudianteIdyfecha(@RequestParam("estudianteId") Integer estudianteId) {

        if (estudianteId != null) {
            return asistenciaservicio.asistenciaporEstudianteIdAndFecha(estudianteId);
        }
        return new Asistencia();
    }

    @GetMapping("/obtenerAsistenciasEstudiante")
    public List<Asistencia> obtenerAsistenciaporEstudiante(@RequestParam("estudianteId") Integer estudianteId) {

        if (estudianteId != null) {
            return asistenciaservicio.asistenciaporEstudianteId(estudianteId);
        }
        return new ArrayList<>();
    }

    @PostMapping("/guardarAsistencia")
    @ResponseBody
    public String guardarAsistencias(@RequestBody List<Map<String, Object>> mapAsistencias) {
        Date fechaAsistencia = new Date();
        for (Map<String, Object> mapAsistencia : mapAsistencias) { 

            String estudianteID = (String) mapAsistencia.get("estudianteID"); // Cambio a String
            String estadoAsistencia = (String) mapAsistencia.get("estadoAsistencia");
            String observaciones = (String) mapAsistencia.get("observaciones");

            Asistencia existente = asistenciaservicio.asistenciaporEstudianteIdAndFecha(Integer.parseInt(estudianteID));
            if (existente != null) {
                // Actualizar la asistencia existente
                existente.setEstadoAsistencia(estadoAsistencia);
                existente.setObservaciones(observaciones);
                asistenciaservicio.guardar(existente);
            } else {
                // Crear una nueva asistencia
                Asistencia asistencia = new Asistencia();
                asistencia.setFechaAsistencia(fechaAsistencia);
                asistencia.setEstadoAsistencia(estadoAsistencia);
                asistencia.setObservaciones(observaciones);
                asistencia.setEstudianteID(estudianteservicio.buscar(Integer.parseInt(estudianteID)));
                asistenciaservicio.guardar(asistencia);
            }
        }
        return "Datos recibidos correctamente";
    }

    @PostMapping("/guardarAsiEstu")
    public String guardarAsiEstu() {

        return "redirect:registrarEstudiante";
    }




}