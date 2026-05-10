package org.getfin.reportes;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.getfin.modelos.Visita;

import java.util.*;

public class ReporteVisita {

    public static void generarReporte(List<Visita> visitas) {
        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(visitas);

            JasperReport reporte = JasperCompileManager.compileReport("reporte_visitas.jrxml");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("TITULO_REPORTE", "Listado de Visitas");

            JasperPrint print = JasperFillManager.fillReport(reporte, parametros, dataSource);

            // 🔹 Mostrar en una ventana (pantallita)
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setVisible(true);

        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
