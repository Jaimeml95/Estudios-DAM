/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea07_moro_lopez_jaime;

/**
 *
 * @author tpmvo
 */
public class Bicicleta extends VehiculoElectrico {

    private static final short CAPACIDAD_CARGA_FIJA = 50;

    public Bicicleta(String matricula, String marca, String modelo, double autonomiaKm) {
        super(matricula, marca, modelo, autonomiaKm, CAPACIDAD_CARGA_FIJA);
    }

    @Override
    public double obtenerCapacidadCarga() {
        double totalVolumen = 0;
        for (Paquete paquete : listaReparto) {
            totalVolumen += paquete.getVolumen();
        }
        return Bicicleta.CAPACIDAD_CARGA_FIJA - totalVolumen;

    }
}
