package com.example.pmdm03_3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

// La clase MainActivity implementa View.OnClickListener para manejar el evento del botón Calcular
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*
     * Alumno: Jaime Moro Lopez
     * Tarea: UT03.3. Gestión de cursos.
     */

    // Declaración de objetos de la UI
    private RadioGroup rgCursos;
    private CheckBox cbDescuentoOro, cbDescuentoPlata;
    private Button btnCalcular;

    // Constantes para los porcentajes de descuento
    private static final double DESC_ORO = 0.10; // 10%
    private static final double DESC_PLATA = 0.05; // 5%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inicialización de los elementos de la UI (Binding)
        rgCursos = findViewById(R.id.rg_cursos);
        cbDescuentoOro = findViewById(R.id.cb_descuento_oro);
        cbDescuentoPlata = findViewById(R.id.cb_descuento_plata);
        btnCalcular = findViewById(R.id.btn_calcular);

        // 2. Registro de eventos: El botón calcular apunta a la instancia 'this'
        btnCalcular.setOnClickListener(this);
    }

    // 3. Método Único Manejador de Eventos (Implementado de View.OnClickListener)
    @Override
    public void onClick(View v) {
        // Solo el botón Calcular dispara este evento
        if (v.getId() == R.id.btn_calcular) {
            calcularImporte();
        }
    }

    /**
     * Función principal para calcular el importe final del curso.
     */
    private void calcularImporte() {
        // Variables para almacenar los datos del curso seleccionado
        double precioBase = 0.0;
        String nombreCurso = "";

        // 1. Obtener el ID del RadioButton seleccionado dentro del RadioGroup
        int idSeleccionado = rgCursos.getCheckedRadioButtonId();

        // Control de Curso Seleccionado (Validación)
        if (idSeleccionado == -1) {
            Toast.makeText(this, "Debe seleccionar un curso.", Toast.LENGTH_LONG).show();
            return; // Sale del método si no hay curso seleccionado
        }

        // 2. Obtener la información del RadioButton seleccionado
        RadioButton rbSeleccionado = findViewById(idSeleccionado);

        // El precio base se ha guardado en el atributo 'tag' del RadioButton en el XML.
        try {
            precioBase = Double.parseDouble(rbSeleccionado.getTag().toString());
        } catch (NumberFormatException e) {
            // Manejo de error si el tag no es un número (no debería ocurrir si el XML es correcto)
            Toast.makeText(this, "Error interno: precio del curso no válido.", Toast.LENGTH_LONG).show();
            return;
        }

        // Obtener el nombre del curso (el texto del RadioButton)
        // Se formatea para obtener solo el nombre sin el precio entre paréntesis
        String textoCompleto = rbSeleccionado.getText().toString();
        nombreCurso = textoCompleto.substring(0, textoCompleto.indexOf('(')).trim();

        // 3. Aplicar Descuentos
        double importeFinal = precioBase;
        double descuentoTotal = 0.0;

        // Descuento Socio Oro (10%)
        if (cbDescuentoOro.isChecked()) {
            descuentoTotal += (importeFinal * DESC_ORO);
        }

        // Descuento Socio Plata (5%)
        if (cbDescuentoPlata.isChecked()) {
            descuentoTotal += (importeFinal * DESC_PLATA);
        }

        // Aplicar el descuento total al importe final
        importeFinal = importeFinal - descuentoTotal;

        // 4. Formatear y Mostrar Resultado en Toast
        String mensaje = String.format("%s: Importe %.2f€, Descuento %.2f€ // Total: %.2f€",
                nombreCurso,
                precioBase,
                descuentoTotal,
                importeFinal);

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}