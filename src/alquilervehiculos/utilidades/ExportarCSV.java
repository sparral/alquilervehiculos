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
import java.util.List;
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
        LocalDate fechaDevolucion = LocalDate.now();
        // Nombre del archivo
        String salidaArchivo = datos[0] + "/" + fechaDevolucion.toString();
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe

        // Si existe un archivo llamado asi lo borra
        if (existe) {
            File archivoClientes = new File(salidaArchivo);
            archivoClientes.delete();
        }
        try {
            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            // Datos para escribir las columnas:
            salidaCSV.write("Matricula");
            salidaCSV.write("Cliente");
            salidaCSV.write("Estado");
            salidaCSV.write("Gasolina");
            salidaCSV.write("Limpieza");
            salidaCSV.write("Espejos");
            salidaCSV.write("ValorPago");
            salidaCSV.endRecord();          // Deja de escribir en el archivo

            String[] medio = {datos[0], datos[1], datos[2], datos[3], datos[4], 
                datos[5],datos[6]};
            salidaCSV.writeRecord(medio);
            salidaCSV.endRecord();          // Deja de escribir en el archivo

            salidaCSV.endRecord();
            salidaCSV.write(datos[7]);

            salidaCSV.endRecord();      // Deja de escribir en el archivo
            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
