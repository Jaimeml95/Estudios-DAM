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
public class Coche extends VehiculoMotor {

    public Coche(String matricula, String marca, String modelo, double capacidadCarga, double autonomiaDeposito) {
        super(matricula, marca, modelo, capacidadCarga, autonomiaDeposito);
    }

    @Override
    public double obtenerCapacidadCarga() {
        double totalVolumen = 0;
        for (Paquete paquete : listaReparto) {
            totalVolumen += paquete.getVolumen();
        }
        return this.capacidadCarga - totalVolumen;
    }
}
