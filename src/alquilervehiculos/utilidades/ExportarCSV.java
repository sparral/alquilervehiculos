/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.usuario.Usuario;
import com.csvreader.CsvWriter;
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

        // Nombre del archivo
        String salidaArchivo = "src/Usuarios.csv";
        try {
            CsvWriter salidaCSV = new CsvWriter(salidaArchivo);
            // Datos para escribir las columnas
            salidaCSV.write("Correo");
            salidaCSV.write("Password");
            salidaCSV.write("Tipo");
            salidaCSV.write("Nombre");
            salidaCSV.write("Apellido");
            salidaCSV.write("Edad");
            salidaCSV.write("Visión");
            salidaCSV.write("Auditivo");

            salidaCSV.endRecord();          // Deja de escribir en el archivo
            
            // Luego, recorre la lista, extrae los datos y escribe en el CSV:
            for (Usuario user : usuarios) {
                String[] datos = user.getArray();
                salidaCSV.writeRecord(datos);
            }

            salidaCSV.endRecord();      // Deja de escribir en el archivo

            salidaCSV.close();
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
