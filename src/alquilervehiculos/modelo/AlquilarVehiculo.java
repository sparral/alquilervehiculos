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
import javax.swing.JOptionPane;

/**
 *
 * @author Santy
 */
public class AlquilarVehiculo {

    // Relaciona la clase "AbstractVehiculo" con "Usuario",
    private List<AbstractVehiculo> vehiculos;
    private List<Cliente> clientes;
    private final LocalDate fechaActual = LocalDate.now();

    public AlquilarVehiculo() {
        llenarClientes();
    }
// -------------------------** LISTA CLIENTES ** -------------------------------

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

// ------------------------** MÉTODOS ** ---------------------------------------
    public void alquilarVehiculo(AbstractVehiculo vehiculo, Usuario user,
            LocalDate fechaInicial, LocalDate fechaFinal, String pago) {
        // Actualizar estado vehiculo, si la fecha es ahora:
        if (fechaActual.isEqual(fechaInicial)) {
            vehiculo.alquilar();
            actualizarVehiculo(vehiculo, false);
        }
        // Ingresa al sistema el nuevo cliente:
        clientes.add(new Cliente(user.getUserID(), vehiculo.getMatricula(),
                fechaInicial, fechaFinal, pago));
        ExportarCSV.clienteCSV(clientes);
    }

    public double devolverVehiculo(AbstractVehiculo vehiculo,
            int kilometrajeNuevo, String[] datos) {

        Cliente user = buscarCliente(vehiculo.getMatricula());

        // Indicar si la devolución es prematura o tardía:
        int opcion = 0;
        if (fechaActual.isBefore(user.getFechaDevolucion())) {
            // Entrega prematura
            opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de "
                    + "validar este vehiculo?",
                    "Entrega prematura", JOptionPane.YES_NO_OPTION);
            if (opcion == 1) {
                return 0;
            } else if (opcion == 0 && datos[7].isEmpty()) {
                datos[9] = "Entrega prematura";
            }

        } else if (fechaActual.isAfter(user.getFechaDevolucion())) {
            // Entrega tardía
            opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de "
                    + "validar este vehiculo?",
                    "Entrega tardia", JOptionPane.YES_NO_OPTION);
            if (opcion == 1) {
                return 0;
            } else if (opcion == 0 && datos[7].isEmpty()) {
                datos[9] = "Entrega tardia";
            }
        }

        if (opcion == 0) {
            // Indicar cuánto debe pagar el cliente:
            int valor = 0;
            switch (user.getTipoPago()) {
                case "Dia": {
                    valor = fechaActual.compareTo(user.getFechaAlquiler());
                    break;
                }
                case "Km": {
                    valor = kilometrajeNuevo - vehiculo.getKilometraje();
                    break;
                }
            }

            // Crear el CSV de registro:
            datos[0] = vehiculo.getClass().getSimpleName();
            datos[2] = user.getUserID();
            datos[7] = user.getTipoPago();
            datos[8] = Double.toString(vehiculo.calcularAlquiler(user.getTipoPago(), valor));
            ExportarCSV.generarReporte(datos);

            // Eliminar el cliente de la lista y sobreescribir en el CSV:
            for (Cliente seleccionado : this.clientes) {
                if (seleccionado.getUserID().compareTo(user.getUserID()) == 0) {
                    clientes.remove(seleccionado);
                    break;
                }
            }
            vehiculo.devolver(kilometrajeNuevo);
            actualizarVehiculo(vehiculo, true);
            ExportarCSV.clienteCSV(clientes);

            return vehiculo.calcularAlquiler(user.getTipoPago(), valor);
        }
        // Admin no aceptó devolver el vehiculo:
        return 0;
    }

    private void actualizarVehiculo(AbstractVehiculo vehiculo, boolean estado) {
        // Sobreescribir en el CSV, que el vehiculo está disponible/ocupado:
        ControladorVehiculo control = new ControladorVehiculo();
        vehiculos = control.getVehiculos(vehiculo.getClass().getSimpleName());

        for (AbstractVehiculo seleccionado : vehiculos) {
            if (seleccionado.getMatricula().compareTo(vehiculo.getMatricula()) == 0) {
                // Cambiar el estado del vehiculo a disponible/ocupado:
                seleccionado.setEstado(estado);
                break;
            }
        }
        ExportarCSV.vehiculoCSV(vehiculos);
    }

    public void actualizarEstadoVehiculos() {
        // Actualizar el estado del vehiculo en la fecha estipulada:
        for (Cliente seleccionado : this.clientes) {
            if (fechaActual.isEqual(seleccionado.getFechaAlquiler())) {
                String matricula = seleccionado.getMatricula();
                ControladorVehiculo control = new ControladorVehiculo();
                actualizarVehiculo(control.encontrarVehiculo(matricula), false);
            }
        }
    }

    public boolean validarEstadoVehiculo(String matricula,
            LocalDate fechaInicial, LocalDate fechaFinal) {
        // Valida que se pueda alquilar el vehiculo en las fechas dadas:
        for (Cliente seleccionado : this.clientes) {
            if (seleccionado.getMatricula().compareTo(matricula) == 0
                    && (fechaFinal.isAfter(seleccionado.getFechaAlquiler())
                    || fechaInicial.isBefore(seleccionado.getFechaDevolucion()))) {
                return false;
            }
        }
        // Vehiculo no se encuentra en la lista de clientes:
        return true;
    }
}
