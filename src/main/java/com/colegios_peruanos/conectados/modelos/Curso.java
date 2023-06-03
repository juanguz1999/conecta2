package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;
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
    @OneToMany(mappedBy = "cursoID")
    private List<Calificacion> calificacionList;
    @OneToMany(mappedBy = "cursoID")
    private List<Comunicado> comunicadoList;
    @JoinColumn(name = "SeccionID", referencedColumnName = "ID")
    @ManyToOne
    private Seccion seccionID;
    @JoinColumn(name = "GradoID", referencedColumnName = "ID")
    @ManyToOne
    private Grado gradoID;

    
}
