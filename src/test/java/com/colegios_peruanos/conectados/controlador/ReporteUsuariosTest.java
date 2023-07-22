package com.colegios_peruanos.conectados.controlador;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.colegios_peruanos.conectados.modelos.Usuario;
import com.colegios_peruanos.conectados.util.ReporteUsuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteUsuariosTest {

    @Test
    public void testBuildExcelDocument() throws Exception {
        // Datos de ejemplo para el test
        List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Juan");
        usuario1.setApellido("Perez");
        usuario1.setCorreoElectronico("juan.perez@example.com");
        usuario1.setTipoUsuario("docente");
        usuario1.setFechaRegistro(new Date());
        usuarios.add(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Maria");
        usuario2.setApellido("Lopez");
        usuario2.setCorreoElectronico("maria.lopez@example.com");
        usuario2.setTipoUsuario("estudiante");
        usuario2.setFechaRegistro(new Date());
        usuarios.add(usuario2);

        // Mock del modelo que se pasa a la vista
        Map<String, Object> model = new HashMap<>();
        model.put("usuarios", usuarios);

        // Crear un objeto Workbook simulado utilizando XSSFWorkbook
        Workbook workbook = new XSSFWorkbook();

        // Crear la vista y construir el archivo Excel
        ReporteUsuarios reporteUsuarios = new ReporteUsuarios();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        reporteUsuarios.buildExcelDocument(model, workbook, request, response);

        // Verificación del contenido del archivo Excel
        byte[] contenido = response.getContentAsByteArray();

    }
}
