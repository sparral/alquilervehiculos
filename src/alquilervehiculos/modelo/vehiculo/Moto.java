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
public class Moto extends AbstractVehiculo {

    // Moto ES UN Vehículo, y se añade las variables de Moto:
    private boolean casco;

    // Contructor:
    public Moto(boolean casco, String matricula, int kilometraje, boolean estado,
            TipoMarca marca, String anio, int[] valorAlquiler, int contAlquiler,
            boolean activar) {
        super(matricula, kilometraje, estado, marca, anio, valorAlquiler,
                contAlquiler, activar);
        this.casco = casco;
    }

    // Métodos (Getter&Setter):
    public boolean isCasco() {
        return casco;
    }
    
    public void setCasco(boolean casco) {
        this.casco = casco;
    }

    // Métodos de la MOTO:
    @Override
    public String[] getArrayCSV() {
        // La idea es retornar un arreglo de String con todas las variables:
        String marca = getMarca().getMarca();
        String[] datos = {Boolean.toString(isEstado()), getMatricula(),
            marca, getAnio(), Integer.toString(getKilometraje()),
            Integer.toString(getValorAlquiler()[0]),
            Integer.toString(getValorAlquiler()[1]), Boolean.toString(casco),
            Integer.toString(getContAlquiler()), Boolean.toString(isActivar())};
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
        setContAlquiler(getContAlquiler() + 1);
    }
}
