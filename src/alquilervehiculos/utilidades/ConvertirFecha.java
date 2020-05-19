/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author santi
 */
public class ConvertirFecha {

    public LocalDate ConvertirFecha(Date date) {
        LocalDate fecha= LocalDate.of(date.getYear()+1900, date.getMonth()+1, 
                date.getDate());
        return fecha;
    }
    
}
