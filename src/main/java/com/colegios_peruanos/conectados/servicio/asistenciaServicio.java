package com.colegios_peruanos.conectados.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colegios_peruanos.conectados.dao.asistenciaDao;
import com.colegios_peruanos.conectados.modelos.Asistencia;

@Component
public class asistenciaServicio implements IServicio<Asistencia>{

    @Autowired
    asistenciaDao asistenciadao;

    @Autowired
    estudianteServicio estudianteservicio;


    @Override
    public List<Asistencia> listar() {
        return (List<Asistencia>) asistenciadao.findAll();
    }

    @Override
    public void guardar(Asistencia item) {
        asistenciadao.save(item);
    }

    @Override
    public void eliminar(Asistencia item) {
        asistenciadao.delete(item);
    }

    @Override
    public Asistencia buscar(Asistencia item) {
        return asistenciadao.findById(item.getId()).orElse(null);
    }

    @Override
    public Asistencia buscar(Integer id) {
        return asistenciadao.findById(id).orElse(null);
    }

    public Asistencia asistenciaporEstudianteId(Integer estudianteId) {
        Date fechaActual = new Date();
        return asistenciadao.findByEstudianteIDAndFechaAsistencia(estudianteservicio.buscar(estudianteId),fechaActual);
    }


    
}
