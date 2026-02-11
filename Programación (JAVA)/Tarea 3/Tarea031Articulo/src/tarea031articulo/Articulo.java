/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea031articulo;

/**
 *
 * @author tpmvo
 */
public class Articulo {
    
    //Atributos
    
    private String codigoBarra;
    private String nombre;
    //Suponiendo que el numero de articulos pudiese superar eel almacenamiento del byte (500 articulos por ejemplo)
    private short unidades;
    //Utilizaremos el tipo double para asegurarnos que los errores cometidos por las futuras aproximaciones sean menores
    private double precioCompra;
    private byte beneficio;
    
    //Constructor por defecto
    
    public Articulo() {
    }
    
    //Constructor con parametros
    
    public Articulo(String codigoBarra, String nombre, short unidades, double precioCompra, byte beneficio) {
        this.codigoBarra = codigoBarra;
        this.nombre = nombre;
        this.unidades = unidades;
        this.precioCompra = precioCompra;
        this.beneficio = beneficio;
    }

    //Metodos get y set de cada atributo

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getUnidades() {
        return unidades;
    }

    public void setUnidades(short unidades) {
        this.unidades = unidades;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public byte getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(byte beneficio) {
        this.beneficio = beneficio;
    }
    
    //Metodo pvp
    
    public double pvp() {
        return precioCompra+((precioCompra*beneficio)/100);
    }
    
    //Metodo toString

    @Override
public String toString() {
    return "Código de barras: " + codigoBarra +
           "\nNombre: " + nombre +
           "\nUnidades: " + unidades +
           "\nPrecio de compra: " + precioCompra +
           "\nBeneficio: " + beneficio + "%" +
           "\nPVP: " + pvp();
}
     
}
