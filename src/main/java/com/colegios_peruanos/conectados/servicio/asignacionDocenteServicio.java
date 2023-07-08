package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.asignacionDocenteDao;
import com.colegios_peruanos.conectados.modelos.AsignacionDocente;

@Component
public class asignacionDocenteServicio implements IServicio<AsignacionDocente> {

    @Autowired
    private asignacionDocenteDao asignacionDocenteDao;

    @Override
    public List<AsignacionDocente> listar() {
        return asignacionDocenteDao.findAll();
    }

    @Override
    public void guardar(AsignacionDocente item) {
        asignacionDocenteDao.save(item);
    }

    @Override
    public void eliminar(AsignacionDocente item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public AsignacionDocente buscar(AsignacionDocente item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public AsignacionDocente buscar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

}
