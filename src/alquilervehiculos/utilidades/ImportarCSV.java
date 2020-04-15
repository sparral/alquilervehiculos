/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.usuario.TipoUsuario;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Vehiculo;
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

    public static List<Usuario> cargarUsuarios(TipoUsuario[] tipos) {
        List<Usuario> listadoUsuarios = new ArrayList<>();
        try {
            // Para encontrar el archivo CSV:
            CsvReader leerUsuarios = new CsvReader("src/Usuarios.csv");
            leerUsuarios.readHeaders();

            // Se cargan los datos de los usuarios mientras existan líneas:
            while (leerUsuarios.readRecord()) {
                String correo = leerUsuarios.get(0);
                String password = leerUsuarios.get(1);

                // Se obtiene el valor del tipo y se convierte a TipoUsuario:
                byte tipo = Byte.parseByte(leerUsuarios.get(2));

                String nombre = leerUsuarios.get(3);
                String apellido = leerUsuarios.get(4);
                String cedula = leerUsuarios.get(5);
                byte edad = Byte.parseByte(leerUsuarios.get(6));
                boolean vision = Boolean.parseBoolean(leerUsuarios.get(7));
                boolean auditivo = Boolean.parseBoolean(leerUsuarios.get(8));

                listadoUsuarios.add(new Usuario(correo, password, tipos[tipo - 1],
                        nombre, apellido, cedula, edad, vision, auditivo));

            }
            leerUsuarios.close();           // Cerrar el archivo
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listadoUsuarios;
    }

    public static List<Vehiculo> cargarVehiculos() {
        List<Vehiculo> listadoVehiculos = new ArrayList<>();
        try {
            // Para encontrar el archivo CSV:
            CsvReader leerVehiculos = new CsvReader("src/vehiculos.csv");
            leerVehiculos.readHeaders();

            // Se cargan los datos de los usuarios mientras existan líneas:
            while (leerVehiculos.readRecord()) {
                String tipo = leerVehiculos.get(0);
                String matricula = leerVehiculos.get(1);
                int kilometraje = Integer.parseInt(leerVehiculos.get(2));
                boolean estado= Boolean.parseBoolean(leerVehiculos.get(3));
                String marca= leerVehiculos.get(4);
                int año = Integer.parseInt(leerVehiculos.get(5));
                double valorAlquiler= Double.parseDouble(leerVehiculos.get(6));
                
                // Necesito identificar si agregar un auto, furgoneta o moto:
                
                

            }
            leerVehiculos.close();           // Cerrar el archivo
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listadoVehiculos;
    }
}
