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
import alquilervehiculos.modelo.vehiculo.TipoMarca;
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

    public static List<AbstractVehiculo> cargarVehiculos(List<TipoMarca> marcas) {
        List<AbstractVehiculo> listadoVehiculos = new ArrayList<>();
        String[] srcs = {"src/Autos.csv", "src/Motos.csv", "src/Furgonetas.csv"};
        try {
            for (String ubicacion : srcs) {
                // Para encontrar el archivo CSV:
                CsvReader leer = new CsvReader(ubicacion);
                leer.readHeaders();

                // Se cargan los datos de los vehiculos mientras existan líneas:
                while (leer.readRecord()) {
                    boolean estado = Boolean.parseBoolean(leer.get(0));
                    String matricula = leer.get(1);
                    String anio = leer.get(3);
                    int kilometraje = Integer.parseInt(leer.get(4));
                    int [] valorAlquiler = {Integer.parseInt(leer.get(5)),
                        Integer.parseInt(leer.get(6))};
                    
                    // Valores que dependen del Vehiculo: 
                    switch (ubicacion) {
                        case "src/Autos.csv": {
                            boolean extras = Boolean.parseBoolean(leer.get(7));

                            listadoVehiculos.add(new Auto(extras, matricula,
                                    kilometraje, estado, 
                                    marcas.get(marcas.indexOf(new TipoMarca("Auto",leer.get(2)))), 
                                    anio, valorAlquiler));
                            break;
                        }
                        case "src/Motos.csv": {
                            boolean casco = Boolean.parseBoolean(leer.get(7));

                            listadoVehiculos.add(new Moto(casco, matricula,
                                    kilometraje, estado, 
                                    marcas.get(marcas.indexOf(new TipoMarca("Moto",leer.get(2)))), 
                                    anio, valorAlquiler));
                            break;
                        }
                        case "src/Furgonetas.csv": {
                            short capacidad = Short.parseShort(leer.get(7));

                            listadoVehiculos.add(new Furgoneta(capacidad, matricula,
                                    kilometraje, estado, 
                                    marcas.get(marcas.indexOf(new TipoMarca("Furgoneta",leer.get(2)))), 
                                    anio, valorAlquiler));
                            break;
                        }
                    }
                }
                leer.close();           // Cerrar el archivo
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Archivo" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listadoVehiculos;
    }
}
