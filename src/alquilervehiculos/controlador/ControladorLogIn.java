/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.excepciones.LogInException;
import alquilervehiculos.modelo.TipoUsuario;
import alquilervehiculos.modelo.Usuario;

/**
 *
 * @author Santy
 */
public class ControladorLogIn {

    private TipoUsuario[] tipousuarios;
    private Usuario[] usuarios;

    public ControladorLogIn() {
        llenarTiposUsuario();
        llenarUsuarios();
    }

    // Métodos del ControladorLogIn:
    
    private void llenarTiposUsuario() {
        tipousuarios = new TipoUsuario[3];
        tipousuarios[0] = new TipoUsuario((byte) 1, "Administrador");
        tipousuarios[1] = new TipoUsuario((byte) 2, "Usuario");
        tipousuarios[2] = new TipoUsuario((byte) 3, "Consulta");
    }

    private void llenarUsuarios() {
        // Por ahora, con arreglo:
        usuarios = new Usuario[2];
        usuarios[0] = new Usuario("sparral@unal.edu.co", "santiago123",
                tipousuarios[0], "Santiago", "Parra", "1053873598", (byte) 20);
        usuarios[1] = new Usuario("carloaizaumanizales.edu.co", "carlos123",
                tipousuarios[0], "Carlos", "Loaiza", "1234567", (byte) 50);

        // LLENARLO CON CSV...
    }

    public Usuario encontrarUsuario(String correo, String password) {
        for (Usuario usuarioEncontrado : usuarios) {
            if (usuarioEncontrado.getCorreo().compareTo(correo) == 0
                    && usuarioEncontrado.getPassword().compareTo(password) == 0) {
                return usuarioEncontrado;
            }
        }

        return null;
    }

    public Usuario validarUsuario(String correo, String password) throws LogInException {

        if (correo == null || correo.compareTo("") == 0) {
            throw new LogInException("Debe diligenciar el correo");
        } else if (password.length() == 0) {
            throw new LogInException("Debe diligenciar la contraseña");
        } else {
            // Significa que diligenció usuario y contraseña:
            Usuario usuarioAutenticado = encontrarUsuario(correo, password);
            if (usuarioAutenticado == null) {
                throw new LogInException("Usuario ingresado no existe");
            }
            return usuarioAutenticado;
        }
    }
}
