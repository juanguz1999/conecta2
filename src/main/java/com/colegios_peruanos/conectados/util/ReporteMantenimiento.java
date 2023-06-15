package com.colegios_peruanos.conectados.util;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.colegios_peruanos.conectados.modelos.Mantenimiento;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("mantenimiento/administrarMantenimiento.xlsx")
    public class ReporteMantenimiento extends AbstractXlsxView {

        @Override
        protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
                HttpServletResponse response) throws Exception {
            response.setHeader("Content-Disposition", "attachment; filename=\"reporte-Mantenimiento.xlsx\"");
            Sheet hoja = workbook.createSheet("Reporte-Mantenimiento");

            Row filaTittulo = hoja.createRow(0);
            Cell celda = filaTittulo.createCell(0);

            celda.setCellValue("REPORTE DE ACCIONES DE MANTENIMIENTO");

            Row filaData = hoja.createRow(2);
            String[] columnas = { "N°", "Ticket", "Descripción","Tipo de Mantenimiento","Fecha de programación/solución", "Estado", "Observaciones","Usuario designado" };

            for (int i = 0; i < columnas.length; i++) {
                celda = filaData.createCell(i);
                celda.setCellValue(columnas[i]);
            }

            List<Mantenimiento> operaciones = (List<Mantenimiento>) model.get("operaciones");

            int numFila = 3;

            for (Mantenimiento operacion : operaciones) {

                filaData = hoja.createRow(numFila);

                filaData.createCell(0).setCellValue(numFila - 2);
                filaData.createCell(1).setCellValue(operacion.getId());
                filaData.createCell(2).setCellValue(operacion.getDescripcion());
                filaData.createCell(3).setCellValue(operacion.getTipoMantenimiento());
                filaData.createCell(4).setCellValue(operacion.getFechaHoraProgramada());
                filaData.createCell(5).setCellValue(operacion.getEstado());
                filaData.createCell(6).setCellValue(operacion.getObservaciones());
                filaData.createCell(7).setCellValue(operacion.getUsuarioID().getNombre()+" "+operacion.getUsuarioID().getApellido());

                numFila++;
            }

        }

    }
