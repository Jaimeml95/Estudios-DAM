/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea062farmacia;

import java.io.*;
import java.util.*;

/**
 *
 * @author tpmvo
 */
public class ClasePrincipal {

    private static final String FICHERO = "datos.dat";
    private static TreeMap<String, Articulo> articulos = new TreeMap<>();

    public static void main(String[] args) {
        cargarDatos();

        Scanner entrada = new Scanner(System.in);
        byte opcion;

        do {
            System.out.println("\n1. Añadir artículo");
            System.out.println("2. Buscar artículo");
            System.out.println("3. Mostrar todos los artículos");
            System.out.println("4. Guardar y salir");
            System.out.print("Elige una opción: ");
            opcion = Byte.parseByte(entrada.nextLine());

            switch (opcion) {
                case 1:
                    aniadirArticulo(entrada);
                    break;
                case 2:
                    buscarArticulo(entrada);
                    break;
                case 3:
                    mostrarArticulos();
                    break;
                case 4:
                    guardarDatos();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

        } while (opcion != 4);
    }

    private static void cargarDatos() {
        File fichero = new File(FICHERO);
        if (fichero.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
                articulos = (TreeMap<String, Articulo>) ois.readObject();
                System.out.println("Datos cargados correctamente.");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error al cargar datos: " + e.getMessage());
            }
        } else {
            System.out.println("Fichero no encontrado. Se creará uno nuevo al guardar.");
        }
    }

    private static void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
            oos.writeObject(articulos);
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    private static void aniadirArticulo(Scanner entrada) {
        System.out.print("Introduce código (4 números + 4 letras): ");
        String codigo = entrada.nextLine().toUpperCase();

        if (articulos.containsKey(codigo)) {
            System.out.println("Ese código ya existe.");
            return;
        }

        System.out.print("Denominación: ");
        String denominacion = entrada.nextLine();

        System.out.print("Unidades: ");
        int unidades = Integer.parseInt(entrada.nextLine());

        System.out.print("Precio: ");
        double precio = Double.parseDouble(entrada.nextLine());

        articulos.put(codigo, new Articulo(denominacion, (short) unidades, precio));
        System.out.println("Artículo añadido.");
    }

    private static void buscarArticulo(Scanner entrada) {
        System.out.print("Introduce código a buscar: ");
        String codigo = entrada.nextLine().toUpperCase();

        Articulo art = articulos.get(codigo);
        if (art != null) {
            System.out.println(art);
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }

    private static void mostrarArticulos() {
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos.");
        } else {
            for (Map.Entry<String, Articulo> entry : articulos.entrySet()) {
                System.out.println("Código: " + entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
