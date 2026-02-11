/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactogmail;

/**
 *
 * @author tpmvo
 */
import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class ManejarFicheros {

    // Creamos un TreeSet para guardar los contactos.
    public Set<Contacto> leerFicheroCSV(String ruta) {
        TreeSet<Contacto> contactos = new TreeSet<>();
        System.out.println("\nLeyendo fichero " + ruta);
        // Declaramos un BufferedReader (nos ayuda a leer texto línea por línea).
        BufferedReader br = null;
        // linea es donde vamos guardando cada línea leída.
        String linea;
        // primeraLinea es para saltar el encabezado del CSV.
        boolean primeraLinea = true;

        try {
            // Abrimos el fichero con FileReader, y lo envolvemos en BufferedReader para hacerlo más eficiente.
            br = new BufferedReader(new FileReader(ruta));
            // Empezamos a leer línea por línea hasta que no haya más.
            while ((linea = br.readLine()) != null) {
                // Saltamos la primera línea del fichero (encabezados como "Nombre, Apellidos, Email...").
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltamos cabecera
                }
                // Separamos la línea en partes usando la coma , como separador. Cada parte va a un array campos.
                String[] campos = linea.split(",");

                // Solo seguimos si la línea tiene al menos 31 campos (para evitar errores).
                // Luego sacamos los datos que necesitamos de las posiciones específicas del CSV.
                if (campos.length > 30) {
                    String nombre = campos[1].trim();
                    String apellidos = campos[3].trim();
                    String email = campos[30].trim();

                    // Si el email no está vacío, creamos un nuevo Contacto y lo metemos en el TreeSet.
                    if (!email.isEmpty()) {
                        Contacto c = new Contacto(nombre, apellidos, email);
                        contactos.add(c); // TreeSet se encarga de duplicados y orden.
                    }
                }
            }
            // Manejamos errores por si el fichero no existe o hay algún problema al leerlo.
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            // Cerramos el fichero para liberar memoria.
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }

        return contactos;
    }

    public void escribirFicheroTexto(String nombreFichero, Set<Contacto> contactos) {
        System.out.println("\nEscribiendo fichero");
        // Declaramos un BufferedWriter, que permite escribir texto de forma eficiente.
        BufferedWriter bw = null;

        try {
            // Abrimos el fichero para escritura. Si no existe, lo crea. Si ya existe, lo sobrescribe.
            bw = new BufferedWriter(new FileWriter(nombreFichero));

            // Recorremos todos los contactos del TreeSet.
            // Por cada uno, escribimos los datos en una línea del fichero.
            for (Contacto c : contactos) {
                bw.write(c.getNombre() + "," + c.getApellidos() + "," + c.getEmail());
                bw.newLine();
            }

            System.out.println("Fichero escrito correctamente: " + nombreFichero);

        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    // Cerramos el fichero cuando terminamos para guardar bien los datos y liberar memoria.
                    bw.close();
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }
}
