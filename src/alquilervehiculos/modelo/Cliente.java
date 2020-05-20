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
    private final String matricula;
    private LocalDate fechaAlquiler;
    private LocalDate fechaDevolucion;

    // Constructor:

    public Cliente(String userID, String matricula, LocalDate fechaAlquiler, LocalDate fechaDevolucion) {
        this.userID = userID;
        this.matricula = matricula;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
    }
    
    // Getter & Setter:
    public String getUserID() {
        return userID;
    }

    public String getMatricula() {
        return matricula;
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
