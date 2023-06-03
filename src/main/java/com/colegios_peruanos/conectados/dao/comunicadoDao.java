package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Comunicado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface comunicadoDao extends JpaRepository<Comunicado, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}