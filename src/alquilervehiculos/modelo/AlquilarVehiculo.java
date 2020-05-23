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
            LocalDate fechaInicial, LocalDate fechaFinal, String pago) {
        // Ingresa al sistema el nuevo cliente y actualizar estado vehiculo:
        vehiculo.alquilar();
        Cliente nuevoCliente = new Cliente(user.getUserID(),
                vehiculo.getMatricula(), fechaInicial, fechaFinal, pago);

        clientes.add(nuevoCliente);
        actualizarVehiculo(vehiculo);
        ExportarCSV.clienteCSV(clientes);
    }

    public double devolverVehiculo(AbstractVehiculo vehiculo,
            int kilometrajeNuevo, String[] datos) {

        Cliente user = buscarCliente(vehiculo.getMatricula());
        LocalDate fechaDevolucion = LocalDate.now();

        // Indicar si la devolución es prematura o tardía:
        int opcion = 0;
        if (fechaDevolucion.isBefore(user.getFechaDevolucion())) {
            // Entrega prematura
            opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de "
                    + "validar este vehiculo?",
                    "Entrega prematura", JOptionPane.YES_NO_OPTION);
            if (opcion == 1) {
                return 0;
            } else if (opcion == 0 && datos[7].isEmpty()) {
                datos[7] = "Entrega prematura";
            }

        } else if (fechaDevolucion.isAfter(user.getFechaDevolucion())) {
            // Entrega tardía
            opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de "
                    + "validar este vehiculo?",
                    "Entrega tardia", JOptionPane.YES_NO_OPTION);
            if (opcion == 1) {
                return 0;
            } else if (opcion == 0 && datos[7].isEmpty()) {
                datos[7] = "Entrega tardia";
            }
        }

        if (opcion == 0) {
            // Usuario aceptó devolver el vahiculo:
            vehiculo.devolver(kilometrajeNuevo);
            actualizarVehiculo(vehiculo);

            // Indicar cuánto debe pagar el cliente:
            int valor = 0;
            switch (user.getTipoPago()) {
                case "Dia": {
                    valor = fechaDevolucion.compareTo(user.getFechaAlquiler());
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
            datos[7] = Double.toString(vehiculo.calcularAlquiler(user.getTipoPago(), valor));
            ExportarCSV.generarReporte(datos);

            // Eliminar el cliente de la lista y sobreescribir en el CSV:
            for (Cliente seleccionado : this.clientes) {
                if (seleccionado.getUserID().compareTo(user.getUserID()) == 0) {
                    clientes.remove(seleccionado);
                    break;
                }
            }
            ExportarCSV.clienteCSV(clientes);

            return vehiculo.calcularAlquiler(user.getTipoPago(), valor);
        }
        // Admin no aceptó devolver el vehiculo:
        return 0;
    }

    private void actualizarVehiculo(AbstractVehiculo vehiculo) {
        // Sobreescribir en el CSV, que el vehiculo está disponible/ocupado:
        ControladorVehiculo control = new ControladorVehiculo();

        vehiculos = control.getVehiculos(vehiculo.getClass().getSimpleName());
        ExportarCSV.vehiculoCSV(vehiculos);
    }

}
