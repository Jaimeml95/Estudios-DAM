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

public class Ejercicio4 {
    public static void main(String[] args) {
        String[] apellidos = {"Fernández Pérez", "López Morán", "Sánchez Timón", "Álvarez Chen", "Castro Serrano", "Casillas Carbonero", "Salas Nuñez"};
        int[] edades = {17, 20, 18, 17, 19, 21, 20};
        double[] notas = {7.5, 4.2, 6.5, 8.0, 3.2, 9.2, 9.9};

        try (RandomAccessFile raf = new RandomAccessFile("alumnos.dat", "rw")) {
            for (int i = 0; i < apellidos.length; i++) {
                StringBuffer sb = new StringBuffer(apellidos[i]);
                sb.setLength(20); // Longitud fija para apellidos
                raf.writeChars(sb.toString());
                raf.writeInt(edades[i]);
                raf.writeDouble(notas[i]);
            }

            // Lectura
            raf.seek(0);
            System.out.println("Contenido de alumnos.dat:");
            while (raf.getFilePointer() < raf.length()) {
                String ape = "";
                for (int i = 0; i < 20; i++) ape += raf.readChar();
                int edad = raf.readInt();
                double nota = raf.readDouble();
                System.out.println("Apellido: " + ape.trim() + " | Edad: " + edad + " | Nota: " + nota);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
