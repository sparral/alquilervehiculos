/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilervehiculos.utilidades;

import alquilervehiculos.modelo.vehiculo.AbstractVehiculo;
import alquilervehiculos.modelo.vehiculo.Auto;
import alquilervehiculos.modelo.vehiculo.Furgoneta;
import alquilervehiculos.modelo.vehiculo.Moto;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author santi
 */
public class Graficas {

    public JFreeChart generarGraficaDisponible(List<AbstractVehiculo> vehiculos) {

        int[] disp = {0, 0, 0};
        int[] ocup = {0, 0, 0};
        vehiculos.forEach((seleccionado) -> {
            boolean estado = seleccionado.isEstado();
            if (seleccionado instanceof Auto && estado) {
                disp[0]++;
            } else if (seleccionado instanceof Auto && !estado) {
                ocup[0]++;
            } else if (seleccionado instanceof Moto && estado) {
                disp[1]++;
            } else if (seleccionado instanceof Moto && !estado) {
                ocup[1]++;
            } else if (seleccionado instanceof Furgoneta && estado) {
                disp[2]++;
            } else if (seleccionado instanceof Furgoneta && !estado) {
                ocup[2]++;
            }
        });

        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        String[] tipo = {"Auto", "Moto", "Furgoneta"};
        for (int i = 0; i < 3; i++) {
            datos.addValue(disp[i], "Disponible", tipo[i]);
            datos.addValue(ocup[i], "Ocupado", tipo[i]);
        }

        JFreeChart grafica = ChartFactory.createBarChart("Vehiculos disponibles",
                "Vehiculo", "Numero vehiculos", datos, PlotOrientation.HORIZONTAL,
                true, true, false);
        return grafica;
    }
}
