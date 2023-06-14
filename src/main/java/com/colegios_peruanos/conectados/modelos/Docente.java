package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Data
@Table(name = "docente")

public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NombreDocente")
    private String nombreDocente;
    @Column(name = "ApellidoDocente")
    private String apellidoDocente;
    @Column(name = "OtrosDatosDocente")
    private String otrosDatosDocente;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioID;

    
}
