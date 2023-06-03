package com.colegios_peruanos.conectados.modelos;


import lombok.Data;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "mantenimiento")

public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "TipoMantenimiento")
    private String tipoMantenimiento;
    @Column(name = "FechaHoraProgramada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraProgramada;
    @Column(name = "Estado")
    private String estado;
    @Lob
    @Column(name = "Observaciones")
    private String observaciones;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "ID")
    @ManyToOne
    private Usuario usuarioID;
    
}
