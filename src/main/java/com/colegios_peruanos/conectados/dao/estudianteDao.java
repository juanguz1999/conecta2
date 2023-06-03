package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Estudiante;

import org.springframework.data.jpa.repository.JpaRepository;

public interface estudianteDao extends JpaRepository<Estudiante, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}