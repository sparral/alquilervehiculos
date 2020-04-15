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
public abstract class Vehiculo implements Serializable {

    private String matricula;
    private int kilometraje;
    private boolean estado;
    private String marca;
    private int año;
    private double valorAlquiler;

    // Contructor:
    public Vehiculo(String matricula, int kilometraje, boolean estado, String marca, int año, double valorAlquiler) {
        this.matricula = matricula;
        this.kilometraje = kilometraje;
        this.estado = estado;
        this.marca = marca;
        this.año = año;
        this.valorAlquiler = valorAlquiler;
    }

    // Métodos (Getter&Setter):
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getValorAlquiler() {
        return valorAlquiler;
    }

    public void setValorAlquiler(double valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    // Métodos del VEHÍCULO, las comparten todos los "Tipos" que existen:
    
}
