/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.excepciones.UsuarioException;
import alquilervehiculos.modelo.usuario.TipoUsuario;
import alquilervehiculos.modelo.usuario.Usuario;
import alquilervehiculos.utilidades.ExportarCSV;
import alquilervehiculos.utilidades.ImportarCSV;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Santy
 */
public class ControladorUsuario implements Serializable {

    private TipoUsuario[] tipousuarios;
    private List<Usuario> usuarios;
    private final String REGEXP;

    public ControladorUsuario() {

        // 2. emailPattern    "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
        //            "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$"
        // 3. emailPattern   "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        //                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        
        this.REGEXP = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"
                + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        llenarTiposUsuario();
        llenarUsuarios();
    }

    private void llenarTiposUsuario() {
        tipousuarios = new TipoUsuario[3];
        tipousuarios[0] = new TipoUsuario((byte) 1, "Administrador");
        tipousuarios[1] = new TipoUsuario((byte) 2, "Usuario");
        tipousuarios[2] = new TipoUsuario((byte) 3, "Invitado");
    }

    public TipoUsuario[] getTipousuarios() {
        return tipousuarios;
    }
    
    private void llenarUsuarios() {
        if (usuarios == null || usuarios.isEmpty()) {
            usuarios = ImportarCSV.cargarUsuarios(tipousuarios);
        }
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Método para ingresar al sistema:
    public Usuario validarUsuario(String correo, String password) throws UsuarioException {
        if (correo == null || correo.compareTo("") == 0) {
            throw new UsuarioException("Debe diligenciar el correo");
        } else if (password.length() == 0) {
            throw new UsuarioException("Debe diligenciar la contraseña");
        } else {
            // Significa que diligenció correo y contraseña:
            Pattern emailPattern = Pattern.compile(REGEXP);
            Matcher matcher = emailPattern.matcher(correo);

            if (matcher.find()) {
                // Significa que el correo tiene formato válido:
                for (Usuario usuarioEncontrado : usuarios) {
                    if (usuarioEncontrado.getCorreo().compareTo(correo) == 0
                            && usuarioEncontrado.getPassword().compareTo(password) == 0) {
                        return usuarioEncontrado;
                    }
                }
            } else {
                throw new UsuarioException("Correo no tiene formato válido");
            }

            throw new UsuarioException("Usuario ingresado no existe");
        }
    }

    // Métodos para el CRUD de Usuarios:
    
    public void agregarUsuario(Usuario user) throws UsuarioException {
        Pattern emailPattern = Pattern.compile(REGEXP);
        Matcher matcher = emailPattern.matcher(user.getCorreo());

        if (matcher.find()) {
            // Formato de correo válido:
            if (encontrarUsuario(user.getCorreo()) == null) {
                // No existe un usuario con ese correo, lo agrego a la lista:
                usuarios.add(user);
                ExportarCSV.agregarUsuarioCSV(usuarios);
            } else {
                throw new UsuarioException("Usuario ingresado ya existe");
            }
        } else {
            throw new UsuarioException("Correo no tiene formato válido");
        }
    }

    public Usuario encontrarUsuario(String correo) {

        for (Usuario usuarioEncontrado : this.usuarios) {
            // Validación por medio de correo:           
            if (correo.compareTo(usuarioEncontrado.getCorreo()) == 0) {
                return usuarioEncontrado;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioTabla(String userID) {

        for (Usuario usuarioEncontrado : this.usuarios) {
            // Encuentra el usuario comparando con el userID dado en la tabla:
            if (userID.compareTo(usuarioEncontrado.getUserID()) == 0) {
                return usuarioEncontrado;
            }
        }
        return null;
    }
      
    public void eliminarUsuario(String userID) {
        
        for(Usuario user:this.usuarios) {
            if (user.getUserID().compareTo(userID)==0) {
                // Al encontrar el usuario por el userID, lo elimina:
                this.usuarios.remove(user);
                
                // FALTA ELIMINARLO DEL CSV...
            }
        } 
    }
}
