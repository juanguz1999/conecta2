package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Data
@Table(name = "curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NombreCurso")
    private String nombreCurso;
    @ManyToMany(mappedBy = "cursoList")
    @JsonIgnore
    private List<Estudiante> estudianteList;
    @JoinTable(name = "asignaciondocente", joinColumns = {
        @JoinColumn(name = "CursoID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "docenteID", referencedColumnName = "ID")})
    @ManyToMany
    @JsonIgnore
    private List<Docente> docenteList;
    @OneToMany(mappedBy = "cursoID")
    @JsonIgnore
    private List<Calificacion> calificacionList;
    @OneToMany(mappedBy = "cursoID")
    @JsonIgnore
    private List<Comunicado> comunicadoList;
    @JoinColumn(name = "SeccionID", referencedColumnName = "ID")
    @JsonIgnore
    @ManyToOne
    private Seccion seccionID;
    @JoinColumn(name = "GradoID", referencedColumnName = "ID")
    @JsonIgnore
    @ManyToOne
    private Grado gradoID;
    
}
