/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo;

import java.io.Serializable;

/**
 *
 * @author Santy
 */
public class Usuario extends Tercero implements Serializable {
    // Variables:
    private String correo;
    private String password;
    private TipoUsuario tipousuario;

    // Constructor:
    public Usuario(String correo, String password, TipoUsuario tipousuario, String nombre, String apellido, String cedula, byte edad) {
        super(nombre, apellido, cedula, edad);
        this.correo = correo;
        this.password = password;
        this.tipousuario = tipousuario;
    }

    // Métodos:
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipoUsuario tipousuario) {
        this.tipousuario = tipousuario;
    }

    @Override
    public String toString() {
        return this.getNombre().toUpperCase()+ " " + this.getApellido().toUpperCase();
    }
    
    
}
