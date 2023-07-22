package com.colegios_peruanos.conectados.controlador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.colegios_peruanos.conectados.controladores.mantenimientoController;
import com.colegios_peruanos.conectados.modelos.Mantenimiento;
import com.colegios_peruanos.conectados.servicio.mantenimientoServicio;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MantenimientoControllerUnitTest {

    @InjectMocks
    private static mantenimientoController mantenimientocontroller;

    @Mock
    private mantenimientoServicio mantServ;

    @Test
    public void testListaMantenimiento() throws Exception {
        // Datos de ejemplo para el test
        Mantenimiento mantenimiento1 = new Mantenimiento();
        mantenimiento1.setId(1);
        mantenimiento1.setDescripcion("Mantenimiento 1");

        Mantenimiento mantenimiento2 = new Mantenimiento();
        mantenimiento2.setId(2);
        mantenimiento2.setDescripcion("Mantenimiento 2");

        List<Mantenimiento> listaMantenimientos = new ArrayList<>();
        listaMantenimientos.add(mantenimiento1);
        listaMantenimientos.add(mantenimiento2);

        // Simulación del comportamiento del servicio para listar los mantenimientos
        when(mantServ.listar()).thenReturn(listaMantenimientos);

        // Crear instancia de MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mantenimientocontroller).build();

        // Realizar la solicitud GET a "/mantenimiento"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/mantenimiento")).andReturn();

        // Verificación
        ModelAndView modelAndView = result.getModelAndView();
        assertNotNull(modelAndView); // Verificar que el objeto ModelAndView no es nulo
        assertEquals("mantenimiento/administrarMantenimiento", modelAndView.getViewName()); // Verificar que la vista devuelta es "mantenimiento/administrarMantenimiento"

        List<Mantenimiento> mantenimientosEnModelo = (List<Mantenimiento>) modelAndView.getModel().get("operaciones");
        assert (mantenimientosEnModelo != null); // Verificar que el atributo "operaciones" no es nulo
        assertEquals(2, mantenimientosEnModelo.size()); // Verificar que se han agregado dos mantenimientos al atributo "operaciones"
    }
}