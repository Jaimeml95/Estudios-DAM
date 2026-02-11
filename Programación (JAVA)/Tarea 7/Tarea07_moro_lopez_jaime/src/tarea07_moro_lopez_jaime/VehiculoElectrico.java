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
public abstract class VehiculoElectrico extends Vehiculo {

    protected double autonomiaKm;

    public VehiculoElectrico(String matricula, String marca, String modelo, double autonomiaKm, double capacidadCarga) {
        super(matricula, marca, modelo, capacidadCarga);
        this.autonomiaKm = autonomiaKm;
    }

}
