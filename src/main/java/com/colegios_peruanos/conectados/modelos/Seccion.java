package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Data
@Table(name = "seccion")
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NombreSeccion")
    private String nombreSeccion;
    @OneToMany(mappedBy = "seccionID")
    @JsonIgnore
    private List<Estudiante> estudianteList;
    @OneToMany(mappedBy = "seccionID")
    @JsonIgnore
    private List<Comunicado> comunicadoList;
    @JoinColumn(name = "GradoID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Grado gradoID;
    @OneToMany(mappedBy = "seccionID")
    @JsonIgnore
    private List<Curso> cursoList;

}
