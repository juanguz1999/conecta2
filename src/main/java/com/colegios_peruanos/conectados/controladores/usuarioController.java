package com.colegios_peruanos.conectados.controladores;

import com.colegios_peruanos.conectados.dao.usuarioDao;
import com.colegios_peruanos.conectados.modelos.Usuario;
import com.colegios_peruanos.conectados.servicio.usuarioServicio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class usuarioController {
    @Autowired
    private usuarioDao usuarioDao;

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(HttpServletRequest request) {
        String nombre = request.getParameter("nombre");
        String correoElectronico = request.getParameter("correoElectronico");
        String tipoUsuario = request.getParameter("tipoUsuario");

        // Verificar que el valor de tipoUsuario sea válido
        if (tipoUsuario != null && !tipoUsuario.isEmpty()) {
            tipoUsuario = tipoUsuario.toLowerCase();
            if (tipoUsuario.equals("docente") || tipoUsuario.equals("estudiante") ||
                    tipoUsuario.equals("padre") || tipoUsuario.equals("administración")) {
                // El valor de tipoUsuario es válido, crear el objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setId(null);
                usuario.setNombre(nombre);
                usuario.setApellido(null);
                usuario.setCorreoElectronico(correoElectronico);
                usuario.setContrasena(null);
                usuario.setTipoUsuario(tipoUsuario);
                usuario.setFechaRegistro(null);

                usuarioDao.save(usuario);
            } else {
                // El valor de tipoUsuario no es válido
                // Aquí puedes manejar el error o enviar un mensaje de error al usuario
            }
        } else {
            // El valor de tipoUsuario está vacío o no está presente
            // Aquí puedes manejar el error o enviar un mensaje de error al usuario
        }

        return "redirect:/vistaprincipal";
    }

}