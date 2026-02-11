/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04ejercicio01leernumero;

import java.util.Scanner;

/**
 *
 * @author tpmvo
 */
public class LeerNumero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner (System.in);
        
        System.out.println("Introduce un numero entero del 1 al 10");
        
        try {
            byte numero = Byte.parseByte(teclado.nextLine());
            String palabra;
        
            switch (numero) {
                case 1:
                    palabra = "uno";
                    break;
                case 2:
                    palabra = "dos";
                    break;
                case 3:
                    palabra = "tres";
                    break;
                case 4:
                    palabra = "cuatro";
                    break;
                case 5:
                    palabra = "cinco";
                    break;
                case 6:
                    palabra = "seis";
                    break;
                case 7:
                    palabra = "siete";
                    break;
                case 8:
                    palabra = "ocho";
                    break;
                case 9:
                    palabra = "nueve";
                    break;
                case 10:
                    palabra = "diez";
                    break;
                default:
                    palabra = "Numero fuera de rango";
            }        
            System.out.println(palabra);
        } catch (NumberFormatException e) {
            System.err.println("No has introducido un numero entero valido");
        }

        
    }
    
}

