package com.colegios_peruanos.conectados.modelos;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "asignacioncurso")
public class AsignacionEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "EstudianteID")
    private Integer estudianteID;

    @Column(name = "CursoID")
    private Integer cursoID;


}
