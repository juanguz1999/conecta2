package com.colegios_peruanos.conectados.controladores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import com.colegios_peruanos.conectados.modelos.Asistencia;
import com.colegios_peruanos.conectados.modelos.Calificacion;
import com.colegios_peruanos.conectados.modelos.Curso;
import com.colegios_peruanos.conectados.modelos.Estudiante;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.modelos.Seccion;
import com.colegios_peruanos.conectados.servicio.asistenciaServicio;
import com.colegios_peruanos.conectados.servicio.seccionServicio;
import com.colegios_peruanos.conectados.servicio.calificacionServicio;
import com.colegios_peruanos.conectados.servicio.cursoServicio;
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

    @Autowired
    private cursoServicio cursoservicio;

    @Autowired
    private calificacionServicio calificacionservicio;

    @Autowired
    private seccionServicio seccionservicio;

    @GetMapping("/registrarEstudiante")
    String registroEstudiante(Model model) {

        List<Grado> grados = gradoservicio.listar();
        model.addAttribute("grados", grados);
        return "docente/asistenciaEstudiante";
    }

    @GetMapping("/registrarNotas")
    String registroNotasEstudiante(Model model) {

        List<Grado> grados = gradoservicio.listar();
        model.addAttribute("grados", grados);
        return "docente/registroNotas";
    }

    @GetMapping("/obtenerCalificaciones")
    @ResponseBody
    public Map<String, Map<String, Object>> obtenerCalificaciones(@RequestParam("gradoId") Integer gradoId,
            @RequestParam("seccionId") Integer seccionId, @RequestParam("cursoId") Integer cursoId) {

        List<Estudiante> estudiantes = estudianteservicio.buscarEstudiantesCurso(gradoId, seccionId, cursoId);
        // Crear el diccionario
        Map<String, Map<String, Object>> diccionarioPrincipal = new HashMap<>();

        for (Estudiante estudiante : estudiantes) {
            Map<String, Object> diccionario = new HashMap<>();

            List<Calificacion> calificaciones = estudiante.getCalificacionList();

            for (Calificacion calificacion : calificaciones) {
                if (calificacion.getTipo().equals("PC1")) {
                    diccionario.put("PC1", calificacion.getValorCalificacion());
                }
                if (calificacion.getTipo().equals("PC2")) {
                    diccionario.put("PC2", calificacion.getValorCalificacion());
                }
                if (calificacion.getTipo().equals("PC3")) {
                    diccionario.put("PC3", calificacion.getValorCalificacion());
                }
                if (calificacion.getTipo().equals("EXFINAL")) {
                    diccionario.put("EXFINAL", calificacion.getValorCalificacion());
                }
            }
            diccionarioPrincipal.put(estudiante.getId().toString(), diccionario);
        }

        return diccionarioPrincipal;
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

    @GetMapping("/obtenerCursos")
    @ResponseBody
    public List<Curso> obtenerCursosPorGradoYSeccion(@RequestParam("gradoId") Integer gradoId,
            @RequestParam("seccionId") Integer seccionId) {

        if (gradoId != null && seccionId != null) {
            return cursoservicio.buscarGradoSeccion(gradoId, seccionId);
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

    @GetMapping("/obtenerEstudiantesCurso")
    @ResponseBody
    public List<Estudiante> obtenerEstudiantesPorGradoYSeccionYcurso(@RequestParam("gradoId") Integer gradoId,
            @RequestParam("seccionId") Integer seccionId, @RequestParam("cursoId") Integer cursoId) {

        if (gradoId != null && seccionId != null && cursoId != null) {
            return estudianteservicio.buscarEstudiantesCurso(gradoId, seccionId, cursoId);
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

    @PostMapping("/guardarNotasEstudiante")
    @ResponseBody
    public String guardarNotasEstudiante(@RequestBody List<Map<String, Object>> mapCalificaciones) {

        for (Map<String, Object> mapCalificacion : mapCalificaciones) {

            String estudianteID = (String) mapCalificacion.get("estudianteID"); // Cambio a String
            String PC1 = (String) mapCalificacion.get("PC1");
            String PC2 = (String) mapCalificacion.get("PC2");
            String PC3 = (String) mapCalificacion.get("PC3");
            String EXFINAL = (String) mapCalificacion.get("EXFINAL");
            String gradoId = (String) mapCalificacion.get("gradoId");
            String seccionId = (String) mapCalificacion.get("seccionId");
            String cursoId = (String) mapCalificacion.get("cursoId");

            Calificacion calificacionPC1 = calificacionservicio.buscarPorCursoEstudiante(Integer.parseInt(cursoId),
                    Integer.parseInt(estudianteID), "PC1");
            Calificacion calificacionPC2 = calificacionservicio.buscarPorCursoEstudiante(Integer.parseInt(cursoId),
                    Integer.parseInt(estudianteID), "PC2");
            Calificacion calificacionPC3 = calificacionservicio.buscarPorCursoEstudiante(Integer.parseInt(cursoId),
                    Integer.parseInt(estudianteID), "PC3");
            Calificacion calificacionFinal = calificacionservicio.buscarPorCursoEstudiante(Integer.parseInt(cursoId),
                    Integer.parseInt(estudianteID), "EXFINAL");

            if (calificacionPC1 != null) {
                calificacionPC1.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(PC1)));
                calificacionservicio.guardar(calificacionPC1);
            } else {
                Calificacion nuevaCalificacionPC1 = new Calificacion();
                nuevaCalificacionPC1.setTipo("PC1");
                nuevaCalificacionPC1.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(PC1)));
                nuevaCalificacionPC1.setEstudianteID(estudianteservicio.buscar(Integer.parseInt(estudianteID)));
                nuevaCalificacionPC1.setCursoID(cursoservicio.buscar(Integer.parseInt(cursoId)));
                calificacionservicio.guardar(nuevaCalificacionPC1);
            }

            if (calificacionPC2 != null) {
                calificacionPC2.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(PC2)));
                calificacionservicio.guardar(calificacionPC2);
            } else {
                Calificacion nuevaCalificacionPC2 = new Calificacion();
                nuevaCalificacionPC2.setTipo("PC2");
                nuevaCalificacionPC2.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(PC2)));
                nuevaCalificacionPC2.setEstudianteID(estudianteservicio.buscar(Integer.parseInt(estudianteID)));
                nuevaCalificacionPC2.setCursoID(cursoservicio.buscar(Integer.parseInt(cursoId)));
                calificacionservicio.guardar(nuevaCalificacionPC2);
            }

            if (calificacionPC3 != null) {
                calificacionPC3.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(PC3)));
                calificacionservicio.guardar(calificacionPC3);
            } else {
                Calificacion nuevaCalificacionPC3 = new Calificacion();
                nuevaCalificacionPC3.setTipo("PC3");
                nuevaCalificacionPC3.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(PC3)));
                nuevaCalificacionPC3.setEstudianteID(estudianteservicio.buscar(Integer.parseInt(estudianteID)));
                nuevaCalificacionPC3.setCursoID(cursoservicio.buscar(Integer.parseInt(cursoId)));
                calificacionservicio.guardar(nuevaCalificacionPC3);
            }

            if (calificacionFinal != null) {
                calificacionFinal.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(EXFINAL)));
                calificacionservicio.guardar(calificacionFinal);
            } else {
                Calificacion nuevaCalificacionFinal = new Calificacion();
                nuevaCalificacionFinal.setTipo("EXFINAL");
                nuevaCalificacionFinal.setValorCalificacion(BigDecimal.valueOf(Double.parseDouble(EXFINAL)));
                nuevaCalificacionFinal.setEstudianteID(estudianteservicio.buscar(Integer.parseInt(estudianteID)));
                nuevaCalificacionFinal.setCursoID(cursoservicio.buscar(Integer.parseInt(cursoId)));
                calificacionservicio.guardar(nuevaCalificacionFinal);
            }

        }
        return "Datos recibidos correctamente";
    }

    @PostMapping("/guardarNotas")
    public String guardarNotas() {

        return "redirect:registrarNotas";
    }

    @PostMapping("/guardarGradoSeccionEs")
    public ResponseEntity<String> guardarGradoSeccionEs(@RequestParam("estudianteId") Integer estudianteId,
            @RequestParam("gradoId") Integer gradoId,
            @RequestParam("seccionId") Integer seccionId) {
        try {
            Estudiante estudiante = estudianteservicio.buscar(estudianteId);
            Grado grado = gradoservicio.buscar(gradoId);
            Seccion seccion = seccionservicio.buscar(seccionId);

            if (estudiante != null && grado != null && seccion != null) {
                estudiante.setGradoID(grado);
                estudiante.setSeccionID(seccion);
                estudianteservicio.guardar(estudiante);
                return ResponseEntity.ok("Grado y sección asignados correctamente al estudiante.");
            } else {
                return ResponseEntity.badRequest()
                        .body("No se encontró el estudiante, grado o sección. Verifica los IDs proporcionados.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor al asignar grado y sección al estudiante.");
        }
    }

}
