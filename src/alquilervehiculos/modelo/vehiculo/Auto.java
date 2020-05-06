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
    public Auto(boolean extras, String matricula, int kilometraje, boolean estado, TipoMarca marca, String anio, double valorAlquiler) {
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
    public String[] getArrayCSV() {
        // La idea es retornar un arreglo de String con todas las variables:
        String marca = getMarca().getMarca();
        String[] datos = {Boolean.toString(isEstado()), getMatricula(), 
            marca, getAnio(), Integer.toString(getKilometraje()), 
            Double.toString(getValorAlquiler()), Boolean.toString(extras)};
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
