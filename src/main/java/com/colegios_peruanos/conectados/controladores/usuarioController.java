package com.colegios_peruanos.conectados.controladores;
import com.colegios_peruanos.conectados.dao.usuarioDao;
import com.colegios_peruanos.conectados.modelos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class usuarioController {
    @Autowired
    private usuarioDao usuarioDao;

    public void guardarUsuario(Usuario usuario) {
        usuarioDao.save(usuario);
    }
}