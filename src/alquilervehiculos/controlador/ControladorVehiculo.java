/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Moto;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
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
        // Por ahora, escribirlos:
  
        vehiculos= new ArrayList<>();
        vehiculos.add(new Moto(true, "DMR585", 35000, true, "Yamaha", "2017", (double) 50000));
        vehiculos.add(new Auto(true, "CMR812", 40345, true, "Mazda", "2018", (double) 120000));
        vehiculos.add(new Auto(true, "DGS934", 12005, true, "Kia", "2019", (double) 100000));
        
        
        // Ahora, cargarlo por CSV:
        // MIRAR IMPORTARCSV...
    }
    
    public List<AbstractVehiculo> obtenerVehiculos() {
        // Me obtiene la LISTA ENTERA de vehículos para mirarlo en la tabla:
        
        List <AbstractVehiculo> listaTemp = new ArrayList<>();
        
        for (AbstractVehiculo seleccionado: vehiculos) {
            listaTemp.add(seleccionado);
        }
        
        return listaTemp;
    }
    
}