package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Padrefamilia;

import org.springframework.data.jpa.repository.JpaRepository;

public interface padrefamiliaDao extends JpaRepository<Padrefamilia, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}