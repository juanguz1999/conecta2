package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Calificacion;
import com.colegios_peruanos.conectados.modelos.Curso;
import com.colegios_peruanos.conectados.modelos.Estudiante;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.modelos.Seccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface estudianteDao extends JpaRepository<Estudiante, Integer> {
    
    @Transactional
    List<Estudiante> findAllByGradoIDAndSeccionID(Grado grado,Seccion seccion);

    @Query("SELECT e FROM Estudiante e " +
           "JOIN e.cursoList c " +
           "WHERE e.gradoID = :grado " +
           "AND e.seccionID = :seccion " +
           "AND c = :curso")
    List<Estudiante> findAllByGradoSeccionCurso(Grado grado, Seccion seccion, Curso curso);

    @Query("SELECT c FROM Calificacion c JOIN c.estudianteID e JOIN e.cursoList cr WHERE e.gradoID = :grado AND e.seccionID = :seccion AND cr = :curso")
    List<Calificacion> findCalificacionesByGradoAndSeccionAndCurso(Grado grado, Seccion seccion, Curso curso);

    @Transactional
    List<Calificacion> findCalificacionListById(Estudiante estudiante);

}