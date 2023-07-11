package com.colegios_peruanos.conectados;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.colegios_peruanos.conectados.modelos.Calificacion;
import com.colegios_peruanos.conectados.servicio.calificacionServicio;

@SpringBootTest
class ConectadosApplicationTests {

	@Autowired
	calificacionServicio calificacionservicio;
	
	
	@Test
	void contextLoads() {
	}

	@Test
	void pruebasDatos() {

		Calificacion calificacionPC1 = calificacionservicio.buscar(4);

		System.out.println(calificacionPC1);

	}

}
