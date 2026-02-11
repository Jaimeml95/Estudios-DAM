import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

public class TransformarLecturas {

    // Umbral de alerta por defecto si no se especifica --minAlert

    private static final double DEFAULT_MIN_ALERT = 30.0;

    public static void main(String[] args) {

        // 1. Manejo de argumentos: <entrada.csv> <salida.csv> [--minAlert=umbral]

        if (args.length < 2) {

            System.err.println("Uso: java TransformarLecturas <entrada.csv> <salida.csv> [--minAlert=umbral]");

            // Código de salida no cero para indicar error

            System.exit(1);

        }

        String inputFile = args[0];

        String outputFile = args[1];

        double minAlert = DEFAULT_MIN_ALERT;

        // 2. Procesamiento del argumento opcional --minAlert

        for (int i = 2; i < args.length; i++) {

            if (args[i].startsWith("--minAlert=")) {

                try {

                    String val = args[i].substring("--minAlert=".length());

                    minAlert = Double.parseDouble(val);

                } catch (NumberFormatException e) {

                    System.err.println("Umbral de alerta inválido. Usando: " + DEFAULT_MIN_ALERT);

                }

            }

        }

        // 3. Lógica principal de lectura, transformación y escritura

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));

                BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            String linea;

            boolean isHeader = true;

            while ((linea = br.readLine()) != null) {

                if (isHeader) {

                    // Escribir la cabecera original + el nuevo campo 'estado'

                    bw.write(linea + ";estado");

                    bw.newLine();

                    isHeader = false;

                    continue; // Saltar a la siguiente línea (datos)

                }

                // 4. Transformación de la línea de datos

                String[] partes = linea.split(";");

                if (partes.length >= 3) {

                    try {

                        // El valor está en la tercera columna (índice 2)

                        double valor = Double.parseDouble(partes[2].trim());

                        String estado = (valor >= minAlert) ? "ALERTA" : "OK";

                        // Escribir la línea original más el estado

                        bw.write(linea + ";" + estado);

                        bw.newLine();

                    } catch (NumberFormatException e) {

                        // Ignorar líneas con valor no numérico (puede loguearse para debug)

                        continue;

                    }

                }

            }

        } catch (IOException e) {

            System.err.println("Error de E/S al procesar " + inputFile + ": " + e.getMessage());

            System.exit(1);

        }

    }

}