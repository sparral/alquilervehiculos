/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Furgoneta;
import alquilervehiculos.modelo.vehiculo.Moto;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santy
 */
public class ExportarCSV {

    public static void agregarUsuarioCSV(List<Usuario> usuarios) {

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

    public static void agregarVehiculoCSV(List<AbstractVehiculo> vehiculos, String salidaArchivo) {

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
            salidaCSV.write("ValorAlquiler");

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

}
