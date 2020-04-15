/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo.vehiculo;

/**
 *
 * @author Santy
 */
public class Furgoneta extends Vehiculo {
    // Furgoneta ES UN Vehículo, y se añaden las variables de Furgoneta:
    private short capacidad;

    // Contructor:
    public Furgoneta(short capacidad, String matricula, int kilometraje, boolean estado, String marca, int año, double valorAlquiler) {
        super(matricula, kilometraje, estado, marca, año, valorAlquiler);
        this.capacidad = capacidad;
    }


    // Métodos (Getter&Setter):
    public short getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(short capacidad) {
        this.capacidad = capacidad;
    }
    
    // Métodos de la FURGONETA:
    
}
