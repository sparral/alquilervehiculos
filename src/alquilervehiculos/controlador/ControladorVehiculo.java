/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.TipoMarca;
import alquilervehiculos.utilidades.ImportarCSV;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ControladorVehiculo implements Serializable {

    private List<AbstractVehiculo> vehiculos;
    private List<TipoMarca> marcas;
    
    
    public ControladorVehiculo() {
        llenarVehiculos();
        llenarMarcas();
    }

    private void llenarVehiculos() {
        if (vehiculos == null || vehiculos.isEmpty()) {
            vehiculos = ImportarCSV.cargarVehiculos();
        }
    }
    
    private void llenarMarcas() {
        //                      **MODIFICAR ESTO**
        marcas= new ArrayList<>();
        //String [] tiposVehiculo={"Auto","Moto","Furgoneta"};
        //String [] marcasAuto= {"Chevrolet","Kia","Mazda","Nissan"};
        //String [] marcasMoto= {"AKT","Honda","Kawasaki","Susuki","Yamaha"};
        //String [] marcasFurgoneta= {"Fiat","Ford","Mercedes","Renault"};
        
        // Marcas de Autos:
        marcas.add(new TipoMarca("Auto", "Chevrolet"));
        marcas.add(new TipoMarca("Auto", "Kia"));
        marcas.add(new TipoMarca("Auto", "Mazda"));
        marcas.add(new TipoMarca("Auto", "Nissan"));
        
        // Marcas de Motos:
        marcas.add(new TipoMarca("Moto", "AKT"));
        marcas.add(new TipoMarca("Moto", "Honda"));
        marcas.add(new TipoMarca("Moto", "Kawasaki"));
        marcas.add(new TipoMarca("Moto", "Susuki"));
        marcas.add(new TipoMarca("Moto", "Yamaha"));
        
        // Marcas de Furgonetas:
        marcas.add(new TipoMarca("Furgoneta", "Fiat"));
        marcas.add(new TipoMarca("Furgoneta", "Ford"));
        marcas.add(new TipoMarca("Furgoneta", "Hyundai"));
        marcas.add(new TipoMarca("Furgoneta", "Mercedes"));
        marcas.add(new TipoMarca("Furgoneta", "Renault"));
    }

    public List<AbstractVehiculo> getVehiculos() {
        return vehiculos;
    }

    public List<TipoMarca> getMarcas() {
        return marcas;
    }
    
    public List<AbstractVehiculo> obtenerListaVehiculos(String tipo) {
        // Me obtiene la lista correspondiente de vehículos para la tabla:        
        List<AbstractVehiculo> listaTemp = new ArrayList<>();

        for (AbstractVehiculo seleccionado : this.vehiculos) {

            if (seleccionado.getClass().getSimpleName().compareTo(tipo) == 0) {
                // Es selectivo con respecto al tipo de vehículo,
                listaTemp.add(seleccionado);
            } else if (tipo.isEmpty()) {
                // Significa que quiero TODA la lista:
                listaTemp.add(seleccionado);
            }
        }

        return listaTemp;
    }

}
