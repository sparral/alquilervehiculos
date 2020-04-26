/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.usuario.TipoUsuario;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Furgoneta;
import alquilervehiculos.modelo.vehiculo.Moto;
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
                byte edad = Byte.parseByte(leerUsuarios.get(5));
                boolean vision = Boolean.parseBoolean(leerUsuarios.get(6));
                boolean auditivo = Boolean.parseBoolean(leerUsuarios.get(7));

                listadoUsuarios.add(new Usuario(correo, password, tipos[tipo - 1],
                        nombre, apellido, edad, vision, auditivo));

            }
            leerUsuarios.close();           // Cerrar el archivo
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listadoUsuarios;
    }

    public static List<AbstractVehiculo> cargarVehiculos() {
        List<AbstractVehiculo> listadoVehiculos = new ArrayList<>();
        try {
            // Para cargar los AUTOS:
            CsvReader leerAutos = new CsvReader("src/Autos.csv");
            leerAutos.readHeaders();

            while (leerAutos.readRecord()) {
                boolean estado = Boolean.parseBoolean(leerAutos.get(0));
                String matricula = leerAutos.get(1);
                String marca = leerAutos.get(2);
                String anio = leerAutos.get(3);
                int kilometraje = Integer.parseInt(leerAutos.get(4));
                double valorAlquiler = Double.parseDouble(leerAutos.get(5));
                boolean extras = Boolean.parseBoolean(leerAutos.get(6));

                listadoVehiculos.add(new Auto(extras, matricula, kilometraje,
                        estado, marca, anio, valorAlquiler));

            }
            leerAutos.close();           // Cerrar el archivo

            // Para cargar las FURGONETAS:
            CsvReader leerFurgonetas = new CsvReader("src/Furgonetas.csv");
            leerFurgonetas.readHeaders();

            while (leerFurgonetas.readRecord()) {
                boolean estado = Boolean.parseBoolean(leerFurgonetas.get(0));
                String matricula = leerFurgonetas.get(1);
                String marca = leerFurgonetas.get(2);
                String anio = leerFurgonetas.get(3);
                int kilometraje = Integer.parseInt(leerFurgonetas.get(4));
                double valorAlquiler = Double.parseDouble(leerFurgonetas.get(5));
                short capacidad = Short.parseShort(leerFurgonetas.get(6));

                listadoVehiculos.add(new Furgoneta(capacidad, matricula,
                        kilometraje, estado, marca, anio, valorAlquiler));

            }
            leerFurgonetas.close();           // Cerrar el archivo

            // Para cargar las MOTOS:
            CsvReader leerMotos = new CsvReader("src/Motos.csv");
            leerMotos.readHeaders();

            while (leerMotos.readRecord()) {
                boolean estado = Boolean.parseBoolean(leerMotos.get(0));
                String matricula = leerMotos.get(1);
                String marca = leerMotos.get(2);
                String anio = leerMotos.get(3);
                int kilometraje = Integer.parseInt(leerMotos.get(4));
                double valorAlquiler = Double.parseDouble(leerMotos.get(5));
                boolean casco= Boolean.parseBoolean(leerMotos.get(6));

                listadoVehiculos.add(new Moto(casco, matricula, kilometraje, 
                        estado, marca, anio, valorAlquiler));

            }
            leerMotos.close();           // Cerrar el archivo

        } catch (FileNotFoundException ex) {
            System.out.println("Archivo" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listadoVehiculos;
    }
}
