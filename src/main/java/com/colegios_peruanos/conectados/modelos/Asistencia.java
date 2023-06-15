package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Data
@Table(name = "asistencia")
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FechaAsistencia")
    @Temporal(TemporalType.DATE)
    private Date fechaAsistencia;
    @Column(name = "EstadoAsistencia")
    private String estadoAsistencia;
    @Column(name = "Observaciones")
    private String observaciones;
    @JoinColumn(name = "EstudianteID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Estudiante estudianteID;


}
