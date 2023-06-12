package com.colegios_peruanos.conectados.controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.modelos.Seccion;
import com.colegios_peruanos.conectados.servicio.estudianteServicio;
import com.colegios_peruanos.conectados.servicio.gradoServicio;
import com.colegios_peruanos.conectados.servicio.seccionServicio;

@Controller
public class controladorEstudiante {
    
    @Autowired
    private gradoServicio gradoservicio;

    @Autowired
    private seccionServicio seccionservicio;

    @Autowired
    private estudianteServicio estudianteservicio;

    @GetMapping("/registrarEstudiante")
    String registroEstudiante(Model model){
        
        List<Grado> grados=gradoservicio.listar();
        model.addAttribute("grados", grados);
        return "administrador/registroEstudiante";
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


}
