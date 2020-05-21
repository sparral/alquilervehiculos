/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo.vehiculo;

import alquilervehiculos.modelo.AlquilarVehiculo;
import alquilervehiculos.modelo.Cliente;

/**
 *
 * @author Santy
 */
public abstract class AbstractVehiculo implements VehiculoAble {

    // Variables que todo vehículo tiene:
    private final String matricula;
    private int kilometraje;
    private boolean estado;
    private TipoMarca marca;
    private String anio;
    private int[] valorAlquiler;

    // Contructor:
    public AbstractVehiculo(String matricula, int kilometraje, boolean estado, TipoMarca marca, String anio, int[] valorAlquiler) {
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

    public TipoMarca getMarca() {
        return marca;
    }

    public void setMarca(TipoMarca marca) {
        this.marca = marca;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int[] getValorAlquiler() {
        return valorAlquiler;
    }

    public void setValorAlquiler(int[] valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    // Para obtener los datos de las tabla:  
    public Object[] getObjectAdmin() {
        String cliente = "";
        String fecha = "";
        if (!estado) {
            AlquilarVehiculo buscar = new AlquilarVehiculo();
            Cliente user = buscar.buscarCliente(matricula);
            cliente = user.getUserID();
            fecha = user.getFechaDevolucion().toString();
        }

        Object[] datos = {isEstado(), getClass().getSimpleName(), getMatricula(),
            getMarca(), getAnio(), cliente, fecha};
        return datos;
    }

    public Object[] getObjectUsuario() {
        Object[] datos = {isEstado(), getClass().getSimpleName(), getMarca(), getAnio()};
        return datos;
    }

    public Object[] getObjectCRUD() {
        // MODIFICAR ESTO:
        Object[] datos = {getMatricula(), getMarca(), getAnio(), getKilometraje(), true};
        return datos;
    }

    public String getArrayDatosAlquilar() {
        String datos = "Datos de " + getClass().getSimpleName() + ": \n"
                + marca.getMarca() + ", " + anio
                + " \n" + Integer.toString(kilometraje) + " km \n";
        return datos;
    }

    public String getArrayDatosValidar() {
        AlquilarVehiculo buscar = new AlquilarVehiculo();
        Cliente user = buscar.buscarCliente(matricula);
        String fechaInicio= user.getFechaAlquiler().toString();
        String fechaFinal = user.getFechaDevolucion().toString();
        
        String datos = "Datos de " + getClass().getSimpleName() + ": \n"
                + marca.getMarca() + ", " + anio
                + " \n" + Integer.toString(kilometraje) + " km \n"
                + "Fecha alquilado: " + fechaInicio + "\n"
                + "Fecha devolución: " + fechaFinal;
        return datos;
    }
}
