import java.io.Serializable;

// Para almacenar la información en memoria.
public class Producto implements Serializable {
    private String codigo;
    private String nombre;
    private int stock;
    private double precio;

    public Producto(String codigo, String nombre, int stock, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getStock() { return stock; }
    public double getPrecio() { return precio; }

    @Override
    public String toString() {
        return String.format("%s - %s - Stock: %d - Precio: %.2f€", codigo, nombre, stock, precio);
    }
}