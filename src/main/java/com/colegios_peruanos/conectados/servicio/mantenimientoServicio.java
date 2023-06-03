package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.colegios_peruanos.conectados.dao.mantenimientoDao;
import com.colegios_peruanos.conectados.modelos.Mantenimiento;

@Component
public class mantenimientoServicio implements IServicio<Mantenimiento> {

    @Autowired
    mantenimientoDao mantDao;

    @Override
    public List<Mantenimiento> listar() {
        return (List<Mantenimiento>) mantDao.findAll();
    }

    @Override
    public void guardar(Mantenimiento item) {
        mantDao.save(item);
    }

    @Override
    public void eliminar(Mantenimiento item) {
        mantDao.delete(item);
    }

    @Override
    public Mantenimiento buscar(Mantenimiento item) {
        return mantDao.findById(item.getId()).orElse(null);
    }

    @Override
    public Mantenimiento buscar(Integer id) {
        return mantDao.findById(id).orElse(null);
    }

}
