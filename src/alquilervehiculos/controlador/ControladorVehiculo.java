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
        // Me obtiene la lista correspondiente de vehículos (activado/desactivado):       
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
        List<AbstractVehiculo> listaTemp = new ArrayList<>();
        if (estado == 0 && tipo.compareTo("Seleccionar") == 0) {
            // Ningún filtro ha sido seleccionado, retorna los vehiculos activos:
            for (AbstractVehiculo vehiculo : vehiculos) {
                if (vehiculo.isActivar()) {
                    listaTemp.add(vehiculo);
                }
            }
        } else {
            // Me obtiene lista de vehículos ACTIVADOS con filtros:              
            vehiculos.forEach((vehiculo) -> {
                if (estado == 0 && vehiculo.isActivar()) {           // TODOS

                    // Vehiculo y marca seleccionado:
                    if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && vehiculo.getMarca().getMarca().compareTo(marca) == 0) {
                        listaTemp.add(vehiculo);
                    } // Vehiculo seleccionado sin marca:
                    else if (vehiculo.getClass().getSimpleName().compareTo(tipo) == 0
                            && marca.compareTo("Seleccionar") == 0) {
                        listaTemp.add(vehiculo);
                    }
                } else if (estado == 1 && vehiculo.isEstado() && vehiculo.isEstado()) {
                    // DISPONIBLES

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
                } else if (estado == 2 && !vehiculo.isEstado() && vehiculo.isActivar()) {
                    // ALQUILADOS

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
            });
        }
        return listaTemp;
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

    public Object[] buscarValoresVehiculo(String matricula) {
        // Retorna los valores del vehiculo,
        for (AbstractVehiculo vehiculo : this.vehiculos) {
            // Encuentra el vehiculo comparando con la matricula:
            if (vehiculo.getMatricula().compareTo(matricula) == 0) {

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

    public void editarVehiculo(Object[] valores) {
        // {tipo, matricula, kilometraje, marca, anio, 
        //        valorAlquiler[0], valorAlquiler[1], adicional}

        for (AbstractVehiculo vehiculo : vehiculos) {
            // Encuentra el vehiculo comparando con la matricula:
            if (vehiculo.getMatricula().compareTo((String) valores[1]) == 0) {
                vehiculo.setKilometraje((int) valores[2]);
                vehiculo.setMarca(marcas.get(marcas.indexOf(
                        new TipoMarca((String) valores[0], (String) valores[3]))));
                vehiculo.setAnio(valores[4].toString());

                int[] valoresAlquiler = {(int) valores[5], (int) valores[6]};
                vehiculo.setValorAlquiler(valoresAlquiler);

                // Reescribo valores faltantes de acuerdo al tipo de vehiculo y
                // lo sobreescribo en el CSV correspondiente:
                switch ((String) valores[0]) {
                    case "Auto": {
                        ((Auto) vehiculo).setExtras((boolean) valores[7]);
                        break;
                    }
                    case "Moto": {
                        ((Moto) vehiculo).setCasco((boolean) valores[7]);
                        break;
                    }
                    case "Furgoneta": {
                        ((Furgoneta) vehiculo).setCapacidad((short) valores[7]);
                        break;
                    }
                }
            }
        }
        // Finalmente, sobreescribe en el CSV los cambios en el vehiculo:
        ExportarCSV.vehiculoCSV(getVehiculos((String) valores[0]));
    }

    public boolean eliminarVehiculo(String matricula) {
        for (AbstractVehiculo vehiculo : this.vehiculos) {
            // Encuentra el vehiculo comparando con la matricula:
            if (vehiculo.getMatricula().compareTo(matricula) == 0
                    && vehiculo.getContAlquiler() != 0) {
                boolean opc = this.vehiculos.remove(vehiculo);
                // Finalmente, sobreescribe en el CSV el cambio:
                ExportarCSV.vehiculoCSV(getVehiculos(vehiculo.toString()));
                return opc;
            }
        }
        return false;
    }

    public boolean estadoVehiculo(String matricula) {
        for (AbstractVehiculo vehiculo : this.vehiculos) {
            // Valida el estado del vehiculo:
            if (vehiculo.getMatricula().compareTo(matricula) == 0
                    && vehiculo.isEstado()) {
                return true;
            }
        }
        return false;
    }

    public void desactivarVehiculo(String matricula, boolean tipo)
            throws VehiculoException {
        for (AbstractVehiculo vehiculo : this.vehiculos) {
            // Encuentra el vehiculo comparando con la matricula 
            // y que esté disponible:
            if (vehiculo.getMatricula().compareTo(matricula) == 0
                    && vehiculo.isEstado() == true) {
                vehiculo.setActivar(tipo);
                // Finalmente, sobreescribe en el CSV el cambio:
                ExportarCSV.vehiculoCSV(getVehiculos(vehiculo.toString()));
                break;
            } else if (vehiculo.getMatricula().compareTo(matricula) == 0
                    && vehiculo.isEstado() == false) {
                throw new VehiculoException("No se puede desactivar este vehiculo");
            }
        }
    }
}
