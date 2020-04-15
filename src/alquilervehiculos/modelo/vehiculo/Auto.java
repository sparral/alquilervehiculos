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
public class Auto extends Vehiculo {
    // Auto ES UN Vehículo, y se añade las variables del auto:
    private boolean extras;

    // Contructor:

    public Auto(boolean extras, String matricula, int kilometraje, boolean estado, String marca, int año, double valorAlquiler) {
        super(matricula, kilometraje, estado, marca, año, valorAlquiler);
        this.extras = extras;
    }
    

    //  Métodos (Getter&Setter):
    public boolean isExtras() {
        return extras;
    }

    public void setExtras(boolean extras) {
        this.extras = extras;
    }
    
    // Métodos del AUTO:
    
}
