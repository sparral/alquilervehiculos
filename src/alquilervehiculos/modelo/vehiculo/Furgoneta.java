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
public class Furgoneta extends AbstractVehiculo {

    // Furgoneta ES UN Vehículo, y se añaden las variables de Furgoneta:
    private short capacidad;

    // Contructor:
    public Furgoneta(short capacidad, String matricula, int kilometraje, boolean estado, TipoMarca marca, String anio, double valorAlquiler) {
        super(matricula, kilometraje, estado, marca, anio, valorAlquiler);
        this.capacidad = capacidad;
    }

    // Métodos (Getter&Setter):
    public short getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(short capacidad) {
        this.capacidad = capacidad;
    }

    // Métodos de la FURGONETA:
    public String[] getArrayCSV() {
        // La idea es retornar un arreglo de String con todas las variables:
        String marca = getMarca().getMarca();
        String[] datos = {Boolean.toString(isEstado()), getMatricula(),
            marca, getAnio(), Integer.toString(getKilometraje()),
            Double.toString(getValorAlquiler()), Short.toString(capacidad)};
        return datos;
    }

    @Override
    public void alquilar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void devolver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double calcularAlquiler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String mostrarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
