package com.colegios_peruanos.conectados.controlador;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import com.colegios_peruanos.conectados.controladores.usuarioController;
import com.colegios_peruanos.conectados.modelos.Usuario;
import com.colegios_peruanos.conectados.servicio.usuarioServicio;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UsuariosTest {
    
    @InjectMocks
    private usuarioController usuariocontroller;

    @Mock
    private usuarioServicio usuarioservicio;

    @Test
    public void testMostrarUsuarios() {
        // Datos de ejemplo para el test
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Juan");
        usuario1.setApellido("Perez");
        usuario1.setCorreoElectronico("juan.perez@example.com");
        usuario1.setTipoUsuario("docente");

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Maria");
        usuario2.setApellido("Lopez");
        usuario2.setCorreoElectronico("maria.lopez@example.com");
        usuario2.setTipoUsuario("estudiante");

        List<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(usuario1);
        listaUsuarios.add(usuario2);

        // Simulación del comportamiento del servicio usuarioservicio.listar()
        Mockito.when(usuarioservicio.listar()).thenReturn(listaUsuarios);

        // Modelo para recibir los atributos del controlador
        Model model = new ConcurrentModel();

        // Simulación de HttpServletRequest
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Llamada al método a probar
        String viewName = usuariocontroller.mostrarUsuarios(model, request);

        // Verificación
        assert (viewName.equals("administrarPerfil")); // Verificar que la vista devuelta es "administrarPerfil"
        List<Usuario> usuariosEnModelo = (List<Usuario>) model.getAttribute("usuarios");
        assert (usuariosEnModelo != null); // Verificar que el atributo "usuarios" no es nulo
        assert (usuariosEnModelo.size() == 2); // Verificar que se han agregado dos usuarios al atributo "usuarios"
    }
}
