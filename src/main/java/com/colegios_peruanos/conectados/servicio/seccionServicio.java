package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.colegios_peruanos.conectados.dao.seccionDao;
import com.colegios_peruanos.conectados.modelos.Seccion;

@Component
public class seccionServicio implements IServicio<Seccion>{

    @Autowired
    seccionDao secciondao;
    
    @Override
    public List<Seccion> listar() {
        return (List<Seccion>) secciondao.findAll();
    }

    @Override
    public void guardar(Seccion item) {
        secciondao.save(item);
    }

    @Override
    public void eliminar(Seccion item) {
        secciondao.delete(item);
    }

    @Override
    public Seccion buscar(Seccion item) {
        return secciondao.findById(item.getId()).orElse(null);
    }

    @Override
    public Seccion buscar(Integer id) {
        return secciondao.findById(id).orElse(null);
    }
    
}
