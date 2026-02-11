/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea062farmacia;

import java.io.Serializable;

/**
 *
 * @author tpmvo
 */
public class Articulo implements Serializable {

    private String denominacion;
    private short unidades; //Consideramos que un tipo short es suficiente
    private double precio;

    public Articulo(String denominacion, short unidades, double precio) {
        this.denominacion = denominacion;
        this.unidades = unidades;
        this.precio = precio;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public short getUnidades() {
        return unidades;
    }

    public void setUnidades(short unidades) {
        this.unidades = unidades;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Articulo{" + "denominacion=" + denominacion + ", unidades=" + unidades + ", precio=" + precio + '}';
    }

}
