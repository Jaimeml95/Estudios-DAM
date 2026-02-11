


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tarea032Movil;

/**
 *
 * @author tpmvo
 */

import java.util.Scanner;

public class AplicacionMovil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner entrada = new Scanner(System.in);
        
        //Recogemos todo el codigo en try-catch para capturar las posibles excepciones que haya
        
        try
        {
        
            //Solicitando las entradas del usuario
            
            System.out.println("Aplicacion Movil\n");
            System.out.println("Introduce los siguientes datos para registrar el movil");
            System.out.println("Numero de teléfono");
            int num = Integer.parseInt(entrada.nextLine());        
            System.out.println("IMEI");
            long IMEI = Long.parseLong(entrada.nextLine());
            System.out.println("Marca");
            String marca = entrada.nextLine();
            System.out.println("Modelo");
            String modelo = entrada.nextLine();
            System.out.println("Tipo de sistema operativo");
            String tipo = entrada.nextLine();
            System.out.println("Version del sistema operativo");
            int version = Integer.parseInt(entrada.nextLine());        
            System.out.println("Resolucion de la camara frontal");
            int resolucionF = Integer.parseInt(entrada.nextLine());        
            System.out.println("Numero de camaras frontales");
            int numCamF = Integer.parseInt(entrada.nextLine());        
            System.out.println("¿La camara frontal tiene estabilizador? (true/false)");
            boolean estabilizadorF = Boolean.parseBoolean(entrada.nextLine());
            System.out.println("Resolucion de la camara trasera");
            int resolucionT = Integer.parseInt(entrada.nextLine());        
            System.out.println("Numero de camaras traseras");
            int numCamT = Integer.parseInt(entrada.nextLine());        
            System.out.println("¿La camara trasera tiene estabilizador? (true/false)");
            boolean estabilizadorT = Boolean.parseBoolean(entrada.nextLine());
        
            //Creacion de los objetos

            So sistemaOperativo = new So (tipo, version);
            Cam camaraFrontal = new Cam(resolucionF, numCamF, estabilizadorF);
            Cam camaraTrasera = new Cam(resolucionT, numCamT, estabilizadorT);
            Movil movil1 = new Movil(num, IMEI, marca, modelo, sistemaOperativo, camaraFrontal, camaraTrasera);

            //Salida por pantalla de los datos de movil1

            System.out.println(movil1.toString());
        
        } catch (Exception e) {
            System.err.println("Introduce un numero valido");
            e.printStackTrace();
        }
    }
    
}
