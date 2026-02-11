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
public class So {
    
    //Atributos
    
    private String tipo;
    private int version;
    
    //Metodos
    
    //Constructores

    public So() {
    }

    public So(String tipo, int version) {
        this.tipo = tipo;
        this.version = version;
    }
    
    //Getters y Setters

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    //toString

    @Override
    public String toString() {
        return "So{" + "tipo=" + tipo + ", version=" + version + '}';
    }
    
    
}
