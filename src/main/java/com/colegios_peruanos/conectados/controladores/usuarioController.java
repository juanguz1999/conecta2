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
        String correoElectronico = request.getParameter("correoElectronico");

        Usuario usuario = new Usuario();
        usuario.setId(null);
        usuario.setNombre(null);
        usuario.setApellido(null);
        usuario.setCorreoElectronico(correoElectronico);
        usuario.setContrasena(null);
        usuario.setTipoUsuario(null);
        usuario.setFechaRegistro(null);

        usuarioDao.save(usuario);

        return "/vistaprincipal";
    }

}