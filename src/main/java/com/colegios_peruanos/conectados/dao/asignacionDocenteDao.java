package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.AsignacionDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface asignacionDocenteDao extends JpaRepository<AsignacionDocente, Integer> {
    // Aquí puedes agregar métodos personalizados según tus necesidades
}
