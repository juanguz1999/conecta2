package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.calificacionDao;
import com.colegios_peruanos.conectados.modelos.Calificacion;

@Component
public class calificacionServicio implements IServicio<Calificacion>{

    @Autowired
    calificacionDao calificaciondao;

    @Override
    public List<Calificacion> listar() {
        return (List<Calificacion>)calificaciondao.findAll();
    }

    @Override
    public void guardar(Calificacion item) {
        calificaciondao.save(item);
    }

    @Override
    public void eliminar(Calificacion item) {
        calificaciondao.delete(item);
    }

    @Override
    public Calificacion buscar(Calificacion item) {
        return calificaciondao.findById(item.getId()).orElse(null);
    }

    @Override
    public Calificacion buscar(Integer id) {
        return calificaciondao.findById(id).orElse(null);
    }

    public List<Calificacion> calificacionEstudiante(int parseInt) {
        return null;
    }
    
}
