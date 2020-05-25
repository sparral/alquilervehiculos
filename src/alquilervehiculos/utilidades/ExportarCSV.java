/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.Cliente;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Furgoneta;
import alquilervehiculos.modelo.vehiculo.Moto;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santy
 */
public class ExportarCSV {

    public static void usuarioCSV(List<Usuario> usuarios) {

        String salidaArchivo = "src/Usuarios.csv";         // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe

        // Si existe un archivo llamado asi lo borra
        if (existe) {
            File archivoUsuarios = new File(salidaArchivo);
            archivoUsuarios.delete();
        }
        try {
            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            // Datos para escribir las columnas:
            salidaCSV.write("Correo");
            salidaCSV.write("Password");
            salidaCSV.write("Tipo");
            salidaCSV.write("Nombre");
            salidaCSV.write("Apellido");
            salidaCSV.write("Edad");
            salidaCSV.write("Vision");
            salidaCSV.write("Auditivo");

            salidaCSV.endRecord();          // Deja de escribir en el archivo

            // Luego, recorre la lista, extrae los datos y escribe en el CSV:
            for (Usuario user : usuarios) {
                String[] datos = user.getArrayCSV();
                salidaCSV.writeRecord(datos);
            }
            salidaCSV.endRecord();      // Deja de escribir en el archivo
            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void vehiculoCSV(List<AbstractVehiculo> vehiculos) {
        String salidaArchivo = "";                      // Nombre del archivo
        if (vehiculos.get(0) instanceof Auto) {
            salidaArchivo = "src/Autos.csv";
        } else if (vehiculos.get(0) instanceof Moto) {
            salidaArchivo = "src/Motos.csv";
        } else if (vehiculos.get(0) instanceof Furgoneta) {
            salidaArchivo = "src/Furgonetas.csv";
        }

        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe
        // Si existe un archivo llamado asi lo borra
        if (existe) {
            File archivoVehiculos = new File(salidaArchivo);
            archivoVehiculos.delete();
        }
        try {
            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            // Datos para escribir las columnas:
            salidaCSV.write("Estado");
            salidaCSV.write("Matricula");
            salidaCSV.write("Marca");
            salidaCSV.write("Anio");
            salidaCSV.write("Kilometraje");
            salidaCSV.write("ValorDia");
            salidaCSV.write("ValorKm");

            switch (salidaArchivo) {
                case "src/Autos.csv": {
                    salidaCSV.write("Extras");
                    break;
                }
                case "src/Motos.csv": {
                    salidaCSV.write("Casco");
                    break;
                }
                case "src/Furgonetas.csv": {
                    salidaCSV.write("Capacidad");
                    break;
                }
            }
            salidaCSV.write("NumAlquiler");
            salidaCSV.write("Activado");
            salidaCSV.endRecord();     // Deja de escribir en el archivo

            // Luego, recorre la lista, extrae los datos y escribe en el CSV:
            for (AbstractVehiculo vehiculo : vehiculos) {
                switch (salidaArchivo) {
                    case "src/Autos.csv": {
                        String[] datos = ((Auto) vehiculo).getArrayCSV();
                        salidaCSV.writeRecord(datos);
                        break;
                    }
                    case "src/Motos.csv": {
                        String[] datos = ((Moto) vehiculo).getArrayCSV();
                        salidaCSV.writeRecord(datos);
                        break;
                    }
                    case "src/Furgonetas.csv": {
                        String[] datos = ((Furgoneta) vehiculo).getArrayCSV();
                        salidaCSV.writeRecord(datos);
                        break;
                    }
                }
            }
            salidaCSV.endRecord();     // Deja de escribir en el archivo
            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void clienteCSV(List<Cliente> clientes) {

        String salidaArchivo = "src/Clientes.csv";         // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe

        // Si existe un archivo llamado asi lo borra
        if (existe) {
            File archivoClientes = new File(salidaArchivo);
            archivoClientes.delete();
        }
        try {
            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            // Datos para escribir las columnas:
            salidaCSV.write("userID");
            salidaCSV.write("Matricula");
            salidaCSV.write("FechaInicial");
            salidaCSV.write("FechaFinal");
            salidaCSV.write("TipoPago");

            salidaCSV.endRecord();          // Deja de escribir en el archivo

            // Luego, recorre la lista, extrae los datos y escribe en el CSV:
            for (Cliente user : clientes) {
                String[] datos = user.getArrayCSV();
                salidaCSV.writeRecord(datos);
            }
            salidaCSV.endRecord();      // Deja de escribir en el archivo
            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generarReporte(String[] datos) {
        LocalDate fecha = LocalDate.now();
        String mes = fecha.getMonth().getDisplayName(TextStyle.FULL,
                new Locale("es", "ES")) + "-" + fecha.getYear();
        // Nombre del archivo :"src/Reportes/CSVs/julio_2020/Auto/23_CMR534.csv"
        String salidaArchivo = "src/Reportes/CSVs/" + mes + "/" + datos[0] + "/"
                + fecha.getDayOfMonth() + "_" + datos[1] + ".csv";
        File archivo = new File(salidaArchivo);
        archivo.getParentFile().mkdirs();

        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe
        // Si existe un archivo llamado asi lo borra
        if (existe) {
            archivo.delete();
        }
        try {
            // tipo, matricula, cliente, estadoVehiculo, gasolina, limpieza, 
            // espejos, tipoPago, valorPago, observaciones

            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            // Datos para escribir en filas:
            salidaCSV.write("Vehiculo : " + datos[0]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Matricula: " + datos[1]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Cliente  : " + datos[2]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Estado   : " + datos[3]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Gasolina : " + datos[4]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Limpieza : " + datos[5]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Espejos  : " + datos[6]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("TipoPago : " + datos[7]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("ValorPago: $" + datos[8] + " COP");
            salidaCSV.endRecord();          // Deja de escribir en el archivo

            if (!datos[8].isEmpty()) {
                salidaCSV.endRecord();
                salidaCSV.write("Observaciones: " + datos[8]);
            }
            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
