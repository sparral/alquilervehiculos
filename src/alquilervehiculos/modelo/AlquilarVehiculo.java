/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo;

import alquilervehiculos.controlador.ControladorUsuario;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Santy
 */
public class AlquilarVehiculo {
    // Relaciona la clase "AbstractVehiculo" con "Usuario",
    private ControladorUsuario controlUsuario;
    private final List<Cliente> clientes = controlUsuario.getClientes();

    public void AlquilarVehiculo(AbstractVehiculo vehiculo, Usuario user,
            LocalDate fechaInicial, LocalDate fechaFinal) {
        
        vehiculo.alquilar();
        Cliente nuevoCliente = new Cliente(user.getUserID(), user.getNombre(),
                vehiculo.getClass().getSimpleName(), vehiculo.getMatricula(),
                fechaInicial, fechaFinal);

        clientes.add(nuevoCliente);
    }
    
    public void devolverVehiculo() {
        
    }

}
