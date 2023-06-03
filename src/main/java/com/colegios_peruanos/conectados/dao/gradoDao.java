package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Grado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface gradoDao extends JpaRepository<Grado, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}