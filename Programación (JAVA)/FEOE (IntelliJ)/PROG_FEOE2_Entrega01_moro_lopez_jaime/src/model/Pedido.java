package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Contiene un conjunto de productos y calcula totales.
 */
public class Pedido {
    private List<Producto> items = new ArrayList<>();

    public void agregarProducto(Producto p) {
        items.add(p);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : items) {
            total += p.getPrecio();
        }
        return total;
    }

    public void mostrarPedido() {
        if (items.isEmpty()) {
            System.out.println("El pedido está vacío.");
            return;
        }
        System.out.println("Productos en el pedido:");
        for (Producto p : items) {
            System.out.println("  • " + p);
        }
        System.out.printf("Total: €%.2f%n", calcularTotal());
    }

    public boolean eliminarProducto(int codigo) {
        Iterator<Producto> it = items.iterator();
        while (it.hasNext()) {
            Producto p = it.next();
            if (p.getCodigo() == codigo) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}
