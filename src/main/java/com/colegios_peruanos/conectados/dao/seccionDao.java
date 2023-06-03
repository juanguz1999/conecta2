package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface seccionDao extends JpaRepository<Seccion, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}