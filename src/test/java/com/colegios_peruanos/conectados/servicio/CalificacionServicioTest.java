package com.colegios_peruanos.conectados.servicio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.colegios_peruanos.conectados.modelos.Calificacion;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CalificacionServicioTest {
    
    @Autowired
    calificacionServicio calificacionservicio;

    @DisplayName("test para buscar calificacion por estudiante Id y curso")
    @Test
    void testbuscarPorCursoEstudiante() {

        // when
        Calificacion calificacion = calificacionservicio.buscarPorCursoEstudiante(4,3,"PC1");

        // then
        assertThat(calificacion).isNotNull();
        
        }

    @DisplayName("test para buscar calificacion por tipo")
    @Test
    void testbuscarbuscarPorTipo() {

        // when
        Calificacion calificacion = calificacionservicio.buscarPorTipo("PC1");

        // then
        assertThat(calificacion).isNotNull();
        
        }
}
