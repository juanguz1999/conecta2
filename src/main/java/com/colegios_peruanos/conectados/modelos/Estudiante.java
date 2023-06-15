package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;

@Entity
@Data
@Table(name = "estudiante")
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @JoinColumn(name = "GradoID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Grado gradoID;
    @JoinColumn(name = "SeccionID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Seccion seccionID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioID;
    @OneToMany(mappedBy = "estudianteID")
    @JsonIgnore
    private List<Asistencia> asistenciaList;

}
