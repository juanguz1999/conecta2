package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "padrefamilia")
public class Padrefamilia implements Serializable{
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
    @Column(name = "CorreoElectronico")
    private String correoElectronico;
    @Column(name = "Contrasena")
    private String contrasena;
    @Column(name = "TipoUsuario")
    private String tipoUsuario;
    @Column(name = "FechaRegistro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    
    // @ManyToMany(mappedBy = "usuarioList")
    // private List<Padrefamilia> padrefamiliaList;
   
    @JoinTable(name = "usuariorol", joinColumns = {
        @JoinColumn(name = "UsuarioID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "RolID", referencedColumnName = "ID")})
    
    @ManyToMany
    private List<Rol> rolList;
    @OneToMany(mappedBy = "usuarioID")
    private List<Estudiante> estudianteList;
    @OneToMany(mappedBy = "estudianteID")
    private List<Calificacion> calificacionList;
    @OneToMany(mappedBy = "usuarioID")
    private List<Docente> docenteList;
    @OneToMany(mappedBy = "usuarioID")
    private List<Mantenimiento> mantenimientoList;

    // @OneToMany(mappedBy = "usuarioID")
    // private List<Padrefamilia> padrefamiliaList1;
}
