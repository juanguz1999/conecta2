package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Calificacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface calificacionDao extends JpaRepository<Calificacion, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}