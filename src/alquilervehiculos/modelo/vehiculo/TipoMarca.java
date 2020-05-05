/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo.vehiculo;

import java.io.Serializable;

/**
 *
 * @author Santy
 */
public class TipoMarca implements Serializable {
    // private byte codigo;
    private String tipoVehiculo;
    private String marca;    

    public TipoMarca(String tipoVehiculo, String marca) {
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return this.marca;
    }

    
    
}
