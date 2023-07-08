package com.colegios_peruanos.conectados.util;

import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.colegios_peruanos.conectados.modelos.Usuario;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("administrarPerfil.xlsx")
public class ReporteUsuarios extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"reporte-usuarios.xlsx\"");
        Sheet hoja = workbook.createSheet("Reporte-usuarios");

        Row filaTittulo = hoja.createRow(0);
        Cell celda = filaTittulo.createCell(0);

        celda.setCellValue("REPORTE DE USUARIOS");

        Row filaData = hoja.createRow(2);
        String[] columnas = { "N°", "Nombre", "Apellido", "Correo Electronico", "Tipo de Usuario",
                "Fecha Registro" };

        for (int i = 0; i < columnas.length; i++) {
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
        }

        List<Usuario> usuarios = (List<Usuario>) model.get("usuarios");

        int numFila = 3;

        for (Usuario operacion : usuarios) {

            filaData = hoja.createRow(numFila);

            filaData.createCell(0).setCellValue(numFila - 2);
            filaData.createCell(1).setCellValue(operacion.getNombre());
            filaData.createCell(2).setCellValue(operacion.getApellido());
            filaData.createCell(3).setCellValue(operacion.getCorreoElectronico());
            filaData.createCell(4).setCellValue(operacion.getTipoUsuario());
            filaData.createCell(5).setCellValue(operacion.getFechaRegistro());

            numFila++;
        }

    }

}
