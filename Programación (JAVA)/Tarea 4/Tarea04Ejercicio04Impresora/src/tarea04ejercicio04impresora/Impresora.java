/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04ejercicio04impresora;

/**
 *
 * @author tpmvo
 */
public class Impresora {
    
    // Atributos
    
    private String nombre;
    private byte toner;
    private short memoria;
    
    // Metodos

    public Impresora() {
    }

    public Impresora(String nombre, byte toner, short memoria) {
        this.nombre = nombre;
        this.toner = toner;
        this.memoria = memoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getToner() {
        return toner;
    }

    public void setToner(byte toner) {
        this.toner = toner;
    }

    public short getMemoria() {
        return memoria;
    }

    public void setMemoria(short memoria) {
        this.memoria = memoria;
    }

    @Override
    public String toString() {
        return "Impresora{" + "nombre=" + nombre + ", toner=" + toner + ", memoria=" + memoria + '}';
    }
    
    // Siendo el coste de impresión un 12% de tinta del tóner por página, no tiene sentido que se impriman muchas páginas, por lo que trabajaremos con bytes.
    public void imprimir (byte num) {   
        byte costeImpresion = 12;
        byte gastoTinta = (byte) (num * costeImpresion);
        if (this.toner - gastoTinta < 0) {
            System.out.println("Error de impresión");
        } else {
            this.toner -= gastoTinta;
            System.out.println(num + " páginas impresas.");
        }  
    }
        
    public void escanear (byte num) {
        byte costePagina = 27;
        short costeMemoria = (short) (costePagina * num);
        if (this.memoria < costeMemoria) {
            System.out.println("Error de memoria");
    } 
    }
}
