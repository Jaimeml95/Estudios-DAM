import java.io.*;
import java.net.*;

/**
 * Servidor TCP secuencial (sin hilos).
 * Carga productos y atiende a un único cliente.
 */
public class Servidor {

    private static final int PUERTO = 5000;
    private static final String ARCHIVO_TXT = "codigo.txt";

    public static void main(String[] args) {

        System.out.println("Servidor iniciado en el puerto " + PUERTO + "...");

        // 2. Iniciar ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            
            // Esperar conexión (bloqueante)
            System.out.println("Esperando cliente...");
            Socket clienteSocket = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + clienteSocket.getInetAddress());

            // 3. Crear flujos de entrada/salida
            // Usamos BufferedReader y PrintWriter para texto
            BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true);

            String comandoCliente;
            boolean continuar = true;

            // 4. Bucle de procesamiento de comandos
            while (continuar && (comandoCliente = entrada.readLine()) != null) {
                System.out.println("[COMANDO] " + comandoCliente);
                
                // Procesar la petición y obtener respuesta
                String respuesta = procesarComando(comandoCliente);
                
                // Enviar respuesta al cliente
                salida.println(respuesta);
                
                // Enviar marca de fin de respuesta para que el cliente sepa cuándo parar de leer
                salida.println("__EOF__");

                // Verificar si hay que cerrar
                if (comandoCliente.equals("FIN")) {
                    continuar = false;
                    System.out.println("Cliente solicita finalizar conexión.");
                }
            }

            // 5. Cierre de recursos
            clienteSocket.close();
            System.out.println("Cerrando conexión...");
            System.out.println("Servidor finalizado.");

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private static String procesarComando(String linea) {
        String[] partes = linea.trim().split(" ");
        String comando = partes[0].toUpperCase();

        switch (comando) {
            case "CODIGO":
                return mostrarCodigo();

            case "FIN":
                return "Conexión finalizada. Gracias por usar el sistema.";

            default:
                return "ERROR: Comando no reconocido o formato incorrecto.";
        }
    }
        private static String mostrarCodigo() {
            System.out.println("Cargando archivo " + ARCHIVO_TXT + "...");
            File archivo = new File(ARCHIVO_TXT);
            if (!archivo.exists()) {
                return "El archivo no existe.";
            }
    
            try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
                String codigo = br.readLine();
            return codigo;
            } catch (Exception e) {
                System.err.println();
                return "Error al leer TXT: " + e.getMessage();
            }
        }
}