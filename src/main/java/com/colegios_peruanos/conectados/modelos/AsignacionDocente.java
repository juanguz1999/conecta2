package com.colegios_peruanos.conectados.modelos;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Data
@Table(name = "asignaciondocente")
public class AsignacionDocente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    
    @Column(name = "docenteID")
    private Integer docenteID;
    
    @Column(name = "CursoID")
    private Integer cursoID;

    
}

