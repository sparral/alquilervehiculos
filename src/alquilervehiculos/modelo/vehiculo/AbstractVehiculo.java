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
public abstract class AbstractVehiculo {

    // Variables que todo vehículo tiene:
    private String matricula;
    private int kilometraje;
    private boolean estado;
    private String marca;
    private String anio;
    private double valorAlquiler;

    // Contructor:
    public AbstractVehiculo(String matricula, int kilometraje, boolean estado, String marca, String anio, double valorAlquiler) {
        this.matricula = matricula;
        this.kilometraje = kilometraje;
        this.estado = estado;
        this.marca = marca;
        this.anio = anio;
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

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public double getValorAlquiler() {
        return valorAlquiler;
    }

    public void setValorAlquiler(double valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }
    
    // Adicionalmente:
    
    public  Object [] ObtenerArregloAdmin() {
        // MODIFICAR ESTO:
        Object [] datos= {isEstado(), getClass().getSimpleName(), getMatricula(),
            getMarca(), getAnio(), "danielh", "02-15-2019",true};
        return datos;
    }
    
    public Object [] ObtenerArregloUsuario() {
        Object [] datos= {isEstado(),getClass().getSimpleName(),getMarca(),getAnio()};
        return datos;
    }
    
}
