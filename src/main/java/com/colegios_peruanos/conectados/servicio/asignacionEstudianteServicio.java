package com.colegios_peruanos.conectados.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.colegios_peruanos.conectados.dao.asignacionEstudianteDao;
import com.colegios_peruanos.conectados.modelos.AsignacionEstudiante;

@Component
public class asignacionEstudianteServicio {

    @Autowired
    private asignacionEstudianteDao asignacionEstudianteDao;

    @PersistenceContext
    private EntityManager entityManager;

    public List<AsignacionEstudiante> listar() {
        return asignacionEstudianteDao.findAll();
    }

    public void guardar(AsignacionEstudiante item) {
        asignacionEstudianteDao.save(item);
    }

    public void eliminar(AsignacionEstudiante item) {
        asignacionEstudianteDao.delete(item);
    }

    // Método para obtener el nombre del estudiante a partir del ID
    public String obtenerNombreEstudiante(Integer estudianteID) {
        Query query = entityManager.createQuery("SELECT e.nombre FROM Estudiante e WHERE e.ID = :estudianteID");
        query.setParameter("estudianteID", estudianteID);
        return (String) query.getSingleResult();
    }

    // Método para obtener el nombre del curso a partir del ID
    public String obtenerNombreCurso(Integer cursoID) {
        Query query = entityManager.createQuery("SELECT c.nombreCurso FROM Curso c WHERE c.ID = :cursoID");
        query.setParameter("cursoID", cursoID);
        return (String) query.getSingleResult();
    }

}
