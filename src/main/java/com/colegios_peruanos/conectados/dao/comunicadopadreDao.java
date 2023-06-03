package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Comunicadopadre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface comunicadopadreDao extends JpaRepository<Comunicadopadre, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}