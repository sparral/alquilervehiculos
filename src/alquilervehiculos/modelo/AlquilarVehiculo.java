/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo;

import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import java.time.LocalDate;

/**
 *
 * @author Santy
 */
public class AlquilarVehiculo {
    // Relaciona la clase "AbstractVehiculo" con "Usuario",

    public AlquilarVehiculo(AbstractVehiculo vehiculo, Usuario user,
            LocalDate fechaInicial, LocalDate fechaFinal) {

        vehiculo.alquilar();
        Cliente nuevoCliente = new Cliente(user.getUserID(), user.getNombre(),
                vehiculo.getClass().getSimpleName(), vehiculo.getMatricula(),
                fechaInicial, fechaFinal);

        //clientes.add(nuevoCliente);
    }

}
