package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.cursoDao;
import com.colegios_peruanos.conectados.modelos.Curso;

@Component
public class cursoServicio implements IServicio<Curso>{

    @Autowired
    cursoDao cursodao;

    @Override
    public List<Curso> listar() {
        return (List<Curso>) cursodao.findAll();
    }

    @Override
    public void guardar(Curso item) {
        cursodao.save(item);
    }

    @Override
    public void eliminar(Curso item) {
        cursodao.delete(item);
    }

    @Override
    public Curso buscar(Curso item) {
        return cursodao.findById(item.getId()).orElse(null);
    }

    @Override
    public Curso buscar(Integer id) {
        return cursodao.findById(id).orElse(null);
    }
    
}
