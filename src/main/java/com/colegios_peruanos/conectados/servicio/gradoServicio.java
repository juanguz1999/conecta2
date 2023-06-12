package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.gradoDao;
import com.colegios_peruanos.conectados.modelos.Grado;

@Component
public class gradoServicio implements IServicio<Grado>{

    @Autowired
    gradoDao gradodao;

    @Override
    public List<Grado> listar() {
        return (List<Grado>) gradodao.findAll();
    }

    @Override
    public void guardar(Grado item) {
        gradodao.save(item);
    }

    @Override
    public void eliminar(Grado item) {
        gradodao.delete(item);
    }

    @Override
    public Grado buscar(Grado item) {
        return gradodao.findById(item.getId()).orElse(null);
    }

    @Override
    public Grado buscar(Integer id) {
        return gradodao.findById(id).orElse(null);
    }
    
}
