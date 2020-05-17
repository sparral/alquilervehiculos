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
public abstract class AbstractVehiculo implements VehiculoAble {

    // Variables que todo vehículo tiene:
    private final String matricula;
    private int kilometraje;
    private boolean estado;
    private TipoMarca marca;
    private String anio;
    private double [] valorAlquiler;

    // Contructor:
    public AbstractVehiculo(String matricula, int kilometraje, boolean estado, TipoMarca marca, String anio, double[] valorAlquiler) {
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

    public double[] getValorAlquiler() {
        return valorAlquiler;
    }

    public void setValorAlquiler(double[] valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    // Para obtener los datos de las tabla:  
    public Object[] getObjectAdmin() {
        // MODIFICAR ESTO:
        Object[] datos = {isEstado(), getClass().getSimpleName(), getMatricula(),
            getMarca(), getAnio(), "---", "---",};
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

    // Finalmente, sobreescribo los métodos de VehiculoAble:
    @Override
    public abstract double calcularAlquiler(String tipo, int valor);

    @Override
    public abstract void devolver(int kilometraje);

    @Override
    public abstract void alquilar();
    
    
}
