/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.usuario.Usuario;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santy
 */
public class ExportarCSV {

    public static void agregarUsuario(List<Usuario> usuarios) {
        
        // Primero, se verifica si el archivo existe:
        String salidaArchivo = "src/Usuarios.csv";         // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists();

        // Si ya existe un archivo así, se debe borrar:
        if (existe) {
            File archivoUsuarios = new File(salidaArchivo);
            archivoUsuarios.delete();
        }
        
        try {
            // Crea el archivo:
            CsvWriter salidaCSV = new CsvWriter(new FileWriter(salidaArchivo, true), ',');

            // Datos para identificar las columnas
            salidaCSV.write("Correo");
            salidaCSV.write("Password");
            salidaCSV.write("Tipo");
            salidaCSV.write("Nombre");
            salidaCSV.write("Apellido");
            salidaCSV.write("Cédula");
            salidaCSV.write("Edad");
            salidaCSV.write("Visión");
            salidaCSV.write("Auditivo");

            salidaCSV.endRecord();          // Deja de escribir en el archivo

            // Recorremos la lista y lo insertamos en el archivo:
            for (Usuario user : usuarios) {
                salidaCSV.write(user.getCorreo());
                salidaCSV.write(user.getPassword());
                salidaCSV.write(user.getTipousuario().toString());
                salidaCSV.write(user.getNombre());
                salidaCSV.write(user.getApellido());
                salidaCSV.write(user.getCedula());
                salidaCSV.write(Byte.toString(user.getEdad()));
                salidaCSV.write(Boolean.toString(user.isProblemasvision()));
                salidaCSV.write(Boolean.toString(user.isProblemasauditivos()));

                salidaCSV.endRecord(); // Deja de escribir en el archivo
            }

            salidaCSV.close(); // Cierra el archivo
        } catch (IOException ex) {
            Logger.getLogger(ExportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
