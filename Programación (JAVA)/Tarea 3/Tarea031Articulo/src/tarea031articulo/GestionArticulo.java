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
public class GestionArticulo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Creación de los objetos
        Articulo articulo1 = new Articulo ("83A42F531", "Paquete de 500 folios A4", (short) 200, 5.49, (byte) 5);
        Articulo articulo2 = new Articulo ();
        
        // Pasamos los datos al articulo2 mediante los setters
        articulo2.setCodigoBarra("13F42X332");
        articulo2.setNombre("Paquete de 180g cartulinas A4");
        articulo2.setUnidades((short)150);
        articulo2.setPrecioCompra(4.05);
        articulo2.setBeneficio((byte)10);
        
        //Salida de datos por pantalla
        
        //articulo1
        System.out.println("Artículo 1 (usando getters):");
        System.out.println("Código de barras: " + articulo1.getCodigoBarra());
        System.out.println("Nombre: " + articulo1.getNombre());
        System.out.println("Unidades: " + articulo1.getUnidades());
        System.out.println("Precio de compra: " + articulo1.getPrecioCompra());
        System.out.println("Beneficio: " + articulo1.getBeneficio() + "%");
        System.out.println("PVP: " + articulo1.pvp());
        
        //articulo2
        System.out.println("\nArtículo 2 (usando toString):");
        System.out.println(articulo2.toString());
    }
    
}