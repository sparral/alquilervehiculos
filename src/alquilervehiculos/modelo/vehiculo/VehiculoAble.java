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
public interface VehiculoAble {
    // Todos los métodos necesarios de los vehículos:
    
    public void alquilar();
    
    public void devolver(int kilometraje);
    
    public double calcularAlquiler(String tipo, int valor);
    
}
