package com.colegios_peruanos.conectados.dao;

import com.colegios_peruanos.conectados.modelos.AsignacionEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface asignacionEstudianteDao extends JpaRepository<AsignacionEstudiante, Integer> {
    // Aquí puedes agregar métodos personalizados según tus necesidades
}
