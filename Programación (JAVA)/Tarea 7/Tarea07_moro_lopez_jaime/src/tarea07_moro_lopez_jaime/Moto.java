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
public class Moto extends VehiculoMotor {

    private static final short CAPACIDAD_CARGA_FIJA = 100;

    public Moto(String matricula, String marca, String modelo, double autonomiaDeposito) {
        super(matricula, marca, modelo, autonomiaDeposito, CAPACIDAD_CARGA_FIJA);
    }

    @Override
    public double obtenerCapacidadCarga() {
        double totalVolumen = 0;
        for (Paquete paquete : listaReparto) {
            totalVolumen += paquete.getVolumen();
        }
        return Moto.CAPACIDAD_CARGA_FIJA - totalVolumen;
    }
}
