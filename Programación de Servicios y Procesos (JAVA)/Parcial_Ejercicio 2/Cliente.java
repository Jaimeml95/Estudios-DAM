import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Cliente TCP que muestra menú y conecta con Servidor.
 */
public class Cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        System.out.println("Conectando al servidor en " + HOST + ":" + PUERTO + "...");

        // 1. Establecer conexión con el servidor
        try (Socket socket = new Socket(HOST, PUERTO);
                Scanner scanner = new Scanner(System.in);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

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
                        comandoAEnviar = "CODIGO";
                        break;
                    case "2":
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

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("   MENÚ DE CONSULTA DE CODIGOS");
        System.out.println("------------------------------------");
        System.out.println("1. Envio del codigo");
        System.out.println("2. Salir");
        System.out.println("------------------------------------");
    }
}