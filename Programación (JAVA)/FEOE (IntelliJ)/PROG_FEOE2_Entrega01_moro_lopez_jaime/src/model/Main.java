package model;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Usa un Set<Producto> para el menú y busca por codigo1 iterando.
 */
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Set<Producto> menu = crearMenu();
        Pedido pedido = new Pedido();


        byte opcion;
        do {
            mostrarOpciones();
            opcion = (Byte.parseByte(entrada.nextLine()));

            switch (opcion) {
                case 1:
                    System.out.println("\nMenú disponible:\n");
                    for (Producto p : menu) {
                        System.out.println("  " + p);
                    }
                    break;
                case 2:
                    System.out.print("Introduce codigo del producto: ");
                    int codigo = Integer.parseInt(entrada.nextLine());
                    Producto encontrado = null;
                    for (Producto p : menu) {
                        if (p.getCodigo() == codigo) {
                            encontrado = p;
                            break;
                        }
                    }
                    if (encontrado != null) {
                        pedido.agregarProducto(encontrado);
                        System.out.println("Añadido: " + encontrado.getNombre() + "\n");
                    } else {
                        System.out.println("Codigo inválido. Inténtalo de nuevo.\n");
                    }
                    break;
                case 3:
                    System.out.println();
                    pedido.mostrarPedido();
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Introduce el código del producto a eliminar: ");
                    codigo = Integer.parseInt(entrada.nextLine());
                    if (pedido.eliminarProducto(codigo)) {
                        System.out.println("Producto eliminado correctamente.\n");
                    } else {
                        System.out.println("No se encontró un producto con ese código en el pedido.\n");
                    }
                    break;
                case 5:
                    System.out.println("\nPedido finalizado. ¡Gracias!");
                    System.out.printf("Total a pagar: €%.2f%n", pedido.calcularTotal());
                    break;
                default:
                    System.out.println("Opción desconocida. Usa 1–4.\n");
            }
        } while (opcion != 5);

        entrada.close();
    }

    private static Set<Producto> crearMenu() {
        Set<Producto> menu = new HashSet<>();
        menu.add(new Producto(1, "Hamburguesa", 5.50));
        menu.add(new Producto(2, "Pizza Margarita", 7.20));
        menu.add(new Producto(3, "Ensalada César", 4.80));
        menu.add(new Producto(4, "Refresco", 1.50));
        menu.add(new Producto(5, "Cerveza", 2.00));
        return menu;
    }

    private static void mostrarOpciones() {
        System.out.println("\nGESTION DE PEDIDOS\n");
        System.out.println("Elige una opción.\n");
        System.out.println("Opción 1: Ver menú");
        System.out.println("Opción 2: Añadir producto al pedido");
        System.out.println("Opción 3: Mostrar pedido actual");
        System.out.println("Opción 4: Quitar producto del pedido");
        System.out.println("Opción 5: Finalizar pedido");
    }
}