/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo.usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Santy
 */
public class Cliente {

    // Clase específica que maneja los usuarios que alquilaron un vehiculo
    private final String userID;
    private final String matricula;
    private LocalDateTime fechaAlquiler;
    private LocalDateTime fechaDevolucion;
    private final String tipoPago;

    // Constructor:
    public Cliente(String userID, String matricula, LocalDateTime fechaAlquiler, LocalDateTime fechaDevolucion, String tipoPago) {
        this.userID = userID;
        this.matricula = matricula;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
        this.tipoPago = tipoPago;
    }

    // Getter & Setter:
    public String getUserID() {
        return userID;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDateTime getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDateTime fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public Object[] getObjectCliente() {
        // MODIFICAR ESTO:
        Object[] datos = {userID, matricula, fechaAlquiler.toLocalDate()
            .format(DateTimeFormatter.ofPattern("dd/MM")),
            fechaDevolucion.toLocalDate().format(DateTimeFormatter
            .ofPattern("dd/MM"))};
        return datos;
    }

    public String[] getArrayCSV() {
        // La idea es retornar un arreglo de String con todas las variables:
        String[] datos = {getUserID(), getMatricula(),
            getFechaAlquiler().toString(), getFechaDevolucion().toString(), tipoPago};
        return datos;
    }

}
