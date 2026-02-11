/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaime_moro_lopez_tarea2;

/**
 *
 * @author tpmvo
 */
import java.io.*;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("Ejercicio2.dat");
        
        try (FileOutputStream fos = new FileOutputStream(f);
             FileInputStream fis = new FileInputStream(f)) {
            
            System.out.print("Introduce texto para el binario: ");
            String texto = sc.nextLine();
            fos.write(texto.getBytes());
            
            System.out.println("Leyendo fichero binario:");
            int i;
            while ((i = fis.read()) != -1) {
                System.out.print((char) i);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}