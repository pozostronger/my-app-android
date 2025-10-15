package com.devst.app;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

//  EVENTO EXPLÍCITO 2: MainActivity → ConfigActivity (pantalla de ajustes internos)
public class ConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Switch swNotificaciones = findViewById(R.id.swNotificaciones);

        // Ejemplo: activar o desactivar notificaciones
        swNotificaciones.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "Notificaciones activadas", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notificaciones desactivadas", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
