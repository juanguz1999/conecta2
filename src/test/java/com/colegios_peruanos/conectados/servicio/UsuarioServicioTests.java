package com.colegios_peruanos.conectados.servicio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import com.colegios_peruanos.conectados.modelos.Usuario;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioServicioTests {
    
    @Autowired
    usuarioServicio usuarioservicio;

    @DisplayName("test para buscar usuarios por tipo")
    @Test
    void testbuscarusuariosPorTipo() {

        // when
        List<Usuario> usuarios = usuarioservicio.usuariosPorTipo("estudiante");

        // then
        assertThat(usuarios).isNotNull();
        
        }

    @DisplayName("test para usuario por correo")
    @Test
    void testbuscarusuariosPorCorreo() {

        // when
        Usuario usuario = usuarioservicio.buscarPorCorreo("Estudiante_1@correo.com");

        // then
        assertThat(usuario).isNotNull();
        
        }


    
}
