package model;

import java.util.Objects;

/**
 * Representa un producto del restaurante.
 * Definimos equals/hashCode basados en el codigo para poder usarlo en un Set.
 */
public class Producto {
    private int codigo;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(int id, String nombre, double precio) {
        this.codigo = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (€" + precio + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto p = (Producto) o;
        return codigo == p.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}