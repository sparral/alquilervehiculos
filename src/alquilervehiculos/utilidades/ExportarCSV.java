/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.usuario.Cliente;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Furgoneta;
import alquilervehiculos.modelo.vehiculo.Moto;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
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

    public static void generarReporteEntrega(String[] datos) {
        LocalDate fecha = LocalDate.now();
        String mes = fecha.getMonth().getDisplayName(TextStyle.FULL,
                new Locale("es", "ES")) + "-" + fecha.getYear();
        // Nombre del archivo :"Reportes/CSVs/julio_2020/Auto/23_CMR534.csv"
        String salidaArchivo = "Reportes/Entrega/" + mes + "/" + datos[0] + "/"
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
            // espejos, tipoPago, valorPago, fechaInicio, fechaFinal, observaciones

            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            // Datos para escribir en filas:
            salidaCSV.write("Vehiculo   : " + datos[0]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Matricula  : " + datos[1]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Cliente    : " + datos[2]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Estado     : " + datos[3]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Gasolina   : " + datos[4]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Limpieza   : " + datos[5]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("Espejos    : " + datos[6]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("FechaInicio: " + datos[7]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("FechaFinal : " + datos[8]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("TipoPago   : " + datos[9]);
            salidaCSV.endRecord();          // Deja de escribir en el archivo
            salidaCSV.write("ValorPago  : $" + datos[10] + " COP");
            salidaCSV.endRecord();          // Deja de escribir en el archivo

            if (!datos[11].isEmpty()) {
                salidaCSV.endRecord();
                salidaCSV.write("Observaciones: " + datos[11]);
            }
            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generarReporteHoras(LocalDateTime fechaInicial,
            LocalDateTime fechaFinal, String tipo, List<String> datosAnteriores) {
        int dif = (int) fechaInicial.until(fechaFinal, ChronoUnit.DAYS);
        // Para conocer los nuevos datos:
        String difInicial = String.valueOf(fechaInicial.toLocalTime().getHour());
        String difFinal = String.valueOf(fechaFinal.toLocalTime().getHour());

        int lugar = 0;
        String[] datosNuevos = new String[dif + 1];
        for (int i = 0; i <= dif; i++) {
            LocalDate fecha = LocalDate.ofYearDay(fechaInicial.getYear(),
                    fechaInicial.getDayOfYear() + i);
            String fechaString = fecha.format(DateTimeFormatter
                    .ofPattern("dd/MM/yy"));
            switch (tipo) {
                case "Auto": {
                    if (i == 0) {
                        datosNuevos[i] = fechaString + "," + difInicial + ",0,0";
                    } else if (i == dif) {
                        datosNuevos[i] = fechaString + "," + difFinal + ",0,0";
                    } else {
                        datosNuevos[i] = fechaString + ",24,0,0";
                    }
                    lugar = 1;
                    break;
                }
                case "Moto": {
                    if (i == 0) {
                        datosNuevos[i] = fechaString + ",0," + difInicial + ",0";
                    } else if (i == dif) {
                        datosNuevos[i] = fechaString + ",0," + difFinal + ",0";
                    } else {
                        datosNuevos[i] = fechaString + ",0,24,0";
                    }
                    lugar = 2;
                    break;
                }
                case "Furgoneta": {
                    if (i == 0) {
                        datosNuevos[i] = fechaString + ",0,0," + difInicial;
                    } else if (i == dif) {
                        datosNuevos[i] = fechaString + ",0,0," + difFinal;
                    } else {
                        datosNuevos[i] = fechaString + ",0,0,24";
                    }
                    lugar = 3;
                    break;
                }
            }
        }

        // Ahora, para escribir en el archivo:
        String salidaArchivo = "Reportes/Promedio/" + tipo + ".csv";
        File archivo = new File(salidaArchivo);
        archivo.getParentFile().mkdirs();

        try {
            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            salidaCSV.write("Fecha");
            salidaCSV.write("Horas");
            salidaCSV.endRecord();

            String fechaN = fechaInicial.toLocalDate().format(DateTimeFormatter
                    .ofPattern("dd/MM/yy"));
            int cont = 0;
            String[] z = new String[4];

            for (String dato : datosAnteriores) {
                String[] x = dato.split(",");                   // Datos antiguos.
                if (fechaN.compareTo(x[0]) <= 0) {
                    String[] y = datosNuevos[cont].split(",");   // Datos nuevos.
                    cont++;
                    if (y[0].compareTo(x[0]) == 0) {
                        // Fecha ya existía, deben sumarse las horas:
                        int suma = Integer.parseInt(x[lugar])
                                + Integer.parseInt(y[lugar]);
                        switch (tipo) {
                            case "Auto": {
                                z[0] = x[0];
                                z[1] = String.valueOf(suma);
                                break;
                            }
                            case "Moto": {
                                z[0] = x[0];
                                z[2] = String.valueOf(suma);
                                break;
                            }
                            case "Furgoneta": {
                                z[0] = x[0];
                                z[3] = String.valueOf(suma);
                                break;
                            }
                        }
                        salidaCSV.writeRecord(z);
                    }
                } else {
                    salidaCSV.writeRecord(x);
                    salidaCSV.endRecord();
                }
            }
            // Finalmente, si faltó por escribir algunos datos nuevos:
            if (cont <= dif) {
                for (int i = cont; i <= dif; i++) {
                    salidaCSV.write(datosNuevos[cont]);
                    salidaCSV.endRecord();
                }
            }
            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
