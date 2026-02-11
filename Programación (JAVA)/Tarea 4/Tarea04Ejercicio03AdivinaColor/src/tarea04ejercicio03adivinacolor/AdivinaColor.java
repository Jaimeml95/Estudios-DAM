/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea04ejercicio03adivinacolor;

import java.util.Scanner;

/**
 *
 * @author tpmvo
 */
public class AdivinaColor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner (System.in);
        String miColor = "azul";
        String respuesta;
        int contador = 0;
        
        System.out.println("Introduce un color:");
        while (true) {
            respuesta = teclado.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                break;
            }
                contador++;
                if (respuesta.equalsIgnoreCase(miColor)) {
                    System.out.println("¡Has acertado!");
                    break;
                } else {
                    System.out.println("¡No es correcto!");
                    System.out.println("¿Quieres volver a intentarlo? Introduce un nuevo color o en caso contrario introduce \"n\"");
                    }
            }
        
        
        System.out.println("Has jugado " + contador + (contador ==1 ? " vez." : " veces.") );
          
    }
    
}
