/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author santi
 */
public class GenerarPDF {

    public void generarVehiculoPDF(List<AbstractVehiculo> vehiculos, String tipo)
            throws Exception {

        LocalDate fecha = LocalDate.now();
        // Nombre del archivo: "src/Reportes/PDFs/2020-05-05/Autos.pdf"
        String salidaArchivo = "src/Reportes/PDFs/" + fecha.toString()
                + tipo + ".pdf";
        File archivo = new File(salidaArchivo);
        archivo.getParentFile().mkdirs();

        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe
        // Si existe un archivo llamado asi lo borra
        if (existe) {
            archivo.delete();
        }

        PdfDocument documento = new PdfDocument(new PdfWriter(salidaArchivo));
        try (Document doc = new Document(documento)) {
            int numColumn = 4;
            if (tipo.compareTo("Todos") == 0) {
                numColumn = 5;
            }

            Table tabla = new Table(numColumn);
            // Encabezados: Disp, {Tipo}, Matricula, marca, año, kilometraje, adicional
            tabla.addHeaderCell(getEncabezado("Disponible"));
            if (tipo.compareTo("Todos") == 0) {
                tabla.addHeaderCell("Tipo");
            }
            tabla.addHeaderCell(getEncabezado("Matricula"));
            tabla.addHeaderCell(getEncabezado("Marca"));
            tabla.addHeaderCell(getEncabezado("Año"));
            tabla.addHeaderCell(getEncabezado("Kilometraje"));
//            switch (tipo) {
//                case "Autos": {
//                    tabla.addHeaderCell(getEncabezado("Extras"));
//                    break;
//                }
//                case "Motos": {
//                    tabla.addHeaderCell(getEncabezado("Casco"));
//                    break;
//                }
//                case "Furgonetas": {
//                    tabla.addHeaderCell(getEncabezado("Capacidad"));
//                    break;
//                }
//            }

            for (AbstractVehiculo seleccionado : vehiculos) {
                tabla.startNewRow();
                tabla.addCell(getDisponible(seleccionado.isEstado()));

                if (tipo.compareTo("Todos") == 0) {
                    tabla.addCell(seleccionado.getClass().getSimpleName());
                }

                tabla.addCell(seleccionado.getMatricula());
                tabla.addCell(seleccionado.getMarca().getMarca());
                tabla.addCell(seleccionado.getAnio());
                tabla.addCell(Integer.toString(seleccionado.getKilometraje()));
//                switch (tipo) {
//                    case "Autos": {
//                        
//                        break;
//                    }
//                    case "Motos": {
//                        
//                        break;
//                    }
//                    case "Furgonetas": {
//                        
//                        break;
//                    }
//                }
            }
            doc.add(tabla);
            doc.close();
        }

        // Finalmente, abre el archivo:
        abrirPDF(salidaArchivo);
    }

    private void abrirPDF(String src) throws IOException {
        File archivo = new File(src);
        Desktop.getDesktop().open(archivo);
    }

    private Cell getEncabezado(String texto) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Cell celda = new Cell().add(new Paragraph(texto).setFont(font).
                setFontColor(ColorConstants.BLACK));

        celda.setBackgroundColor(ColorConstants.DARK_GRAY);
        celda.setBorder(Border.NO_BORDER);
        celda.setTextAlignment(TextAlignment.CENTER);
        return celda;
    }

    private Cell getDisponible(boolean disp) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Cell celda = new Cell().add(new Paragraph(Boolean.toString(disp))
                .setFont(font).setFontColor(ColorConstants.BLACK));
        if (disp) {
            celda.setBackgroundColor(ColorConstants.GREEN);
        } else {
            celda.setBackgroundColor(ColorConstants.RED);
        }
        celda.setBorder(Border.NO_BORDER);
        celda.setTextAlignment(TextAlignment.CENTER);
        return celda;
    }
}
