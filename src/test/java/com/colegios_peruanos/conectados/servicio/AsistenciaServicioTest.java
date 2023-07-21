package com.colegios_peruanos.conectados.servicio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.colegios_peruanos.conectados.modelos.Asistencia;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AsistenciaServicioTest {

    @Autowired
    asistenciaServicio asistenciaservicio;

    @DisplayName("test para listar asistencia ")
    @Test
    void testasistenciaporEstudianteIdAndFecha() {

        // when
        Asistencia asistencia = asistenciaservicio.asistenciaporEstudianteIdAndFecha(1);

        // then
        assertThat(asistencia).isNotNull();
        }

    @DisplayName("test para listar asistencia por estudiante Id")
    @Test
    void testasistenciaporEstudianteId() {

        // when

        List<Asistencia> asistencias = asistenciaservicio.asistenciaporEstudianteId(1);

        // then
        assertThat(asistencias).isNotNull();
        assertThat(asistencias.size()).isGreaterThan(1);

    }

}
