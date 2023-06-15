package com.colegios_peruanos.conectados.controladores;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.colegios_peruanos.conectados.modelos.Mantenimiento;
import com.colegios_peruanos.conectados.modelos.Usuario;
import com.colegios_peruanos.conectados.servicio.mantenimientoServicio;
import com.colegios_peruanos.conectados.servicio.usuarioServicio;

@Controller
public class mantenimientoController {
    
	@Autowired
	private mantenimientoServicio mantServ;

    @Autowired
    private usuarioServicio usuarioservicio;

    @GetMapping("/mantenimiento")
	String ListaMantenimiento(Model model){

		List<Mantenimiento> operaciones= mantServ.listar(); 
		model.addAttribute("operaciones", operaciones);
		return "mantenimiento/administrarMantenimiento";
		
	}

	@GetMapping("/agregarMantenimiento")
    public String agregarMantenimiento(Mantenimiento mantenimiento,Model model) {
        
        List<Usuario> usuariosadm = usuarioservicio.usuariosPorTipo("administracion");
        model.addAttribute("usuarios", usuariosadm);

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

}