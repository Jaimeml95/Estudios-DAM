package com.example.pmdm03_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
     * Alumno: Jaime Moro López
     */

    // Declaración de los objetos de la UI (EditText, Button, TextView)
    private EditText etNombre, etApellidos, etEmail, etTelefono;
    private Button btnMostrar;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Vincula esta actividad con el layout diseñado en XML
        setContentView(R.layout.activity_main);

        // 1. Inicialización de los elementos de la UI (Binding o mapeo de IDs)
        // Usamos los IDs definidos en el último XML
        etNombre = findViewById(R.id.et_nombre);
        etApellidos = findViewById(R.id.et_apellidos);
        etEmail = findViewById(R.id.et_email);
        etTelefono = findViewById(R.id.et_telefono);
        btnMostrar = findViewById(R.id.btn_mostrar);
        tvResultado = findViewById(R.id.tv_resultado);

        // 2. Registro del evento: Captura del evento programándolo usando setOnClickListener
        // Se utiliza una clase anónima para manejar la pulsación del botón
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatosPersonales();
            }
        });
    }

    /**
     * Método que realiza la validación de campos y muestra los datos.
     * Implementa la lógica principal de la Tarea UT03.1.
     */
    private void mostrarDatosPersonales() {
        // Obtener el texto de cada campo, eliminando espacios en blanco innecesarios
        String nombre = etNombre.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();

        // 3. Validación: Comprobar que todos los datos estén rellenos
        if (nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            // Mostrar un Toast indicando que faltan datos por rellenar
            Toast.makeText(this, "Error: Faltan datos por rellenar. Todos los campos son obligatorios.", Toast.LENGTH_LONG).show();

            // Limpiar el TextView de resultados si la validación falla
            tvResultado.setText("");

            return; // Detiene la ejecución si hay campos vacíos
        }

        // 4. Si todos los datos están completos: Construir y mostrar el resultado
        // Se utiliza el carácter de salto de línea (\n) para mostrar cada dato en una nueva línea,
        // replicando el formato estructurado de la imagen de referencia.
        String datos = "Los datos personales son:\n" +
                nombre + "\n" +
                apellidos + "\n" +
                email + "\n" +
                telefono;

        // Mostrar los datos en el TextView (cuyo estilo ya está configurado en el XML: 20sp, azul)
        tvResultado.setText(datos);
    }
}