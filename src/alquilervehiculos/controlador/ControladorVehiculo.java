/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Moto;
import alquilervehiculos.modelo.vehiculo.Vehiculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ControladorVehiculo implements Serializable {
    
    private List <Vehiculo> vehiculos;

    public ControladorVehiculo() {
        llenarVehiculos();
    }
    
    private void llenarVehiculos () {
        // Por ahora, escribirlos:
        vehiculos= new ArrayList<>();
        vehiculos.add(new Auto(true, "CMV678", 20000, true, "KIA", 2018, (double) 100000));
        vehiculos.add(new Moto(true, "MDX456", 5000, true, "SUSUKI", 2020, (double)30000));
        
        // Ahora, cargarlo por CSV:
        // MIRAR IMPORTARCSV...
    }
    
    
}