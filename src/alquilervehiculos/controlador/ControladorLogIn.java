/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.excepciones.LogInException;
import alquilervehiculos.modelo.TipoUsuario;
import alquilervehiculos.modelo.Usuario;
import alquilervehiculos.utilidades.ImportarCSV;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Santy
 */
public class ControladorLogIn implements Serializable{

    private TipoUsuario[] tipousuarios;
    private List<Usuario> usuarios;

    public ControladorLogIn() {
        llenarTiposUsuario();
        llenarUsuarios();
    }

    // Métodos del ControladorLogIn:
    private void llenarTiposUsuario() {
        tipousuarios = new TipoUsuario[3];
        tipousuarios[0] = new TipoUsuario((byte) 1, "Administrador");
        tipousuarios[1] = new TipoUsuario((byte) 2, "Usuario");
        tipousuarios[2] = new TipoUsuario((byte) 3, "Invitado");
    }

    private void llenarUsuarios() {
        if (usuarios==null || usuarios.isEmpty()) {
            usuarios = ImportarCSV.cargarUsuarios(tipousuarios);
        }
    }

    public boolean validarUsuario(String correo, String password) throws LogInException {
        // Falta VALIDAR con RegExp:
        if (correo == null || correo.compareTo("") == 0) {
            throw new LogInException("Debe diligenciar el correo");
        } else if (password.length() == 0) {
            throw new LogInException("Debe diligenciar la contraseña");
        } else {
            // Significa que diligenció correo y contraseña:
            for (Usuario usuarioEncontrado : usuarios) {
                if (usuarioEncontrado.getCorreo().compareTo(correo) == 0
                        && usuarioEncontrado.getPassword().compareTo(password) == 0) {
                    return true;
                } else {
                    // No encontró el usuario en la lista:
                    throw new LogInException("Usuario ingresado no existe");
                }
            }
        }
        return false;
    }
    
    
    // Métodos para el Registro de un nuevo usuario:
    
    public void agregarUsuario(Usuario user) throws LogInException {
        // Falta VALIDAR con RegExp y guardarlo en el CSV:
        if (validarExistenciaUsuario(user)) {
             throw new LogInException("Usuario ingresado ya existe");
        } else {
            usuarios.add(user);
            
        }
    }
    
    private boolean validarExistenciaUsuario(Usuario user) {
       
        for (Usuario usuarioExistente: this.usuarios) {
            // Validación por medio de correo y cedula:
            String correo=usuarioExistente.getCorreo();
            String cedula=usuarioExistente.getCedula();
            
            if (correo.compareTo(user.getCorreo())==0 && 
                    cedula.compareTo(user.getCedula())==0) {
                // Usuario existente:
                return true;
            }
        }
        
        return false;   
    }
}
