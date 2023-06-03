package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;
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
    private List<Estudiante> estudianteList;
    @OneToMany(mappedBy = "seccionID")
    private List<Comunicado> comunicadoList;
    @JoinColumn(name = "GradoID", referencedColumnName = "ID")
    @ManyToOne
    private Grado gradoID;
    @OneToMany(mappedBy = "seccionID")
    private List<Curso> cursoList;

}
