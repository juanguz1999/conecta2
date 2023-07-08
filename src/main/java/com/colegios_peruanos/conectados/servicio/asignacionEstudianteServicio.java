package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.asignacionEstudianteDao;
import com.colegios_peruanos.conectados.modelos.AsignacionEstudiante;

@Component
public class asignacionEstudianteServicio implements IServicio<AsignacionEstudiante> {

    @Autowired
    private asignacionEstudianteDao asignacionEstudianteDao;

    @Override
    public List<AsignacionEstudiante> listar() {
        return asignacionEstudianteDao.findAll();
    }

    @Override
    public void guardar(AsignacionEstudiante item) {
        asignacionEstudianteDao.save(item);
    }

    @Override
    public void eliminar(AsignacionEstudiante item) {
        asignacionEstudianteDao.delete(item);
    }

    @Override
    public AsignacionEstudiante buscar(AsignacionEstudiante item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

    @Override
    public AsignacionEstudiante buscar(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscar'");
    }

}
