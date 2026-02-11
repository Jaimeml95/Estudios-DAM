/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea07_moro_lopez_jaime;

import java.util.*;

/**
 *
 * @author tpmvo
 */
public abstract class Vehiculo {

    protected String matricula;
    protected String marca;
    protected String modelo;
    protected double capacidadCarga;
    protected TreeSet<Paquete> listaReparto;

    public Vehiculo() {
    }

    public Vehiculo(String matricula, String marca, String modelo, double capacidadCarga) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidadCarga = capacidadCarga;
        this.listaReparto = new TreeSet<>(Comparator.comparingInt(Paquete::getCpDestino));
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double isCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public TreeSet<Paquete> getListaReparto() {
        return listaReparto;
    }

    public void setListaReparto(TreeSet<Paquete> listaReparto) {
        this.listaReparto = listaReparto;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", capacidadCarga=" + capacidadCarga + '}';
    }

    public abstract double obtenerCapacidadCarga();

    public boolean agregarPaquete(Paquete p) {
        if (p.isEnReparto() || obtenerCapacidadCarga() < p.getVolumen()) {
            return false;
        }
        listaReparto.add(p);
        p.setEnReparto(true);
        return true;
    }
}
