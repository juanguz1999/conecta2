package com.colegios_peruanos.conectados.servicio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.colegios_peruanos.conectados.modelos.Curso;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CursoServicioTest {
    
    @Autowired
    cursoServicio cursoservicio;

    @DisplayName("test para buscar curso por grado y sección")
    @Test
    void testbuscarGradoSeccion() {

        // when
        List<Curso> cursos = cursoservicio.buscarGradoSeccion(2,7);

        // then
        assertThat(cursos).isNotNull();
        
        }

    }

