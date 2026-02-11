package Tarea032Movil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tpmvo
 */
public class Cam {
    
    //Atributos
    
    private int resolucion;
    private int numeroCamaras;
    private boolean estabilizador;
    
    //Metodos
    
    //Constructores

    public Cam() {
    }

    public Cam(int resolucion, int numeroCamaras, boolean estabilizador) {
        this.resolucion = resolucion;
        this.numeroCamaras = numeroCamaras;
        this.estabilizador = estabilizador;
    }
    
    //Getters y Setters

    public int getResolucion() {
        return resolucion;
    }

    public void setResolucion(int resolucion) {
        this.resolucion = resolucion;
    }

    public int getNumeroCamaras() {
        return numeroCamaras;
    }

    public void setNumeroCamaras(int numeroCamaras) {
        this.numeroCamaras = numeroCamaras;
    }

    public boolean isEstabilizador() {
        return estabilizador;
    }

    public void setEstabilizador(boolean estabilizador) {
        this.estabilizador = estabilizador;
    }
    
    //toString

    @Override
    public String toString() {
        return "Cam{" + "resolucion=" + resolucion + " MP, numeroCamaras=" + numeroCamaras + ", estabilizador=" + estabilizador + '}';
    }
    
}
