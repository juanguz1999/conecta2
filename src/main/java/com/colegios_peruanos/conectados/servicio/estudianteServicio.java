package com.colegios_peruanos.conectados.servicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.colegios_peruanos.conectados.dao.estudianteDao;
import com.colegios_peruanos.conectados.modelos.Estudiante;

@Component
public class estudianteServicio implements IServicio<Estudiante>{

    @Autowired
    estudianteDao estudiantedao;

    @Override
    public List<Estudiante> listar() {
        return (List<Estudiante>) estudiantedao.findAll();
    }

    @Override
    public void guardar(Estudiante item) {
        estudiantedao.save(item);
    }

    @Override
    public void eliminar(Estudiante item) {
        estudiantedao.delete(item);
    }

    @Override
    public Estudiante buscar(Estudiante item) {
        return estudiantedao.findById(item.getId()).orElse(null);
    }

    @Override
    public Estudiante buscar(Integer id) {
        return estudiantedao.findById(id).orElse(null);
    }
    
   
    
}
