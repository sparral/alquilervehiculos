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
public class Auto extends AbstractVehiculo {
    // Auto ES UN Vehículo, y se añade las variables del auto:
    private boolean extras;

    // Contructor:
    public Auto(boolean extras, String matricula, int kilometraje, boolean estado, TipoMarca marca, String anio, int[] valorAlquiler) {
        super(matricula, kilometraje, estado, marca, anio, valorAlquiler);
        this.extras = extras;
    }

    //  Métodos (Getter&Setter):
    public boolean isExtras() {
        return extras;
    }

    public void setExtras(boolean extras) {
        this.extras = extras;
    }

    // Métodos del AUTO:
    @Override
    public String[] getArrayCSV() {
        // La idea es retornar un arreglo de String con todas las variables:
        String marca = getMarca().getMarca();
        String[] datos = {Boolean.toString(isEstado()), getMatricula(),
            marca, getAnio(), Integer.toString(getKilometraje()),
            Integer.toString(getValorAlquiler()[0]),
            Integer.toString(getValorAlquiler()[1]), Boolean.toString(extras)};
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
