/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.TipoUsuario;
import alquilervehiculos.modelo.Usuario;
import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Santy
 */
public class ImportarCSV {
    
    public  static List<Usuario> cargarUsuarios(TipoUsuario[] tipos) {
        List<Usuario> listadoUsuarios = new ArrayList<>();
        try {
            // Para encontrar el archivo CSV:
            CsvReader leerUsuarios = new CsvReader("src/usuarios.csv");
            leerUsuarios.readHeaders();
            
            // Se cargan los datos de los usuarios mientras existan líneas:
            while (leerUsuarios.readRecord()) {
                String correo = leerUsuarios.get(0);
                String password = leerUsuarios.get(1);
                
                // Se obtiene el valor del tipo y se convierte a TipoUsuario:
                byte tipo = Byte.parseByte(leerUsuarios.get(2));
                
                String nombre= leerUsuarios.get(3);
                String apellido= leerUsuarios.get(4);
                String cedula= leerUsuarios.get(5);
                byte edad= Byte.parseByte(leerUsuarios.get(6));
                boolean vision=Boolean.parseBoolean(leerUsuarios.get(7));
                boolean auditivo=Boolean.parseBoolean(leerUsuarios.get(8));
                
                listadoUsuarios.add(new Usuario(correo, password, tipos[tipo-1], 
                        nombre, apellido, cedula, edad, vision, auditivo));
                
            }
            leerUsuarios.close();           // Cerrar el archivo
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listadoUsuarios;
    }
}
