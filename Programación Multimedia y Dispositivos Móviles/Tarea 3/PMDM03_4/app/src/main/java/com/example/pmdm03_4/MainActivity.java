package com.example.pmdm03_4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

// La clase MainActivity actúa como manejadora única para el Button y el RadioGroup.
public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    /*
     * Alumno: Jaime Moro López
     * Tarea: UT03.4. Mostrar/Ocultar imágenes.
     */

    // Declaración de objetos de la UI
    private ImageView ivImagenPrincipal;
    private Button btnCambiarImagen;
    private CheckBox cbDeshabilitarBoton;
    private RadioGroup rgVisibilidad;

    // Control para saber qué imagen se está mostrando (Punto 2)
    private boolean isFirstImage = true;

    // IDs de los recursos de imagen (DEBEN EXISTIR en res/drawable)
    private int imagen1Id;
    private int imagen2Id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- 1. Inicialización de los elementos de la UI (Binding) ---
        ivImagenPrincipal = findViewById(R.id.iv_imagen_principal);
        btnCambiarImagen = findViewById(R.id.btn_cambiar_imagen);
        cbDeshabilitarBoton = findViewById(R.id.cb_deshabilitar_boton);
        rgVisibilidad = findViewById(R.id.rg_visibilidad);

        // --- Configuración Inicial de Imágenes ---
        // Asignamos dos recursos para poder alternar (Punto 2).
        // R.drawable.android_logo debe ser reemplazado por el nombre de tu archivo.
        imagen1Id = R.drawable.android_logo;
        imagen2Id = R.drawable.android_logo2;
        ivImagenPrincipal.setImageResource(imagen1Id);

        // --- 2. Registro de Eventos ---

        // a) Evento del Botón "Cambiar imagen" (Punto 2)
        btnCambiarImagen.setOnClickListener(this);

        // b) Evento del CheckBox (Punto 3: Habilita o deshabilita el botón)
        // Se usa una lambda para el OnCheckedChangeListener del CheckBox, habilitando/deshabilitando el botón.
        cbDeshabilitarBoton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Si 'isChecked' es TRUE (marcado), el resultado de '!isChecked' es FALSE,
            // deshabilitando el botón.
            // Si 'isChecked' es FALSE (desmarcado), el resultado de '!isChecked' es TRUE,
            // habilitando el botón.
            btnCambiarImagen.setEnabled(!isChecked);
            Toast.makeText(this, isChecked ? "Botón deshabilitado" : "Botón habilitado", Toast.LENGTH_SHORT).show();
        });

        // c) Evento del RadioGroup (Punto 4: Mostrar u Ocultar)
        rgVisibilidad.setOnCheckedChangeListener(this);
    }

    // --- Implementación de OnClickListener (Maneja el botón 'Cambiar Imagen') ---
    // Punto 2: Implementa la funcionalidad del botón.
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_cambiar_imagen) {
            cambiarImagen();
        }
    }

    /**
     * Alterna entre las dos imágenes definidas (imagen1Id y imagen2Id).
     */
    private void cambiarImagen() {
        if (isFirstImage) {
            ivImagenPrincipal.setImageResource(imagen2Id);
            Toast.makeText(this, "Mostrando Imagen 2", Toast.LENGTH_SHORT).show();
        } else {
            ivImagenPrincipal.setImageResource(imagen1Id);
            Toast.makeText(this, "Mostrando Imagen 1", Toast.LENGTH_SHORT).show();
        }
        isFirstImage = !isFirstImage; // Invierte el estado
    }

    // --- Implementación de OnCheckedChangeListener (Maneja el RadioGroup) ---
    // Punto 4: Permite mostrar u ocultar la imagen.
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group.getId() == R.id.rg_visibilidad) {
            if (checkedId == R.id.rb_mostrar) {
                // Muestra la imagen (VISIBLE)
                ivImagenPrincipal.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Imagen Mostrada", Toast.LENGTH_SHORT).show();
            } else if (checkedId == R.id.rb_ocultar) {
                // Oculta la imagen (GONE para que no ocupe espacio)
                ivImagenPrincipal.setVisibility(View.GONE);
                Toast.makeText(this, "Imagen Ocultada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}