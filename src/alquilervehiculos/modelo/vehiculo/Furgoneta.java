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
    public Furgoneta(short capacidad, String matricula, int kilometraje, boolean estado, TipoMarca marca, String anio, int[] valorAlquiler) {
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
            Integer.toString(getValorAlquiler()[0]),
            Integer.toString(getValorAlquiler()[1]), Short.toString(capacidad)};
        return datos;
    }

    @Override
    public double calcularAlquiler(String tipo, int valor) {
        switch (tipo) {
            case "Dia":
                return getValorAlquiler()[0] * valor;
            case "Km":
                return getValorAlquiler()[1] * valor;
        }
        // Si no es ninguno de los dos, no lo calcula:
        return 0;
    }

    @Override
    public void devolver(int kilometraje) {
        setKilometraje(kilometraje);
        setEstado(true);
    }

    @Override
    public void alquilar() {
        setEstado(false);
    }

}
