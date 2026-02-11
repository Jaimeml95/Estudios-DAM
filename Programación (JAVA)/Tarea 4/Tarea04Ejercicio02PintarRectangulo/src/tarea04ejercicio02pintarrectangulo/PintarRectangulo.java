/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04ejercicio02pintarrectangulo;

import java.util.Scanner;

/**
 *
 * @author tpmvo
 */
public class PintarRectangulo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner teclado = new Scanner (System.in);
        
        System.out.println("Introduce la altura del rectangulo");
        try {
        int altura = Integer.parseInt(teclado.nextLine());
        int contador1;
        int contador2;
        if (altura <= 0) {
        System.out.println("Introduce una altura valida");
        } else {
            for (contador1=1; contador1<=altura; contador1++)
            {
               for (contador2=1; contador2<=altura*2; contador2++)
               {
                   System.out.print("*");
               }
            System.out.println();
            }
        }
        
        } catch (NumberFormatException e) {
            System.err.println("Introduce un numero entero");
        }
    }
}
