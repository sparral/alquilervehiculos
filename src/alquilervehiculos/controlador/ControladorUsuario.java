/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.controlador;

import alquilervehiculos.excepciones.LogInException;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    private void llenarTiposUsuario() {
        tipousuarios = new TipoUsuario[3];
        tipousuarios[0] = new TipoUsuario((byte) 1, "Administrador");
        tipousuarios[1] = new TipoUsuario((byte) 2, "Usuario");
        tipousuarios[2] = new TipoUsuario((byte) 3, "Invitado");
    }

    private void llenarUsuarios() {
        if (usuarios == null || usuarios.isEmpty()) {
            usuarios = ImportarCSV.cargarUsuarios(tipousuarios);
        }
    }

    // Método para ingresar al sistema:
    public Usuario validarUsuario(String correo, String password) throws LogInException {
        if (correo == null || correo.compareTo("") == 0) {
            throw new LogInException("Debe diligenciar el correo");
        } else if (password.length() == 0) {
            throw new LogInException("Debe diligenciar la contraseña");
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
                throw new LogInException("Correo no tiene formato válido");
            }

            throw new LogInException("Usuario ingresado no existe");
        }
    }

    // Métodos para el REGISTRO de un NUEVO USUARIO:
    public void agregarUsuario(Usuario user) throws LogInException {
        Pattern emailPattern = Pattern.compile(REGEXP);
        Matcher matcher = emailPattern.matcher(user.getCorreo());

        if (matcher.find()) {
            // Formato de correo válido:
            if (verificarUsuario(user.getCorreo()) == null) {
                usuarios.add(user);
                ExportarCSV.agregarUsuarioCSV(usuarios);
            } else {
                throw new LogInException("Usuario ingresado ya existe");
            }
        } else {
            throw new LogInException("Correo no tiene formato válido");
        }
    }

    public Usuario verificarUsuario(String correo) {

        for (Usuario usuarioEncontrado : this.usuarios) {
            // Validación por medio de correo:           
            if (correo.compareTo(usuarioEncontrado.getCorreo()) == 0) {
                return usuarioEncontrado;
            }
        }
        return null;
    }

    // Métodos para el CRUD de Usuarios:
    public Usuario buscarUsuarioTabla(String userID) {

        for (Usuario usuarioEncontrado : this.usuarios) {
            // Encuentra el usuario comparando con el userID dado en la tabla:
            if (userID.compareTo(usuarioEncontrado.getUserID()) == 0) {
                return usuarioEncontrado;
            }
        }
        return null;
    }

    public void editarUsuario(Object[] datos) {
        Usuario user=verificarUsuario((String)datos[0]);
        
        // {correo, password, nombre, apellido, edad, vision, auditivo}
        user.setPassword((String)datos[1]);
        user.setNombre((String)datos[2]);
        user.setApellido((String)datos[3]);
        user.setEdad((Byte)datos[4]);
        user.setProblemasvision((Boolean)datos[5]);
        user.setProblemasauditivos((Boolean)datos[6]);
    }
}
