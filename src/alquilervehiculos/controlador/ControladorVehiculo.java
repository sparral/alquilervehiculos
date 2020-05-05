/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.TipoMarca;
import alquilervehiculos.utilidades.ImportarCSV;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ControladorVehiculo implements Serializable {

    private List<AbstractVehiculo> vehiculos;
    private List<TipoMarca> marcas;

    public ControladorVehiculo() {
        llenarMarcas();
        llenarVehiculos();
    }

    private void llenarVehiculos() {
        if (vehiculos == null || vehiculos.isEmpty()) {
            vehiculos = ImportarCSV.cargarVehiculos(marcas);
        }
    }

    public List<AbstractVehiculo> getVehiculos() {
        return vehiculos;
    }

    private void llenarMarcas() {
        marcas = new ArrayList<>();
        byte cont=0;
        // Marcas de Autos:
        String[] marcasAuto = {"Chevrolet", "Kia", "Mazda", "Nissan"};
        for (String marcaEspecifica : marcasAuto) {
            marcas.add(new TipoMarca(cont,"Auto", marcaEspecifica));
            cont++;
        }
        
        // Marcas de Motos:
        String[] marcasMoto = {"AKT", "Honda", "Kawasaki", "Susuki", "Yamaha"};
        for (String marcaEspecifica : marcasMoto) {
            marcas.add(new TipoMarca(cont,"Moto", marcaEspecifica));
            cont++;
        }

        // Marcas de Furgonetas:
        String[] marcasFurgoneta = {"Fiat", "Ford", "Mercedes", "Renault"};
        for (String marcaEspecifica : marcasFurgoneta) {
            marcas.add(new TipoMarca(cont,"Furgoneta", marcaEspecifica));
            cont++;
        }
    }

    public List<TipoMarca> getMarcas() {
        return marcas;
    }

    public List<AbstractVehiculo> obtenerListaVehiculos(String tipo) {
        // Me obtiene la lista correspondiente de vehículos para la tabla:        
        List<AbstractVehiculo> listaTemp = new ArrayList<>();

        for (AbstractVehiculo seleccionado : this.vehiculos) {

            if (seleccionado.getClass().getSimpleName().compareTo(tipo) == 0) {
                // Es selectivo con respecto al tipo de vehículo,
                listaTemp.add(seleccionado);
            } else if (tipo.isEmpty()) {
                // Significa que quiero TODA la lista:
                listaTemp.add(seleccionado);
            }
        }

        return listaTemp;
    }

}
