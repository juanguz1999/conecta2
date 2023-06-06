package com.colegios_peruanos.conectados.controladores;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date; 
import com.colegios_peruanos.conectados.modelos.Mantenimiento;
import com.colegios_peruanos.conectados.servicio.mantenimientoServicio;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
public class mantenimientoController {
    
	@Autowired
	private mantenimientoServicio mantServ;

    @GetMapping("/mantenimiento")
	String ListaMantenimiento(Model model){

		List<Mantenimiento> operaciones= mantServ.listar(); 
		model.addAttribute("operaciones", operaciones);
		return "mantenimiento/administrarMantenimiento";
		
	}

	@GetMapping("/agregarMantenimiento")
    public String agregarMantenimiento(Mantenimiento mantenimiento) {
        return "mantenimiento/editarMantenimiento";
    }


	@PostMapping("/guardarMantenimiento")
	String guardarMantenimiento (Mantenimiento mantenimiento){

		if (mantenimiento.getId() == null) {
			mantenimiento.setId(0);
            mantServ.guardar(mantenimiento);
            System.out.println("Se guardo nueva programación de mantenimiento");
            
        } else{
            mantServ.guardar(mantenimiento);
            System.out.println("Se modifico operación de mantenimiento");
        }
        
        return "redirect:mantenimiento";
	}

	@GetMapping("/editarMantenimiento/{id}")
    public String editarMantenimiento(Mantenimiento mantenimiento, Model model) {

        mantenimiento = mantServ.buscar(mantenimiento);
        model.addAttribute("operacion", mantenimiento);

        return "mantenimiento/editarMantenimiento";
    }

	@GetMapping("/eliminarMantenimiento")
    public String eliminarMantenimiento(Mantenimiento mantenimiento) {

        mantServ.eliminar(mantenimiento);

        return "redirect:mantenimiento";
    }


}