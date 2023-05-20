package com.colegios_peruanos.conectados.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
    @GetMapping("/")
	String Index(){
		return "index";
	}

	@GetMapping("/vistaprincipal")
	String vistaprincipal(){
		return "vistaprincipal";
	}
    
}
