package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface asistenciaDao extends JpaRepository<Asistencia, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}