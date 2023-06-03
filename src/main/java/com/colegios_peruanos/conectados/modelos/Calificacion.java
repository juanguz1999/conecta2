package com.colegios_peruanos.conectados.modelos;
import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;

@Entity
@Data
@Table(name = "calificacion")
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ValorCalificacion")
    private BigDecimal valorCalificacion;
    @JoinColumn(name = "EstudianteID", referencedColumnName = "ID")
    @ManyToOne
    private Usuario estudianteID;
    @JoinColumn(name = "CursoID", referencedColumnName = "ID")
    @ManyToOne
    private Curso cursoID;

    
}
