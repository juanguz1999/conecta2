package com.colegios_peruanos.conectados.modelos;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contrasena;
    private String tipoUsuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

}
