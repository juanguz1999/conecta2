package com.colegios_peruanos.conectados.controlador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.servlet.ModelAndView;

import com.colegios_peruanos.conectados.controladores.mantenimientoController;
import com.colegios_peruanos.conectados.modelos.AsignacionEstudiante;
import com.colegios_peruanos.conectados.servicio.asignacionEstudianteServicio;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AsignacionEstudianteControllerUnitTest {

    @InjectMocks
    private mantenimientoController asignacionEstudianteController;

    @Mock
    private asignacionEstudianteServicio asignacionestudianteServicio;

    @Test
    public void testGuardarAsignacionEstudiante() {
        // Datos de ejemplo para el test
        Integer estudianteId = 1;
        Integer cursoId = 4;

        // Simulación del comportamiento del servicio para guardar la asignación de estudiante
        doNothing().when(asignacionestudianteServicio).guardar(any(AsignacionEstudiante.class));

        // Llamada al método a probar
        String resultado = asignacionEstudianteController.guardarAsignacionEstudiante(estudianteId, cursoId);

        // Verificación
        assertEquals("redirect:/vistaprincipal", resultado); // Verificar que se redirige correctamente
        verify(asignacionestudianteServicio, times(1)).guardar(any(AsignacionEstudiante.class)); // Verificar que el servicio se llamó una vez para guardar la asignación de estudiante
    }
}
