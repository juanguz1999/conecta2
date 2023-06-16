package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Asistencia;
import com.colegios_peruanos.conectados.modelos.Estudiante;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface asistenciaDao extends JpaRepository<Asistencia, Integer> {

    @Transactional
    Asistencia findByEstudianteIDAndFechaAsistencia(Estudiante estudiante,Date date);
    
    @Transactional
    List<Asistencia> findAllByEstudianteID(Estudiante estudiante);
}