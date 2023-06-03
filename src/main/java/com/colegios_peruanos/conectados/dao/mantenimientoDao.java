package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface mantenimientoDao extends JpaRepository<Mantenimiento, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}