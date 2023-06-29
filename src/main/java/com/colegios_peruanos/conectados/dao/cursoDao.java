package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Curso;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.modelos.Seccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface cursoDao extends JpaRepository<Curso, Integer> {

    @Transactional
    List<Curso> findAllByGradoIDAndSeccionID(Grado grado, Seccion seccion);
}