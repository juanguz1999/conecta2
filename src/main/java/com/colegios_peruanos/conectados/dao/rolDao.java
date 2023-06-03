package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface rolDao extends JpaRepository<Rol, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}