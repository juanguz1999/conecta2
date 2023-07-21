package com.colegios_peruanos.conectados.servicio;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.colegios_peruanos.conectados.dao.estudianteDao;
import com.colegios_peruanos.conectados.dao.gradoDao;
import com.colegios_peruanos.conectados.dao.seccionDao;
import com.colegios_peruanos.conectados.dao.usuarioDao;
import com.colegios_peruanos.conectados.modelos.Estudiante;
import com.colegios_peruanos.conectados.modelos.Grado;
import com.colegios_peruanos.conectados.modelos.Seccion;
import com.colegios_peruanos.conectados.modelos.Usuario;

@ExtendWith(MockitoExtension.class)
public class EstudianteServicioTests {

    @Mock
    private estudianteDao estudiantedao;

    @Mock
    private gradoServicio gradoservicio;

    @Mock
    private seccionServicio seccionservicio;

    @InjectMocks
    private estudianteServicio estudianteservicio;

    @Mock
    private seccionDao secciondao;

    @Mock
    private gradoDao gradodao;

    @Mock
    private usuarioDao usuariodao;
    
    @InjectMocks
    private usuarioServicio usuarioservicio;
    
    
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    private Seccion seccion;
    private Seccion seccion2;
    private Grado grado;
    private Grado grado2;
    private Usuario usuario;
    private Usuario usuario2;

    @BeforeEach
    void setup() {

        usuario=new Usuario();
        usuario.setId(1);
        usuario2=new Usuario();
        usuario2.setId(2);

        grado=new Grado();
        grado.setId(1);
        grado.setNombreGrado("1er grado (PRIMARIA)");
        seccion=new Seccion();
        seccion.setId(1);
        seccion.setNombreSeccion("A");
        seccion.setGradoID(grado);

        grado2=new Grado();
        grado2.setId(2);
        grado2.setNombreGrado("2do grado (PRIMARIA)");
        seccion2=new Seccion();
        seccion2.setId(7);
        seccion2.setNombreSeccion("A");
        seccion2.setGradoID(grado2);

        estudiante1 = new Estudiante();
        estudiante1.setId(1);
        estudiante1.setNombre("Pedro");
        estudiante1.setApellido("Jimenez");
        estudiante1.setGradoID(grado);
        estudiante1.setSeccionID(seccion);
        estudiante1.setUsuarioID(usuario);

        estudiante2 = new Estudiante();
        estudiante1.setId(2);
        estudiante2.setNombre("Jaime");
        estudiante2.setApellido("Raez");
        estudiante2.setGradoID(grado2);
        estudiante2.setSeccionID(seccion2);
        estudiante2.setUsuarioID(usuario2);

    }

    @DisplayName("test para listar estudiante")
    @Test
    void testListarEstudiantes(){

        //given

        given(estudiantedao.findAll()).willReturn(List.of(estudiante1,estudiante2));
        
        //when
        List<Estudiante> estudiantes=estudianteservicio.listar();

        //then
        assertThat(estudiantes).isNotNull();
        assertThat(estudiantes.size()).isEqualTo(2);

    }


    @DisplayName("test para buscar por grado y seccion")
    @Test
    void testListarEstudiantesGradoySeccion(){

        //given
        given(estudiantedao.findAllByGradoIDAndSeccionID(grado, seccion)).willReturn(List.of(estudiante1));

        //When
        List<Estudiante> estudiantes=estudianteservicio.buscarGradoSeccion(grado,seccion);

        //then
        assertThat(estudiantes).isNotNull();
        assertThat(estudiantes.size()).isEqualTo(1); 

    }

    @DisplayName("test para buscar por grado y seccion por ID")
    @Test
    void testListarEstudiantesGradoIDySeccionID(){

        //given

        given(gradoservicio.buscar(1)).willReturn(grado);
        given(gradoservicio.buscar(2)).willReturn(grado2);
        given(seccionservicio.buscar(1)).willReturn(seccion);
        given(seccionservicio.buscar(7)).willReturn(seccion2);

        given(estudiantedao.findAllByGradoIDAndSeccionID(grado, seccion)).willReturn(List.of(estudiante1));
        given(estudiantedao.findAllByGradoIDAndSeccionID(grado2, seccion2)).willReturn(List.of(estudiante2));

        //When
        List<Estudiante> estudiantes=estudianteservicio.buscarGradoSeccion(2,7);
        List<Estudiante> estudiantes2=estudianteservicio.buscarGradoSeccion(1,1);
        //then
        assertThat(estudiantes).isNotNull();
        assertThat(estudiantes.size()).isEqualTo(1); 

        assertThat(estudiantes2).isNotNull();
        assertThat(estudiantes2.size()).isEqualTo(1); 

    }


}
