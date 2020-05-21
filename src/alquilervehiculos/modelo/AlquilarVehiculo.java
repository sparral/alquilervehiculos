/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo;

import alquilervehiculos.controlador.ControladorVehiculo;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.utilidades.ExportarCSV;
import alquilervehiculos.utilidades.ImportarCSV;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Santy
 */
public class AlquilarVehiculo {

    // Relaciona la clase "AbstractVehiculo" con "Usuario",
    private List<AbstractVehiculo> vehiculos;
    private List<Cliente> clientes;

    public AlquilarVehiculo() {
        llenarClientes();
    }

    private void llenarClientes() {
        if (clientes == null || clientes.isEmpty()) {
            clientes = ImportarCSV.cargarClientes();
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente buscarCliente(String matricula) {
        // Dada la matricula del vehiculo, retorna el cliente:
        for (Cliente seleccionado : clientes) {
            if (seleccionado.getMatricula().compareTo(matricula) == 0) {
                return seleccionado;
            }
        }
        return null;
    }

    public void alquilarVehiculo(AbstractVehiculo vehiculo, Usuario user,
            LocalDate fechaInicial, LocalDate fechaFinal) {

        vehiculo.alquilar();
        Cliente nuevoCliente = new Cliente(user.getUserID(),
                vehiculo.getMatricula(), fechaInicial, fechaFinal);

        clientes.add(nuevoCliente);
        actualizarVehiculo(vehiculo);
    }

    public void devolverVehiculo() {

    }

    private void actualizarVehiculo(AbstractVehiculo vehiculo) {
        // Sobreescribir en el CSV, que el vehiculo está disponible/ocupado:
        ControladorVehiculo control = new ControladorVehiculo();

        // {adicional, matricula, kilometraje, marca, anio, 
        //        valorAlquiler[0], valorAlquiler[1]}
        Object[] valores = control.buscarVehiculoTabla(vehiculo.getMatricula());
        control.editarVehiculo(valores);

        vehiculos = control.getVehiculos(vehiculo.getClass().getSimpleName());
        ExportarCSV.agregarVehiculoCSV(vehiculos);
    }

}
