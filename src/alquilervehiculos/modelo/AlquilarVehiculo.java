/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo;

import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Santy
 */
public class AlquilarVehiculo {
    // Relaciona la clase "AbstractVehiculo" con "Usuario",
    private List<Cliente> clientes;

    public AlquilarVehiculo() {
        llenarClientes();
    }

    public void llenarClientes() {
        if (clientes==null || clientes.isEmpty()) {
            clientes = new ArrayList<>();
        }
    }
    
    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public Cliente buscarCliente(String matricula) {
        // Dada la matricula del vehiculo, retorna el cliente:
        for (Cliente seleccionado: clientes) {
            if (seleccionado.getMatricula().compareTo(matricula)==0) {
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
    }
    
    public void devolverVehiculo() {
        
    }

}
