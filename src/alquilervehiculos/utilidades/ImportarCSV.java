/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.Cliente;
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
import java.time.LocalDate;
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
                // Se obtiene el valor del tipo y se convierte a TipoUsuario:
                byte tipo = Byte.parseByte(leerUsuarios.get(2));

                // String correo, String password, TipoUsuario tipo, String nombre,
                // String apellido, byte edad, boolean vision, boolean auditivo 
                listadoUsuarios.add(new Usuario(leerUsuarios.get(0),
                        leerUsuarios.get(1), tipos[tipo - 1],
                        leerUsuarios.get(3), leerUsuarios.get(4),
                        Byte.parseByte(leerUsuarios.get(5)),
                        Boolean.parseBoolean(leerUsuarios.get(6)),
                        Boolean.parseBoolean(leerUsuarios.get(7))));
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
                    int[] valorAlquiler = {Integer.parseInt(leer.get(5)),
                        Integer.parseInt(leer.get(6))};
                    int contAlquiler = Integer.parseInt(leer.get(8));
                    boolean activado = Boolean.parseBoolean(leer.get(9));

                    // Valores que dependen del Vehiculo: 
                    switch (ubicacion) {
                        case "src/Autos.csv": {
                            boolean extras = Boolean.parseBoolean(leer.get(7));

                            listadoVehiculos.add(new Auto(extras, matricula,
                                    kilometraje, estado, marcas.get(marcas
                                            .indexOf(new TipoMarca("Auto", leer.get(2)))),
                                    anio, valorAlquiler, contAlquiler, activado));
                            break;
                        }
                        case "src/Motos.csv": {
                            boolean casco = Boolean.parseBoolean(leer.get(7));

                            listadoVehiculos.add(new Moto(casco, matricula,
                                    kilometraje, estado, marcas.get(marcas
                                            .indexOf(new TipoMarca("Moto", leer.get(2)))),
                                    anio, valorAlquiler, contAlquiler, activado));
                            break;
                        }
                        case "src/Furgonetas.csv": {
                            short capacidad = Short.parseShort(leer.get(7));

                            listadoVehiculos.add(new Furgoneta(capacidad, matricula,
                                    kilometraje, estado, marcas.get(marcas
                                            .indexOf(new TipoMarca("Furgoneta", leer.get(2)))),
                                    anio, valorAlquiler, contAlquiler, activado));
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

    public static List<Cliente> cargarClientes() {
        List<Cliente> listadoClientes = new ArrayList<>();
        try {
            // Para encontrar el archivo CSV:
            CsvReader leerClientes = new CsvReader("src/Clientes.csv");
            leerClientes.readHeaders();

            // Se cargan los datos de los usuarios mientras existan líneas:
            while (leerClientes.readRecord()) {
                // String userID, String matricula, LocalDate fechaInicial, 
                // LocalDate fechaFinal, String tipoPago 
                listadoClientes.add(new Cliente(leerClientes.get(0), leerClientes.get(1),
                        LocalDate.parse(leerClientes.get(2)),
                        LocalDate.parse(leerClientes.get(3)), leerClientes.get(4)));
            }
            leerClientes.close();           // Cerrar el archivo
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo" + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImportarCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listadoClientes;
    }
}
