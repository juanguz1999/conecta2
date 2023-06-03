package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "grado")

public class Grado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NombreGrado")
    private String nombreGrado;
    @OneToMany(mappedBy = "gradoID")
    private List<Estudiante> estudianteList;
    @OneToMany(mappedBy = "gradoID")
    private List<Comunicado> comunicadoList;
    @OneToMany(mappedBy = "gradoID")
    private List<Seccion> seccionList;
    @OneToMany(mappedBy = "gradoID")
    private List<Curso> cursoList;

    
}
