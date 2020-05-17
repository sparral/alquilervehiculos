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

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return this.marca;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TipoMarca) {
            // El objeto ya se puede comportar como un TipoMarca:
            TipoMarca other=(TipoMarca)obj;
            if (other.marca.compareTo(this.marca)==0 && 
                    other.tipoVehiculo.compareTo(this.tipoVehiculo)==0) {
                return true;
            }
        }
        return false;
    }
}
