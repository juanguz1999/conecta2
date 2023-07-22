package com.colegios_peruanos.conectados.servicio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import com.colegios_peruanos.conectados.dao.estudianteDao;
import com.colegios_peruanos.conectados.modelos.Estudiante;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EstudianteServicioTest {

    @Autowired
    private estudianteServicio estudianteservicio;

    @Autowired
    private estudianteDao estudiantedao;

    @DisplayName("test para listar estudiante")
    @Test
    void testListarEstudiantes() {

        // when
        List<Estudiante> estudiantes = estudianteservicio.listar();

        // then
        assertThat(estudiantes).isNotNull();
        assertThat(estudiantes.size()).isEqualTo(estudiantedao.findAll().size());

    }

    @DisplayName("test para buscar por grado y seccion por ID")
    @Test
    void testListarEstudiantesGradoIDySeccionID() {

        // When
        List<Estudiante> estudiantes = estudianteservicio.buscarGradoSeccion(2, 7);
        List<Estudiante> estudiantes2 = estudianteservicio.buscarGradoSeccion(1, 4);
        // then
        assertThat(estudiantes).isNotNull();
        assertThat(estudiantes.size()).isEqualTo(1);

        assertThat(estudiantes2).isNotNull();
        assertThat(estudiantes2.size()).isEqualTo(2);

    }

    @DisplayName("test para buscar por grado y seccion y curso")
    @Test
    void testbuscarEstudiantesCurso() {

        // When
        List<Estudiante> estudiantes = estudianteservicio.buscarEstudiantesCurso(1, 4, 1);
        // then
        assertThat(estudiantes).isNotNull();
        assertThat(estudiantes.size()).isEqualTo(2);

    }

}
