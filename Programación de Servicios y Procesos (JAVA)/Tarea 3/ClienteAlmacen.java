import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente TCP que muestra menú y conecta con ServidorAlmacen.
 */
public class ClienteAlmacen {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("Conectando al servidor en " + HOST + ":" + PUERTO + "...");

        // 1. Establecer conexión con el servidor
        try (Socket socket = new Socket(HOST, PUERTO);
                Scanner scanner = new Scanner(System.in);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            socket.setSoTimeout(5000); // 5 segundos de espera máxima para recibir datos
            System.out.println("Conexión establecida.");
            boolean salir = false;

            while (!salir) {
                mostrarMenu();
                System.out.print("Seleccione una opción: ");
                String opcion = scanner.nextLine();
                String comandoAEnviar = "";

                // Construcción del comando según el protocolo
                switch (opcion) {
                    case "1":
                        comandoAEnviar = "LISTA";
                        break;
                    case "2":
                        System.out.print("Introduzca código del producto: ");
                        comandoAEnviar = "STOCK " + scanner.nextLine().trim();
                        break;
                    case "3":
                        System.out.print("Introduzca código del producto: ");
                        comandoAEnviar = "PRECIO " + scanner.nextLine().trim();
                        break;
                    case "4":
                        System.out.print("Introduzca código del producto: ");
                        comandoAEnviar = "INFO " + scanner.nextLine().trim();
                        break;
                    case "5":
                        comandoAEnviar = "FIN";
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        continue; // Vuelve al inicio del while
                }

                // 2. Enviar comando al servidor
                System.out.println("\n>> Enviando comando: " + comandoAEnviar);
                salida.println(comandoAEnviar);

                // 3. Recibir respuesta del servidor
                System.out.println("<< Respuesta del servidor:");
                String lineaRespuesta;

                // Leemos línea a línea hasta encontrar la marca de fin (__EOF__)
                while ((lineaRespuesta = entrada.readLine()) != null) {
                    if (lineaRespuesta.equals("__EOF__")) {
                        break;
                    }
                    System.out.println(lineaRespuesta);
                }

                if (salir) {
                    System.out.println("\nCliente desconectado.");
                }
            }

        } catch (SocketTimeoutException e) {
            System.err.println(
                    "ERROR: El servidor está tardando demasiado en responder. Posiblemente esté ocupado ateniendo a otro cliente.");
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("   MENÚ DE CONSULTA DE PRODUCTOS");
        System.out.println("------------------------------------");
        System.out.println("1. Ver lista de productos");
        System.out.println("2. Consultar stock por código");
        System.out.println("3. Consultar precio por código");
        System.out.println("4. Información completa de un producto");
        System.out.println("5. Salir");
        System.out.println("------------------------------------");
    }
}