package com.example.pmdm03_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// La clase MainActivity implementa View.OnClickListener,
// convirtiéndola en la única clase manejadora de eventos para todos los botones.
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
     * Alumno: Jaime Moro López
     */

    // Declaración de objetos de la UI
    private EditText etOperando1, etOperando2;
    private TextView tvResultado;
    private Button btnSuma, btnResta, btnMultiplicacion, btnDivision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inicialización de los elementos de la UI (Binding)
        etOperando1 = findViewById(R.id.et_operando1);
        etOperando2 = findViewById(R.id.et_operando2);
        tvResultado = findViewById(R.id.tv_resultado);
        btnSuma = findViewById(R.id.btn_suma);
        btnResta = findViewById(R.id.btn_resta);
        btnMultiplicacion = findViewById(R.id.btn_multiplicacion);
        btnDivision = findViewById(R.id.btn_division);

        // 2. Registro de eventos: Todos los botones apuntan a la misma instancia de escucha: 'this' (MainActivity)
        btnSuma.setOnClickListener(this);
        btnResta.setOnClickListener(this);
        btnMultiplicacion.setOnClickListener(this);
        btnDivision.setOnClickListener(this);
    }

    // 3. Método Único Manejador de Eventos (Implementado de View.OnClickListener)
    @Override
    public void onClick(View v) {
        // La clave es identificar qué botón se ha pulsado mediante su ID
        int id = v.getId();

        // El resultado de la operación
        double resultado = 0;
        // El símbolo de la operación
        String simbolo = "";

        // Captura y Control de Excepciones: Try/Catch global para manejo de errores
        try {
            // 3.1. Leer y Validar los Operandos
            String sOperando1 = etOperando1.getText().toString().trim();
            String sOperando2 = etOperando2.getText().toString().trim();

            // Control de operando vacío
            if (sOperando1.isEmpty() || sOperando2.isEmpty()) {
                Toast.makeText(this, "Debe introducir ambos operandos.", Toast.LENGTH_SHORT).show();
                tvResultado.setText("Resultado: Error (Datos Incompletos)");
                return; // Sale del método
            }

            // Convertir a tipo double. Se utiliza Double.parseDouble()
            // Si el texto no es un número válido, lanzará una NumberFormatException (capturada abajo)
            double op1 = Double.parseDouble(sOperando1);
            double op2 = Double.parseDouble(sOperando2);

            // 3.2. Identificar el Botón Pulsado y Realizar la Operación
            if (id == R.id.btn_suma) {
                resultado = op1 + op2;
                simbolo = "+";
            } else if (id == R.id.btn_resta) {
                resultado = op1 - op2;
                simbolo = "-";
            } else if (id == R.id.btn_multiplicacion) {
                resultado = op1 * op2;
                simbolo = "*";
            } else if (id == R.id.btn_division) {
                // Controlar la división por cero (ArithmeticException potencial)
                if (op2 == 0) {
                    Toast.makeText(this, "Error: División por cero no permitida.", Toast.LENGTH_LONG).show();
                    tvResultado.setText("Resultado: Error (División por 0)");
                    return; // Sale del método
                }
                resultado = op1 / op2;
                simbolo = "/";
            } else {
                // En caso de que se pulse un View que no se espera (aunque no debería pasar)
                return;
            }

            // 3.3. Mostrar Resultado Final
            String resultadoFormateado = String.format("%.2f", resultado); // Formatear a dos decimales
            tvResultado.setText("Resultado: " + op1 + " " + simbolo + " " + op2 + " = " + resultadoFormateado);

        } catch (NumberFormatException e) {
            // Captura de Excepción de Tipo: Si el usuario introduce texto en lugar de números
            Toast.makeText(this, "Error de formato: Asegúrese de introducir números reales válidos.", Toast.LENGTH_LONG).show();
            tvResultado.setText("Resultado: Error (Formato Inválido)");
        } catch (Exception e) {
            // Captura de cualquier otra excepción inesperada
            Toast.makeText(this, "Error inesperado: " + e.getMessage(), Toast.LENGTH_LONG).show();
            tvResultado.setText("Resultado: Error Inesperado");
        }
    }
}