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
public class Moto extends Vehiculo {
    // Moto ES UN Vehículo, y se añade las variables de Moto:
    private boolean casco;

    // Contructor:
    public Moto(boolean casco, String matricula, int kilometraje, boolean estado, String marca, int año, double valorAlquiler) {
        super(matricula, kilometraje, estado, marca, año, valorAlquiler);
        this.casco = casco;
    }
    

    // Métodos (Getter&Setter):
    public boolean isCasco() {
        return casco;
    }

    public void setCasco(boolean casco) {
        this.casco = casco;
    }
    
    // Métodos de la MOTO:
    
}
