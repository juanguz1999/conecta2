package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface usuarioDao extends JpaRepository<Usuario, Integer> {
    // List<Usuario> findAllByTipoUsuario(String tipoUsuario); // Comenta o elimina
    // este método

    List<Usuario> findAll(); // Agrega este método para listar todos los usuarios
}
// public interface usuarioDao extends JpaRepository<Usuario, Integer> {
// List<Usuario> findAllByTipoUsuario(String tipoUsuario);
// }