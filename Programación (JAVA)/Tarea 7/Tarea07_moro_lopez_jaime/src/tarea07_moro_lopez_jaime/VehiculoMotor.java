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
public abstract class VehiculoMotor extends Vehiculo {

    protected double autonomiaDeposito;

    public VehiculoMotor(String matricula, String marca, String modelo, double capacidadCarga, double autonomiaDeposito) {
        super(matricula, marca, modelo, capacidadCarga);
        this.autonomiaDeposito = autonomiaDeposito;
    }

}
