/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Furgoneta;
import alquilervehiculos.modelo.vehiculo.Moto;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author santi
 */
public class CrearPDF {

    public void generarVehiculoPDF(List<AbstractVehiculo> vehiculos, String tipo)
            throws Exception {

        LocalDate fecha = LocalDate.now();
        String mes = fecha.getMonth().getDisplayName(TextStyle.FULL,
                new Locale("es", "ES")) + "-" + fecha.getYear();

        // Nombre del archivo: "src/Reportes/PDFs/Mayo-2020/05_Autos.pdf"
        String salidaArchivo = "src/Reportes/PDFs/" + mes + "/"
                + fecha.getDayOfMonth() + "_" + tipo + ".pdf";
        File archivo = new File(salidaArchivo);
        archivo.getParentFile().mkdirs();

        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe
        // Si existe un archivo llamado asi lo borra
        if (existe) {
            archivo.delete();
        }

        PdfDocument documento = new PdfDocument(new PdfWriter(salidaArchivo));
        try (Document doc = new Document(documento)) {
            doc.setMargins(20, 15, 20, 15);

            float[] columnWidths = {3, 5, 5, 3, 6, 5};
            Table tabla = new Table(UnitValue.createPercentArray(columnWidths));
            tabla.setMarginTop(0);
            tabla.setMarginBottom(0);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            Cell arriba = new Cell(1, 6)
                    .add(new Paragraph(tipo).setFontSize(15).setFont(font)
                            .setBackgroundColor(new DeviceRgb(23, 23, 4))
                            .setFontColor(ColorConstants.WHITE)
                            .setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(arriba);

            String adicional = "";
            switch (tipo) {
                case "Autos": {
                    adicional = "Extras";
                    break;
                }
                case "Motos": {
                    adicional = "Casco";
                    break;
                }
                case "Furgonetas": {
                    adicional = "Capacidad (kg)";
                    break;
                }
            }
            // Encabezados: Disp, Matricula, marca, año, kilometraje, adicional
            for (int i = 0; i < 2; i++) {
                Cell[] encabezado = new Cell[]{
                    getEncabezado("Disponible"),
                    getEncabezado("Matricula"),
                    getEncabezado("Marca"),
                    getEncabezado("Año"),
                    getEncabezado("Kilometraje"),
                    getEncabezado(adicional)
                };
                for (Cell hfCell : encabezado) {
                    if (i == 0) {
                        tabla.addHeaderCell(hfCell);
                    } else {
                        tabla.addFooterCell(hfCell);
                    }
                }
            }

            for (AbstractVehiculo seleccionado : vehiculos) {
                tabla.startNewRow();
                tabla.addCell(getDisponible(seleccionado.isEstado()));

                tabla.addCell(getCelda(seleccionado.getMatricula()));
                tabla.addCell(getCelda(seleccionado.getMarca().getMarca()));
                tabla.addCell(getCelda(seleccionado.getAnio()));
                tabla.addCell(getCelda(Integer.toString(
                        seleccionado.getKilometraje())));
                switch (tipo) {
                    case "Autos": {
                        boolean extras = ((Auto) seleccionado).isExtras();
                        tabla.addCell(getCelda(Boolean.toString(extras)));
                        break;
                    }
                    case "Motos": {
                        boolean casco = ((Moto) seleccionado).isCasco();
                        tabla.addCell(getCelda(Boolean.toString(casco)));
                        break;
                    }
                    case "Furgonetas": {
                        short capacidad = ((Furgoneta) seleccionado).getCapacidad();
                        tabla.addCell(getCelda(Short.toString(capacidad)));
                        break;
                    }
                }
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
        Cell celda = new Cell().add(new Paragraph(texto).setFont(font)
                .setFontColor(ColorConstants.BLACK));

        celda.setBackgroundColor(new DeviceRgb(180, 180, 180));
        celda.setTextAlignment(TextAlignment.CENTER);
        return celda;
    }

    private Cell getDisponible(boolean disp) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Cell celda = new Cell().add(new Paragraph(Boolean.toString(disp))
                .setFont(font).setFontColor(ColorConstants.BLACK));
        if (disp) {
            celda.setBackgroundColor(new DeviceRgb(136, 202, 50));
        } else {
            celda.setBackgroundColor(new DeviceRgb(149, 36, 28));
        }
        celda.setTextAlignment(TextAlignment.CENTER);
        return celda;
    }

    private Cell getCelda(String texto) throws IOException {
        Cell celda = new Cell().add(new Paragraph(texto));
        celda.setTextAlignment(TextAlignment.CENTER);
        return celda;
    }
}
