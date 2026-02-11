/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestorenergetico;

/**
 *
 * @author Alumno
 */
public class Dispositivo {
//Declaración de atributos

    private String nombre;
    private int anioFabricacion;
    private int consumoInicial;
    private boolean funcionando;

    //Métodos
    public Dispositivo() {
    }

    public Dispositivo(String nombre, int añoFabricacion, int consumoInicial, boolean funcionando) {
        this.nombre = nombre;
        this.anioFabricacion = añoFabricacion;
        this.consumoInicial = consumoInicial;
        this.funcionando = funcionando;
    }

    //Constructores
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Getters y Setters
    public int getAñoFabricacion() {
        return anioFabricacion;
    }

    public void setAñoFabricacion(int añoFabricacion) {
        this.anioFabricacion = añoFabricacion;
    }

    public int getConsumoInicial() {
        return consumoInicial;
    }

    public void setConsumoInicial(int consumoInicial) {
        this.consumoInicial = consumoInicial;
    }

    public boolean isFuncionando() {
        return funcionando;
    }

    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Dispositivo{" + "nombre=" + nombre + ", a\u00f1oFabricacion=" + anioFabricacion + ", consumoInicial=" + consumoInicial + ", funcionando=" + funcionando + '}';
    }

    //Metodo para calcular el consumo real segun la antiguedad del dispositivo
    public int consumoReal() {
        int antiguedad = 2025 - this.anioFabricacion;
        if (antiguedad < 5) {
            return this.consumoInicial;
        } else if (antiguedad >= 5 & antiguedad < 10) {
            return (this.consumoInicial + (this.consumoInicial * 2 / 100));
        } else {
            return (this.consumoInicial + (this.consumoInicial * 3 / 100));
        }
    }
}
