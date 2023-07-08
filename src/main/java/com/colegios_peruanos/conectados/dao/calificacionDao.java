package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Calificacion;
import com.colegios_peruanos.conectados.modelos.Curso;
import com.colegios_peruanos.conectados.modelos.Estudiante;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface calificacionDao extends JpaRepository<Calificacion, Integer> {

    @Transactional
    Calificacion findByTipo(String tipo);

    @Transactional
    Calificacion findByIdAndCursoID(Curso curso, Estudiante estudiante);

    @Transactional
    Calificacion findByCursoIDAndEstudianteIDAndTipo(Curso curso, Estudiante estudiante, String tipo);
}