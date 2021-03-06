/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo.vehiculo;

import alquilervehiculos.modelo.usuario.Cliente;
import java.time.format.DateTimeFormatter;

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

    // Variables para generar reporte y validaciones:
    private int contAlquiler;
    private boolean activar;

    // Contructor:
    public AbstractVehiculo(String matricula, int kilometraje, boolean estado,
            TipoMarca marca, String anio, int[] valorAlquiler, int contAlquiler, boolean activar) {
        this.matricula = matricula;
        this.kilometraje = kilometraje;
        this.estado = estado;
        this.marca = marca;
        this.anio = anio;
        this.valorAlquiler = valorAlquiler;
        this.contAlquiler = contAlquiler;
        this.activar = activar;
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

    public int getContAlquiler() {
        return contAlquiler;
    }

    public void setContAlquiler(int contAlquiler) {
        this.contAlquiler = contAlquiler;
    }

    public boolean isActivar() {
        return activar;
    }

    public void setActivar(boolean activar) {
        this.activar = activar;
    }

    // Para obtener los datos de las tabla:  
    public Object[] getObjectAdmin() {
        String cliente = "";
        String fecha = "";
        if (!estado) {
            AlquilarVehiculo buscar = new AlquilarVehiculo();
            Cliente user = buscar.buscarCliente(matricula);
            cliente = user.getUserID();
            fecha = user.getFechaDevolucion().
                    format(DateTimeFormatter.ofPattern("dd/MM"));
        }

        Object[] datos = {isEstado(), getClass().getSimpleName(), getMatricula(),
            getMarca(), getAnio(), cliente, fecha};
        return datos;
    }

    public Object[] getObjectUsuario() {
        Object[] datos = {isEstado(), getClass().getSimpleName(), getMarca(),
            getAnio()};
        return datos;
    }

    public Object[] getObjectCRUD() {
        // MODIFICAR ESTO:
        Object[] datos = {getMatricula(), getMarca(), getAnio(), getKilometraje(),
            isActivar()};
        return datos;
    }

    public String getArrayDatosAlquilar() {
        AlquilarVehiculo buscar = new AlquilarVehiculo();
        String fechas = buscar.buscarFechasCercanas(matricula);
        String datos = "Datos de " + getClass().getSimpleName() + ": \n"
                + marca.getMarca() + ", " + anio
                + " \n" + Integer.toString(kilometraje) + " km \n"
                + "Fechas ocupado: " + fechas;

        return datos;
    }

    public String getArrayDatosValidar() {
        AlquilarVehiculo buscar = new AlquilarVehiculo();
        Cliente user = buscar.buscarCliente(matricula);
        String fechaInicio = user.getFechaAlquiler().format(DateTimeFormatter
                .ofPattern("dd/MM"));
        String fechaFinal = user.getFechaDevolucion().format(DateTimeFormatter
                .ofPattern("dd/MM"));

        String datos = "Datos de " + getClass().getSimpleName() + ": \n"
                + marca.getMarca() + ", " + anio
                + " \n" + Integer.toString(kilometraje) + " km \n"
                + "Fecha alquilado: " + fechaInicio + "\n"
                + "Fecha devolución: " + fechaFinal;
        return datos;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
