/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
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

    public ControladorVehiculo() {
        llenarVehiculos();
    }

    private void llenarVehiculos() {
        if (vehiculos == null || vehiculos.isEmpty()) {
            vehiculos = ImportarCSV.cargarVehiculos();
        }
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
