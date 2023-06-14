package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private List<Estudiante> estudianteList;
    @OneToMany(mappedBy = "gradoID")
    @JsonIgnore
    private List<Comunicado> comunicadoList;
    @OneToMany(mappedBy = "gradoID")
    @JsonIgnore
    private List<Seccion> seccionList;
    @OneToMany(mappedBy = "gradoID")
    @JsonIgnore
    private List<Curso> cursoList;
    
    
}
