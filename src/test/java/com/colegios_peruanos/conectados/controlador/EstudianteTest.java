package com.colegios_peruanos.conectados.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.colegios_peruanos.conectados.modelos.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EstudianteTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        void testGuardarNotas() throws Exception {
                // Arrange
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guardarNotas");

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                                .andExpect(MockMvcResultMatchers.redirectedUrl("registrarNotas"))
                                .andExpect(MockMvcResultMatchers.redirectedUrlTemplate("registrarNotas"))
                                .andExpect(MockMvcResultMatchers.view().name("redirect:registrarNotas"));

        }

        @Test
        void testObtenerCalificaciones() throws Exception {
                // Arrange
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/obtenerCalificaciones")
                                .param("gradoId", "2")
                                .param("seccionId", "7")
                                .param("cursoId", "4");

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.3.PC1").value("15.0"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.3.PC2").value("15.0"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.3.PC3").value("15.0"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.3.EXFINAL").value("15.0"));
        }

        @Test
        void testObtenerSecciones() throws Exception {
                // Arrange
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/obtenerSecciones")
                                .param("gradoId", "1");

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombreSeccion").value("A"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombreSeccion").value("B"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[2].nombreSeccion").value("C"));
        }

        @Test
        void testObtenerCursosPorGradoYSeccion() throws Exception {
                // Arrange
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/obtenerCursos")
                                .param("gradoId", "2")
                                .param("seccionId", "7");

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombreCurso").value("Historia"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nombreCurso").value("Geometria"));
        }

        @Test
        void testObtenerEstudiantesPorGradoYSeccion() throws Exception {
                // Arrange
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/obtenerEstudiantes")
                                .param("gradoId", "2")
                                .param("seccionId", "7");

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Estudiante_3"));
        }

        @Test
        void testObtenerEstudiantesPorGradoYSeccionYcurso() throws Exception {
                // Arrange
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/obtenerEstudiantesCurso")
                                .param("gradoId", "2")
                                .param("seccionId", "7")
                                .param("cursoId", "4");

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre").value("Estudiante_3"));
        }

        @Test
        void testObtenerAsistenciaporEstudianteIdyfecha() throws Exception {
                // Arrange
                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/obtenerAsistencia")
                                .param("estudianteId", "1");

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaAsistencia").value("2023-07-16"))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.estadoAsistencia").value("Si"));
        }

        @Test
        void testGuardarAsistencias() throws Exception {
                // Arrange
                List<Map<String, Object>> mapAsistencias = new ArrayList<>();

                Map<String, Object> asistencia1 = new HashMap<>();
                asistencia1.put("estudianteID", "1");
                asistencia1.put("estadoAsistencia", "Si");
                asistencia1.put("observaciones", "con observaciones");
                mapAsistencias.add(asistencia1);

                Map<String, Object> asistencia2 = new HashMap<>();
                asistencia2.put("estudianteID", "2");
                asistencia2.put("estadoAsistencia", "No");
                asistencia2.put("observaciones", "Falta injustificada");
                mapAsistencias.add(asistencia2);

                ObjectMapper objectMapper = new ObjectMapper();
                String requestBody = objectMapper.writeValueAsString(mapAsistencias);

                MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/guardarAsistencia")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody);

                // Act & Assert
                mockMvc.perform(requestBuilder)
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string("Datos recibidos correctamente"));
        }

        @Test
        public void testGuardarNotasEstudiante() throws Exception {
                // Datos de ejemplo
                String mapCalificaciones = "[{" +
                                "\"estudianteID\": \"3\"," +
                                "\"PC1\": \"15\"," +
                                "\"PC2\": \"15\"," +
                                "\"PC3\": \"15\"," +
                                "\"EXFINAL\": \"15\"," +
                                "\"gradoId\": \"2\"," +
                                "\"seccionId\": \"7\"," +
                                "\"cursoId\": \"4\"" +
                                "}]";

                // Realizar la solicitud y verificar el resultado
                mockMvc.perform(MockMvcRequestBuilders.post("/guardarNotasEstudiante")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapCalificaciones))
                                .andExpect(MockMvcResultMatchers.status().isOk())
                                .andExpect(MockMvcResultMatchers.content().string("Datos recibidos correctamente"));
        }

        @Test
        public void testVerificarDatosGuardados() throws Exception {
                // Caso 1: Usuario existente
                mockMvc.perform(MockMvcRequestBuilders.get("/VerificarDatosGuardadosAdmin/{email}",
                                "e0001@alincoln.edu.pe"))
                                .andExpect(MockMvcResultMatchers.redirectedUrl("/vistaprincipal"));

        }

        @Test
        public void testGuardarUsuario() throws Exception {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                // Caso 1: Usuario con tipo válido
                mockMvc.perform(MockMvcRequestBuilders.post("/guardarUsuario")
                                .param("nombre", "John")
                                .param("apellido", "Doe")
                                .param("correoElectronico", "john.doe@example.com")
                                .param("tipoUsuario", "docente"))
                                .andExpect(MockMvcResultMatchers.redirectedUrl("/vistaprincipal"));

        }

}