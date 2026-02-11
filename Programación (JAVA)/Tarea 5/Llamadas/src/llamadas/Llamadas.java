/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package llamadas;

import java.util.Scanner;

/**
 *
 * @author tpmvo
 */
public class Llamadas {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        //Recogemos todo el codigo en try-catch para capturar las posibles excepciones que haya
        try {

            //Solicitando las entradas del usuario
            
            System.out.println("Aplicacion Movil\n");
            System.out.println("Introduce los siguientes datos para registrar el movil");
            System.out.println("Numero de teléfono");
            int numTelefono = Integer.parseInt(entrada.nextLine());        
            System.out.println("IMEI");
            long IMEI = Long.parseLong(entrada.nextLine());
            System.out.println("Modelo");
            String modelo = entrada.nextLine();
            //Creacion de los objetos

            Movil movil1 = new Movil(numTelefono, IMEI, modelo);
            
            //Ejecucion del metodo llamarA 3 veces
            
            System.out.println("Introduce un numero de telefono");
            int numero = Integer.parseInt(entrada.nextLine());
            movil1.llamarA(numero);
            System.out.println("Introduce un numero de telefono");
            numero = Integer.parseInt(entrada.nextLine());
            movil1.llamarA(numero);
            System.out.println("Introduce un numero de telefono");
            numero = Integer.parseInt(entrada.nextLine());
            movil1.llamarA(numero);

            //Salida por pantalla de los datos de movil1

            System.out.println(movil1.toString());
            } catch (Exception e) {
            System.err.println("Introduce un numero valido");
            e.printStackTrace();
        }
    }

}
