package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.usuarioDao;
import com.colegios_peruanos.conectados.modelos.Usuario;

@Component
public class usuarioServicio implements IServicio<Usuario>{

    @Autowired
    usuarioDao usuariodao;

    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) usuariodao.findAll();
    }

    @Override
    public void guardar(Usuario item) {
        usuariodao.save(item);
    }

    @Override
    public void eliminar(Usuario item) {
        usuariodao.delete(item);
    }

    @Override
    public Usuario buscar(Usuario item) {
        return usuariodao.findById(item.getId()).orElse(null);
    }

    @Override
    public Usuario buscar(Integer id) {
        return usuariodao.findById(id).orElse(null);
    }

    //public List<Usuario> usuariosPorTipo(String tipoUsuario) {
    //    return usuariodao.findAllByTipoUsuario(tipoUsuario);
    //}
    


}
