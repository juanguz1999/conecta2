package com.colegios_peruanos.conectados.controladores;

import com.colegios_peruanos.conectados.dao.usuarioDao;
import com.colegios_peruanos.conectados.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class usuarioController {
    @Autowired
    private usuarioDao usuarioDao;

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario) {
        usuario.setId(null);
        usuario.setNombre(null);
        usuario.setApellido(null);
        usuario.setCorreoElectronico(null);
        usuario.setContrasena(null);
        usuario.setTipoUsuario(null);
        usuario.setFechaRegistro(null);
        usuarioDao.save(usuario);
        return "usuarioGuardado";
    }

}