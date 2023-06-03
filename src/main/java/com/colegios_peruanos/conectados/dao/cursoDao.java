package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface cursoDao extends JpaRepository<Curso, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}