package com.colegios_peruanos.conectados.controladores;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mantenimientoController {
    
    @GetMapping("/mantenimiento")
	String vistaMantenimiento(){
		return "mantenimiento/administrarMantenimiento";
	}

}