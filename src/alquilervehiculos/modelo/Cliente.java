/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo;

import java.time.LocalDate;

/**
 *
 * @author Santy
 */
public class Cliente {
    // Clase específica que maneja los usuarios que alquilaron un vehiculo
    private final String userID;
    private final String nombre;
    private final String tipoVehiculo; 
    private final String vehiculo;
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;

    public Cliente(String userID, String nombre, String tipoVehiculo, String vehiculo, LocalDate fechaAlquiler, LocalDate fechaDevolucion) {
        this.userID = userID;
        this.nombre = nombre;
        this.tipoVehiculo = tipoVehiculo;
        this.vehiculo = vehiculo;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getUserID() {
        return userID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    
}
