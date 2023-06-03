package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioDao extends JpaRepository<Usuario, Integer> {
    // Agrega métodos personalizados para acceder a la tabla usuario aquí
}