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
public class Usuario extends AbstractTercero implements Serializable {

    // Usuario son los datos requeridos para ingresar al sistema:
    private String correo;
    private String password;
    private TipoUsuario tipousuario;

    // Constructor:
    public Usuario(String correo, String password, TipoUsuario tipousuario, String nombre, String apellido, byte edad, boolean problemasvision, boolean problemasauditivos) {
        super(nombre, apellido, edad, problemasvision, problemasauditivos);
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

    public String getUserID() {
        int posicion = this.correo.indexOf("@");
        return this.correo.substring(0, posicion);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            if (((Usuario) obj).correo.compareTo(this.correo) == 0) {
                // Correos son iguales, por tanto el usuario ya existe:
                return true;
            }
        }
        return false;
    }

    // Para obtener los arreglos necesarios:
    public String[] getArrayCSV() {
        // La idea es retornar un arreglo de String con todas las variables:
        byte tipo = getTipousuario().getCodigo();
        String[] datos = {correo, password, Byte.toString(tipo), getNombre(),
            getApellido(), Byte.toString(getEdad()),
            Boolean.toString(isProblemasvision()), Boolean.toString(isProblemasauditivos())};
        return datos;
    }

    public Object[] getObjectCRUD() {
        Object[] datos = {getUserID(), getNombre(), getApellido(),
            getEdad(), getTipousuario().toString()};
        return datos;
    }

    public Object[] getObjectAlquilar() {
        Object[] datos = {getUserID(), getNombre()+" "+getApellido(), getEdad()};
        return datos;
    }

}
