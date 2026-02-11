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

public class Ejercicio2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File("Ejercicio2.txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                BufferedReader br = new BufferedReader(new FileReader(f))) {

            System.out.print("Introduce un texto: ");
            String texto = sc.nextLine();
            bw.write(texto);
            bw.flush(); // Asegurar escritura

            System.out.println("Contenido del fichero:");
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
