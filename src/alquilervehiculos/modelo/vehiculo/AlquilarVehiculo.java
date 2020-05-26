/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo.vehiculo;

import alquilervehiculos.controlador.ControladorVehiculo;
import alquilervehiculos.modelo.usuario.Cliente;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.utilidades.ExportarCSV;
import alquilervehiculos.utilidades.ImportarCSV;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    private final LocalTime horaActual = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);

    public AlquilarVehiculo() {
        llenarClientes();
    }
// -------------------------** LISTA CLIENTES ** -------------------------------

    private void llenarClientes() {
        if (clientes == null || clientes.isEmpty()) {
            clientes = ImportarCSV.cargarClientes();
        }
    }

    public List<Cliente> getClientesFiltro(String tipo, String matricula, boolean hoy) {
        List<Cliente> listaTemp = new ArrayList<>();
        if (tipo.compareTo("Seleccionar") == 0 && matricula.isEmpty()
                && hoy == false) {
            return clientes;
        } else {
            // Filtros:
            ControladorVehiculo control = new ControladorVehiculo();
            for (Cliente seleccionado : this.clientes) {
                if (hoy && (seleccionado.getFechaAlquiler().toLocalDate()
                        .isEqual(fechaActual) || seleccionado.getFechaDevolucion()
                        .toLocalDate().isEqual(fechaActual))) {
                    // Clientes con tipo de vehiculo especifico:
                    if (control.encontrarVehiculo(seleccionado.getMatricula())
                            .toString().compareTo(tipo) == 0
                            || seleccionado.getMatricula().compareTo(matricula) == 0) {
                        listaTemp.add(seleccionado);
                    } else {
                        // Todos los clientes que alquilan/devuelven hoy:
                        listaTemp.add(seleccionado);
                    }
                } else if (control.encontrarVehiculo(seleccionado.getMatricula())
                        .toString().compareTo(tipo) == 0
                        || seleccionado.getMatricula().compareTo(matricula) == 0) {
                    listaTemp.add(seleccionado);
                }
            }
        }
        return listaTemp;
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

    public String buscarFechasCercanas(String matricula) {
        // Dada la matricula, busca las fechas más cercanas que está alquilado:
        byte cont = 0;
        List<Cliente> listaTemp = new ArrayList<>();
        LocalDate fechaTemp = null;

        for (Cliente seleccionado : clientes) {
            if (seleccionado.getMatricula().compareTo(matricula) == 0) {
                fechaTemp = seleccionado.getFechaAlquiler().toLocalDate();
                listaTemp.add(seleccionado);
                cont++;
            } else if (cont != 0 && seleccionado.getMatricula().compareTo(matricula) == 0) {
                listaTemp.add(seleccionado);
                cont++;
            }
        }
        // Validación de la lista:
        if (listaTemp.isEmpty()) {
            return "---";
        }
        cont = 0;
        byte index = 0;
        // Luego, recorro dicha lista pequeña:
        for (Cliente seleccionado : listaTemp) {
            if (seleccionado.getFechaAlquiler().toLocalDate().compareTo(fechaTemp) <= 0) {
                index = cont;
                fechaTemp = seleccionado.getFechaAlquiler().toLocalDate();
            }
            cont++;
        }

        Cliente user = listaTemp.get(index);
        return user.getFechaAlquiler().format(DateTimeFormatter
                .ofPattern("dd/MM")) + " - " + user.getFechaDevolucion().
                format(DateTimeFormatter.ofPattern("dd/MM"));
    }

    public Cliente obtenerCliente(AbstractVehiculo vehiculo, String user) {
        for (Cliente seleccionado : this.clientes) {
            if (seleccionado.getUserID().compareTo(user) == 0
                    && vehiculo.getMatricula().compareTo(seleccionado.getMatricula()) == 0) {
                return seleccionado;
            }
        }
        return null;
    }

// ------------------------** MÉTODOS ** ---------------------------------------
    public void alquilarVehiculo(AbstractVehiculo vehiculo, Usuario user,
            LocalDate fechaInicial, LocalDate fechaFinal, String pago) {
        // Si el cliente ya existe, actualiza los valores del mismo:
        Cliente cliente = obtenerCliente(vehiculo, user.getUserID());
        // Actualizar estado vehiculo, si la fecha es ahora:
        if (cliente == null) {
            // Cliente no existente:
            if (fechaActual.isEqual(fechaInicial)) {
                vehiculo.alquilar();
                actualizarVehiculo(vehiculo, false, 0);
            }
            // Ingresa al sistema el nuevo cliente:
            clientes.add(new Cliente(user.getUserID(), vehiculo.getMatricula(),
                    LocalDateTime.of(fechaInicial, horaActual),
                    LocalDateTime.of(fechaFinal, horaActual), pago));
        } else {
            // Cliente existente:
            vehiculo.alquilar();
            actualizarVehiculo(vehiculo, false, 0);
            cliente.setFechaAlquiler(LocalDateTime.of(fechaActual, horaActual));
        }
        ExportarCSV.clienteCSV(clientes);
    }

    public double devolverVehiculo(AbstractVehiculo vehiculo, String userID,
            int kilometrajeNuevo, String[] datos) {

        if (kilometrajeNuevo <= vehiculo.getKilometraje()) {
            // No puede generarse una validación de un menor kilometraje:
            return 0;
        }
        Cliente user = obtenerCliente(vehiculo, userID);
        LocalDate fechaSupuesta = user.getFechaDevolucion().toLocalDate();

        // Indicar si la devolución es prematura o tardía:
        int opcion = 0;
        if (fechaActual.isBefore(fechaSupuesta)) {
            // Entrega prematura
            opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de "
                    + "validar este vehiculo?",
                    "Entrega prematura", JOptionPane.YES_NO_OPTION);
            if (opcion == 1) {
                return 0;
            } else if (opcion == 0 && datos[7].isEmpty()) {
                datos[11] = "Entrega prematura";
            }

        } else if (fechaActual.isAfter(fechaSupuesta)) {
            // Entrega tardía
            opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de "
                    + "validar este vehiculo?",
                    "Entrega tardia", JOptionPane.YES_NO_OPTION);
            if (opcion == 1) {
                return 0;
            } else if (opcion == 0 && datos[7].isEmpty()) {
                datos[11] = "Entrega tardia";
            }
        }

        if (opcion == 0) {
            user.setFechaDevolucion(LocalDateTime.of(fechaActual, horaActual));
            // Indicar cuánto debe pagar el cliente:
            int valor = 0;
            switch (user.getTipoPago()) {
                case "Dia": {
                    valor = fechaActual.compareTo(user.getFechaAlquiler()
                            .toLocalDate());
                    break;
                }
                case "Km": {
                    valor = kilometrajeNuevo - vehiculo.getKilometraje();
                    break;
                }
            }

            // Crear el CSV de registro:
            datos[0] = vehiculo.getClass().getSimpleName();
            datos[7] = user.getFechaAlquiler().toString();
            datos[8] = user.getFechaDevolucion().toString();
            datos[9] = user.getTipoPago();
            datos[10] = Double.toString(vehiculo.calcularAlquiler(user.getTipoPago(), valor));
            ExportarCSV.generarReporteEntrega(datos);
            ExportarCSV.generarReporteHoras(user.getFechaAlquiler(),
                    user.getFechaDevolucion(), datos[0], ImportarCSV.cargarReporteHoras());

            double pago = vehiculo.calcularAlquiler(user.getTipoPago(), valor);
            // Eliminar el cliente de la lista y sobreescribir en el CSV:
            for (Cliente seleccionado : this.clientes) {
                if (seleccionado.getUserID().compareTo(user.getUserID()) == 0) {
                    clientes.remove(seleccionado);
                    break;
                }
            }
            vehiculo.devolver(kilometrajeNuevo);
            actualizarVehiculo(vehiculo, true, kilometrajeNuevo);
            ExportarCSV.clienteCSV(clientes);

            return pago;
        }
        // Admin no aceptó devolver el vehiculo:
        return 0;
    }

    private void actualizarVehiculo(AbstractVehiculo vehiculo, boolean estado,
            int kilometrajeNuevo) {
        // Sobreescribir en el CSV los campos correspondientes:
        ControladorVehiculo control = new ControladorVehiculo();
        vehiculos = control.getVehiculos(vehiculo.getClass().getSimpleName());

        for (AbstractVehiculo seleccionado : vehiculos) {
            if (seleccionado.getMatricula().compareTo(vehiculo.getMatricula()) == 0) {
                // Camiar el kilometraje, si se está recibiendo el vehiculo:
                if (estado == true) {
                    seleccionado.setKilometraje(kilometrajeNuevo);
                } // Cambiar el estado de activado, si se está alquilando:
                else if (estado == false) {
                    seleccionado.setContAlquiler(seleccionado.getContAlquiler() + 1);
                    seleccionado.setActivar(true);
                }
                // Cambiar el estado del vehiculo a disponible/ocupado:
                seleccionado.setEstado(estado);
                break;
            }
        }
        ExportarCSV.vehiculoCSV(vehiculos);
    }

    public void actualizarEstadoVehiculos() {
        // Actualizar el estado del vehiculo un dia antes de fecha estipulada:
        for (Cliente seleccionado : this.clientes) {
            if (fechaActual.compareTo(seleccionado.getFechaAlquiler()
                    .toLocalDate()) >= -1) {
                String matricula = seleccionado.getMatricula();
                ControladorVehiculo control = new ControladorVehiculo();
                actualizarVehiculo(control.encontrarVehiculo(matricula), false, 0);
            }
        }
    }

    public boolean validarEstadoVehiculo(String matricula,
            LocalDate fechaInicial, LocalDate fechaFinal) {
        // Valida que se pueda alquilar el vehiculo en las fechas dadas:
        for (Cliente seleccionado : this.clientes) {
            if (seleccionado.getMatricula().compareTo(matricula) == 0
                    // Dado que se desee alquilar antes:
                    && ((fechaInicial.compareTo(seleccionado.getFechaAlquiler()
                            .toLocalDate()) < 0 && fechaFinal.compareTo(seleccionado
                            .getFechaAlquiler().toLocalDate()) >= 0)
                    // Dado que se desee alquilar después:
                    || (fechaInicial.compareTo(seleccionado.getFechaDevolucion()
                            .toLocalDate()) <= 0 && fechaFinal.compareTo(seleccionado
                            .getFechaDevolucion().toLocalDate()) > 0))) {
                return false;
            }
        }
        // Vehiculo no se encuentra en la lista de clientes:
        return true;
    }
}
