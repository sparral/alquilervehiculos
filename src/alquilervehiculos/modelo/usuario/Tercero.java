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
public abstract class Tercero implements Serializable {
    // Tercero son los datos personales del usuario:
    private String nombre;
    private String apellido;
    private String cedula;
    private byte edad;    
    private boolean problemasvision;
    private boolean problemasauditivos;

    // Constructor:

    public Tercero(String nombre, String apellido, String cedula, byte edad, boolean problemasvision, boolean problemasauditivas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.edad = edad;
        this.problemasvision = problemasvision;
        this.problemasauditivos = problemasauditivas;
    }
    

    // Métodos(Getter&Setter):
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public boolean isProblemasvision() {
        return problemasvision;
    }

    public void setProblemasvision(boolean problemasvision) {
        this.problemasvision = problemasvision;
    }

    public boolean isProblemasauditivos() {
        return problemasauditivos;
    }

    public void setProblemasauditivos(boolean problemasauditivas) {
        this.problemasauditivos = problemasauditivas;
    }
     
}
