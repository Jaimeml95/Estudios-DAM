import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Servidor TCP secuencial (sin hilos).
 * Carga productos y atiende a un único cliente.
 */
public class ServidorAlmacen {

    private static final int PUERTO = 5000;
    private static final String ARCHIVO_CSV = "productos.csv";
    // Mapa para búsqueda rápida por código
    private static Map<String, Producto> inventario = new HashMap<>();

    public static void main(String[] args) {
        // 1. Cargar datos antes de iniciar el servidor
        if (!cargarProductos()) {
            System.err.println("Error crítico: No se pudieron cargar los productos.");
            return;
        }

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
        String codigo = (partes.length > 1) ? partes[1].toUpperCase() : "";

        switch (comando) {
            case "LISTA":
                System.out.println("Enviando lista completa de productos...");
                StringBuilder sb = new StringBuilder();
                if (inventario.isEmpty()) {
                    return "No hay productos registrados.";
                }
                for (Producto p : inventario.values()) {
                    sb.append(p.toString()).append("\n");
                }
                // Eliminamos el último salto de línea
                return sb.toString().trim();

            case "STOCK":
                if (validarCodigo(codigo)) {
                    System.out.println("Consultando stock del producto: " + codigo);
                    return "Stock disponible de " + codigo + ": " + inventario.get(codigo).getStock() + " unidades.";
                } else {
                    return generarErrorProducto(codigo);
                }

            case "PRECIO":
                if (validarCodigo(codigo)) {
                    System.out.println("Consultando precio del producto: " + codigo);
                    return "Precio de " + codigo + ": " + inventario.get(codigo).getPrecio() + "€";
                } else {
                    return generarErrorProducto(codigo);
                }

            case "INFO":
                if (validarCodigo(codigo)) {
                    System.out.println("Mostrando información completa del producto " + codigo);
                    Producto p = inventario.get(codigo);
                    return "Código: " + p.getCodigo() + "\n" +
                           "Nombre: " + p.getNombre() + "\n" +
                           "Stock: " + p.getStock() + " unidades\n" +
                           "Precio: " + p.getPrecio() + "€";
                } else {
                    return generarErrorProducto(codigo);
                }

            case "FIN":
                return "Conexión finalizada. Gracias por usar el sistema.";

            default:
                return "ERROR: Comando no reconocido o formato incorrecto.";
        }
    }

    private static boolean validarCodigo(String codigo) {
        return codigo != null && inventario.containsKey(codigo);
    }

    private static String generarErrorProducto(String codigo) {
        System.out.println("ERROR: Código de producto no existente -> " + codigo);
        return "ERROR: El producto con código " + (codigo.isEmpty() ? "(vacío)" : codigo) + " no existe.";
    }

    // Carga del CSV
    private static boolean cargarProductos() {
        System.out.println("Cargando archivo " + ARCHIVO_CSV + "...");
        File archivo = new File(ARCHIVO_CSV);
        if (!archivo.exists()) {
            System.err.println("El archivo no existe.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Saltamos la cabecera si existe
            br.readLine(); 
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 4) {
                    String cod = datos[0];
                    String nom = datos[1];
                    int stock = Integer.parseInt(datos[2]);
                    double precio = Double.parseDouble(datos[3]);
                    
                    inventario.put(cod, new Producto(cod, nom, stock, precio));
                }
            }
            System.out.println("Productos cargados: " + inventario.size());
            return true;
        } catch (Exception e) {
            System.err.println("Error al leer CSV: " + e.getMessage());
            return false;
        }
    }
}