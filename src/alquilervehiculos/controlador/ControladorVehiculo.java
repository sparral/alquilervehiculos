/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.excepciones.VehiculoException;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Furgoneta;
import alquilervehiculos.modelo.vehiculo.Moto;
import alquilervehiculos.modelo.vehiculo.TipoMarca;
import alquilervehiculos.utilidades.ExportarCSV;
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
        llenarMarcas();
        llenarVehiculos();
    }

    private void llenarVehiculos() {
        if (vehiculos == null || vehiculos.isEmpty()) {
            vehiculos = ImportarCSV.cargarVehiculos(marcas);
        }
    }

    public List<AbstractVehiculo> getVehiculos(String tipo) {
        // Me obtiene la lista correspondiente de vehículos:       
        List<AbstractVehiculo> listaTemp = new ArrayList<>();

        for (AbstractVehiculo seleccionado : this.vehiculos) {

            if (seleccionado.getClass().getSimpleName().compareTo(tipo) == 0) {
                // Es selectivo con respecto al tipo de vehículo,
                listaTemp.add(seleccionado);
            } else if (tipo.isEmpty()) {
                // Significa que quiero TODA la lista:
                return vehiculos;
            }
        }
        return listaTemp;
    }

    public List<AbstractVehiculo> getVehiculosFiltro(byte estado, String tipo,
            String marca) {
        // Me obtiene la lista correspondiente de vehículos:  
        if (estado == 0 && tipo.compareTo("Seleccionar") == 0) {
            return vehiculos;
        } else {
            List<AbstractVehiculo> listaTemp = new ArrayList<>();

            for (AbstractVehiculo vehiculo : vehiculos) {

                if (estado == 0) {                                  // TODOS

                    // Vehiculo y marca seleccionado:
                    if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && vehiculo.getMarca().getMarca().compareTo(marca) == 0) {
                        listaTemp.add(vehiculo);
                    } // Vehiculo seleccionado sin marca:
                    else if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && marca.compareTo("Seleccionar") == 0) {
                        listaTemp.add(vehiculo);
                    }

                } else if (estado == 1 && vehiculo.isEstado()) {    // DISPONIBLES

                    // Vehiculo y marca seleccionado:
                    if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && vehiculo.getMarca().getMarca().compareTo(marca) == 0) {
                        listaTemp.add(vehiculo);
                    } // Vehiculo seleccionado sin marca:
                    else if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && marca.compareTo("Seleccionar") == 0) {
                        listaTemp.add(vehiculo);
                    } else {
                        // Solo que esté disponible:
                        listaTemp.add(vehiculo);
                    }

                } else if (estado == 2 && !vehiculo.isEstado()) {   // ALQUILADOS

                    // Vehiculo y marca seleccionado:
                    if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && vehiculo.getMarca().getMarca().compareTo(marca) == 0) {
                        listaTemp.add(vehiculo);
                    } // Vehiculo seleccionado sin marca:
                    else if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && marca.compareTo("Seleccionar") == 0) {
                        listaTemp.add(vehiculo);
                    } else {
                        // Solo que esté alquilado:
                        listaTemp.add(vehiculo);
                    }
                }
            }
            return listaTemp;
        }
    }

    private void llenarMarcas() {
        marcas = new ArrayList<>();
        // Marcas de Autos:
        String[] marcasAuto = {"Chevrolet", "Kia", "Mazda", "Nissan"};
        for (String marcaEspecifica : marcasAuto) {
            marcas.add(new TipoMarca("Auto", marcaEspecifica));
        }

        // Marcas de Motos:
        String[] marcasMoto = {"AKT", "Honda", "Kawasaki", "Susuki", "Yamaha"};
        for (String marcaEspecifica : marcasMoto) {
            marcas.add(new TipoMarca("Moto", marcaEspecifica));
        }

        // Marcas de Furgonetas:
        String[] marcasFurgoneta = {"Fiat", "Ford", "Mercedes", "Renault"};
        for (String marcaEspecifica : marcasFurgoneta) {
            marcas.add(new TipoMarca("Furgoneta", marcaEspecifica));
        }
    }

    public List<TipoMarca> getMarcas() {
        return marcas;
    }

//-----------------------** Métodos del CRUD **---------------------------------
    public void agregarVehiculo(AbstractVehiculo obj) throws VehiculoException {
        // Objecto ingresado se puede comportar como AbstractVehiculo:

        String matricula = ((AbstractVehiculo) obj).getMatricula();
        if (encontrarVehiculo(matricula) == null) {
            // No existe un vehiculo con esa matricula, lo agrego a la lista:

            if (obj instanceof Auto) {
                vehiculos.add((Auto) obj);
                ExportarCSV.vehiculoCSV(getVehiculos("Auto"));
            } else if (obj instanceof Moto) {
                vehiculos.add((Moto) obj);
                ExportarCSV.vehiculoCSV(getVehiculos("Moto"));
            } else if (obj instanceof Furgoneta) {
                vehiculos.add((Furgoneta) obj);
                ExportarCSV.vehiculoCSV(getVehiculos("Furgoneta"));
            }

        } else {
            throw new VehiculoException("Vehiculo ingresado ya existe");
        }
    }

    public AbstractVehiculo encontrarVehiculo(String matricula) {

        for (AbstractVehiculo vehiculoEncontrado : this.vehiculos) {
            // Encuentra el vehiculo comparando con la matricula:
            if (vehiculoEncontrado.getMatricula().compareTo(matricula) == 0) {
                return vehiculoEncontrado;
            }
        }
        return null;
    }

    public Object[] buscarVehiculo(String matricula) {

        for (AbstractVehiculo vehiculo : this.vehiculos) {
            // Encuentra el vehiculo comparando con la matricula:
            if (vehiculo.getMatricula().compareTo(matricula) == 0) {
                // Retorna los valores del vehiculo,
                
                Object[] obj = {vehiculo.toString(), vehiculo.getMatricula(),
                    vehiculo.getKilometraje(), vehiculo.getMarca(),
                    vehiculo.getAnio(), vehiculo.getValorAlquiler()[0],
                    vehiculo.getValorAlquiler()[1], ""};

                // Reescribo el primer valor de acuerdo al tipo de vehiculo,
                if (vehiculo instanceof Auto) {
                    boolean extras = ((Auto) vehiculo).isExtras();
                    obj[7] = extras;
                } else if (vehiculo instanceof Moto) {
                    boolean casco = ((Moto) vehiculo).isCasco();
                    obj[7] = casco;
                } else if (vehiculo instanceof Furgoneta) {
                    short capacidad = ((Furgoneta) vehiculo).getCapacidad();
                    obj[7] = capacidad;
                }
                return obj;
            }
        }
        return null;
    }

    public void editarVehiculo (Object[] valores) {
        // {tipo, matricula, kilometraje, marca, anio, 
        //        valorAlquiler[0], valorAlquiler[1], adicional}

        for (AbstractVehiculo vehiculo : vehiculos) {
            // Encuentra el vehiculo comparando con la matricula:
            if (vehiculo.getMatricula().compareTo((String) valores[1]) == 0) {
                vehiculo.setKilometraje((int) valores[2]);
                String marca = valores[3].toString();
                vehiculo.setAnio(valores[4].toString());

                int[] valoresAlquiler = {(int) valores[5], (int) valores[6]};
                vehiculo.setValorAlquiler(valoresAlquiler);

                // Reescribo valores faltantes de acuerdo al tipo de vehiculo y
                // lo sobreescribo en el CSV correspondiente:
                if (vehiculo instanceof Auto) {
                    ((Auto) vehiculo).setExtras((boolean) valores[7]);
                    vehiculo.setMarca(marcas.get(marcas.indexOf(
                            new TipoMarca("Auto", marca))));
                    ExportarCSV.vehiculoCSV(getVehiculos("Auto"));

                } else if (vehiculo instanceof Moto) {
                    ((Moto) vehiculo).setCasco((boolean) valores[7]);
                    vehiculo.setMarca(marcas.get(marcas.indexOf(
                            new TipoMarca("Moto", marca))));
                    ExportarCSV.vehiculoCSV(getVehiculos("Moto"));

                } else if (vehiculo instanceof Furgoneta) {
                    ((Furgoneta) vehiculo).setCapacidad((short) valores[7]);
                    vehiculo.setMarca(marcas.get(marcas.indexOf(
                            new TipoMarca("Furgoneta", marca))));
                    ExportarCSV.vehiculoCSV(getVehiculos("Furgoneta"));

                }
            }
        }
    }

    public boolean eliminarVehiculo(String matricula) {
        for (AbstractVehiculo vehiculo : this.vehiculos) {
            // Encuentra el vehiculo comparando con la matricula:
            if (vehiculo.getMatricula().compareTo(matricula) == 0) {
                return this.vehiculos.remove(vehiculo);
            }
        }
        return false;
    }

    public boolean estadoVehiculo(String matricula) {
        for (AbstractVehiculo vehiculo : this.vehiculos) {
            // Valida el estado del vehiculo:
            if (vehiculo.getMatricula().compareTo(matricula) == 0 && vehiculo.isEstado()) {
                return true;
            }
        }
        return false;
    }  
}
