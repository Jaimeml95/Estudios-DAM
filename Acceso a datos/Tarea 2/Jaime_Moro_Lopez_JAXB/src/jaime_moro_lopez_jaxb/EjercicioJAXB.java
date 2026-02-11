/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaime_moro_lopez_jaxb;

/**
 *
 * @author tpmvo
 */
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class EjercicioJAXB {
    public static void main(String[] args) {
        try {
            // 1. Crear el contexto de JAXB para nuestras clases
            JAXBContext context = JAXBContext.newInstance(ListaAlumnos.class);
            
            // 2. Crear el objeto Unmarshaller (de XML a Java)
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            // 3. Leer el fichero creado en el ejercicio anterior
            File fichero = new File("alumnos.xml");
            
            if (fichero.exists()) {
                // 4. Convertir el XML en objetos Java
                ListaAlumnos alumnos = (ListaAlumnos) unmarshaller.unmarshal(fichero);
                
                // 5. Mostrar por pantalla
                System.out.println("=== Listado de Alumnos desde XML (JAXB) ===");
                for (Alumno a : alumnos.getListaAlumnos()) {
                    System.out.println("Apellido: " + a.getApellido() + 
                                     " | Edad: " + a.getEdad() + 
                                     " | Nota: " + a.getNota());
                }
            } else {
                System.out.println("Error: El fichero alumnos.xml no existe.");
            }
            
        } catch (Exception e) {
            System.err.println("Error al leer con JAXB: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
