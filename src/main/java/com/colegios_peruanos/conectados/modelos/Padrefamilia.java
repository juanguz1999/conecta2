package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @Column(name = "NombrePadre")
    private String nombrePadre;
    @Column(name = "ApellidoPadre")
    private String apellidoPadre;
    @Column(name = "OtrosDatosPadre")
    private String otrosDatosPadre;
    @JoinTable(name = "relacionpadreestudiante", joinColumns = {
        @JoinColumn(name = "PadreFamiliaID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EstudianteID", referencedColumnName = "ID")})
    @ManyToMany
    @JsonIgnore
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "padreFamiliaID")
    @JsonIgnore
    private List<Comunicadopadre> comunicadopadreList;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Usuario usuarioID;
    
}
