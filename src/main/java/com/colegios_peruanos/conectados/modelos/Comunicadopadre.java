package com.colegios_peruanos.conectados.modelos;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Data
@Table(name = "comunicadopadre")
public class Comunicadopadre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @JoinColumn(name = "ComunicadoID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Comunicado comunicadoID;
    @JoinColumn(name = "PadreFamiliaID", referencedColumnName = "ID")
    @ManyToOne
    @JsonIgnore
    private Padrefamilia padreFamiliaID;

}
