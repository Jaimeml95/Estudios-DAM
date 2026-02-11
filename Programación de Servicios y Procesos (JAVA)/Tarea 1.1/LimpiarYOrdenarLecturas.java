import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class LimpiarYOrdenarLecturas {

    // Clase interna para almacenar y definir la lógica de ordenación
    static class Lectura implements Comparable<Lectura> {
        String sensorId;
        LocalDateTime timestamp;
        double valor;

        public Lectura(String sensorId, LocalDateTime timestamp, double valor) {
            this.sensorId = sensorId;
            this.timestamp = timestamp;
            this.valor = valor;
        }

        // Implementación de compareTo para ordenar: 1. sensorId, 2. timestamp (ascendentes)
        @Override
        public int compareTo(Lectura other) {
            int idComparison = this.sensorId.compareTo(other.sensorId);
            if (idComparison != 0) {
                return idComparison; // Ordenar por Sensor ID
            }
            return this.timestamp.compareTo(other.timestamp); // Luego por Timestamp
        }
    }

    public static void main(String[] args) {
        
        final double MIN_RANGO = -20.0;
        final double MAX_RANGO = 60.0;
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        List<Lectura> lecturas = new ArrayList<>();

        // 1. Leer desde STDIN (la salida del proceso anterior, SimularLecturas)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    String[] partes = linea.split(";");
                    if (partes.length != 3) {
                        continue; // Descartar: número de campos incorrecto
                    }

                    String sensorId = partes[0].trim();
                    String tsString = partes[1].trim();
                    // Usamos trim() para limpiar el valor de espacios en blanco
                    double valor = Double.parseDouble(partes[2].trim()); 

                    // 2. Filtrado de Lecturas: Descartar si está fuera de rango
                    if (valor < MIN_RANGO || valor > MAX_RANGO) {
                        continue; 
                    }

                    // 3. Parseo y almacenamiento
                    LocalDateTime timestamp = LocalDateTime.parse(tsString, FORMATTER);
                    lecturas.add(new Lectura(sensorId, timestamp, valor));

                } catch (NumberFormatException | DateTimeParseException e) {
                    // Descartar: formato de valor o fecha inválido
                    continue; 
                }
            }
        } catch (Exception e) {
            System.err.println("Error de lectura o procesamiento en LimpiarYOrdenarLecturas: " + e.getMessage());
        }

        // 4. Ordenación de todas las lecturas
        Collections.sort(lecturas);

        // 5. Escritura del resultado ordenado a STDOUT
        for (Lectura lectura : lecturas) {
            // Normalizar el valor a 2 decimales para la salida final
            System.out.printf(Locale.US, "%s;%s;%.2f%n", 
                lectura.sensorId, 
                lectura.timestamp.format(FORMATTER), 
                lectura.valor
            );
        }
    }
}