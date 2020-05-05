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

    private final byte index;
    private String tipoVehiculo;
    private String marca;

    public TipoMarca(byte index, String tipoVehiculo, String marca) {
        this.index = index;
        this.tipoVehiculo = tipoVehiculo;
        this.marca = marca;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public byte getIndex() {
        return index;
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

}
