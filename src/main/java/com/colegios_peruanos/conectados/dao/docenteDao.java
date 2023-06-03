package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface docenteDao extends JpaRepository<Docente, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}