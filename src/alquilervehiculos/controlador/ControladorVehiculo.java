/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Moto;
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
    
    private List <AbstractVehiculo> vehiculos;

    public ControladorVehiculo() {
        llenarVehiculos();
    }
    
    private void llenarVehiculos () {
        vehiculos=ImportarCSV.cargarVehiculos();
    }
    
    public List<AbstractVehiculo> obtenerListaVehiculos() {
        // Me obtiene la LISTA ENTERA de vehículos para mirarlo en la tabla:
        
        List <AbstractVehiculo> listaTemp = new ArrayList<>();
        
        for (AbstractVehiculo seleccionado: vehiculos) {
            listaTemp.add(seleccionado);
        }
        
        return listaTemp;
    }
    
}