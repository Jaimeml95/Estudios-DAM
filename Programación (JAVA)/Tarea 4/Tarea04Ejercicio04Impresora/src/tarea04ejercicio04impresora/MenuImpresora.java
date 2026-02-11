/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04ejercicio04impresora;

import java.util.Scanner;

/**
 *
 * @author tpmvo
 */
public class MenuImpresora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner (System.in);
        byte opcion;
        Impresora impresora1 = null;
        try {
            do {
                System.out.println("\nMENU IMPRESORA\n");
                System.out.println("Elige una opción.\n");
                System.out.println("Opción 1: Crear impresora");
                System.out.println("Opción 2: Mostrar datos");
                System.out.println("Opción 3: Imprimir");
                System.out.println("Opción 4: Escanear");
                System.out.println("Opción 5: Salir");

                opcion = (Byte.parseByte(teclado.nextLine()));

                switch (opcion) {
                    case 1:
                        impresora1 = new Impresora("Epson NT", (byte) 100, (short) 512);
                        System.out.println("\nImpresora creada con éxito.");
                        break;
                    case 2:
                        if (impresora1 != null) {
                            System.out.println(impresora1.toString());
                        } else {
                            System.out.println("\nNo existe ninguna impresora creada.");
                        }
                        break;
                    case 3:
                        if (impresora1 != null) {
                            System.out.println("Introduce el número de páginas a imprimir:");
                            byte num = Byte.parseByte(teclado.nextLine());
                            impresora1.imprimir(num);
                        } else {
                            System.out.println("\nNo existe ninguna impresora creada.");
                        }
                        break;
                    case 4:
                        if (impresora1 != null) {
                            System.out.println("Introduce el número de páginas a escanear:");
                            byte num = Byte.parseByte(teclado.nextLine());
                            impresora1.escanear(num);
                        } else {
                            System.out.println("\nNo existe ninguna impresora creada.");
                        }
                        break;
                    case 5:
                        System.out.println("\nSaliendo del menú...");
                        System.out.println("Memoria borrada.");
                        break;
                    default:
                        System.out.println("\nError: introduce una opción válida.");
                }
            } while (opcion != 5);
        } catch (NumberFormatException e) {
            System.err.println("\nDebes introducir un número entero válido");
        }    
    }
}
