package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Data
@Table(name = "comunicado")
public class Comunicado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Tipo")
    private String tipo;
    @Lob
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "FechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "GradoID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Grado gradoID;
    @JoinColumn(name = "SeccionID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Seccion seccionID;
    @JoinColumn(name = "CursoID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Curso cursoID;
    @OneToMany(mappedBy = "comunicadoID")
    @JsonIgnore
    private List<Comunicadopadre> comunicadopadreList;

}
