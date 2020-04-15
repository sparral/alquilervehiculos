/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.modelo.usuario;

import java.io.Serializable;

/**
 *
 * @author Santy
 */
public class Usuario extends Tercero implements Serializable {

    // Usuario son los datos requeridos para ingresar al sistema:
    private String correo;
    private String password;
    private TipoUsuario tipousuario;

    // Constructor:
    public Usuario(String correo, String password, TipoUsuario tipousuario, String nombre, String apellido, String cedula, byte edad, boolean problemasvision, boolean problemasauditivos) {
        super(nombre, apellido, cedula, edad, problemasvision, problemasauditivos);
        this.correo = correo;
        this.password = password;
        this.tipousuario = tipousuario;
    }

    // Métodos (Getter&Setter):
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

    public String[] getArray() {
        // La idea es retornar un arreglo de String con todos las variables:
        byte tipo = getTipousuario().getCodigo();
        String[] datos = {correo, password, Byte.toString(tipo), getNombre(), 
            getApellido(), getCedula(), Byte.toString(getEdad()), 
            Boolean.toString(isProblemasvision()), Boolean.toString(isProblemasauditivos())};
        return datos;
    }
    
    
}
