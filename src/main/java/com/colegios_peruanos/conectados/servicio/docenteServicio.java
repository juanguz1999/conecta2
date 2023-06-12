package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.docenteDao;
import com.colegios_peruanos.conectados.modelos.Docente;

@Component
public class docenteServicio implements IServicio<Docente>{

    @Autowired
    docenteDao docentedao;
    
    
    @Override
    public List<Docente> listar() {
        return (List<Docente>) docentedao.findAll();
    }

    @Override
    public void guardar(Docente item) {
        docentedao.save(item);
    }

    @Override
    public void eliminar(Docente item) {
        docentedao.delete(item);
    }

    @Override
    public Docente buscar(Docente item) {
        return docentedao.findById(item.getId()).orElse(null);
    }
    
    @Override
    public Docente buscar(Integer id) {
        return docentedao.findById(id).orElse(null);
    }
    
}
