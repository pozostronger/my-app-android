package com.devst.app;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EnviarSMSActivity extends AppCompatActivity {

    private EditText editTextPhoneNumber;
    private EditText editTextMessage;
    private TextView textViewStatus;
    private Button buttonSendSMS;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_sms);

        // Inicializar vistas
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextMessage = findViewById(R.id.editTextMessage);
        textViewStatus = findViewById(R.id.textViewStatus);
        buttonSendSMS = findViewById(R.id.buttonSendSMS);
        buttonBack = findViewById(R.id.buttonBack);

        // Configurar el botón para enviar SMS
        buttonSendSMS.setOnClickListener(v -> {
            String phoneNumber = editTextPhoneNumber.getText().toString().trim();
            String message = editTextMessage.getText().toString().trim();

            // Validación de campos vacíos
            if (phoneNumber.isEmpty()) {
                Toast.makeText(EnviarSMSActivity.this, "Por favor ingrese un número de teléfono", Toast.LENGTH_SHORT).show();
                return;
            }
            if (message.isEmpty()) {
                Toast.makeText(EnviarSMSActivity.this, "Por favor ingrese un mensaje", Toast.LENGTH_SHORT).show();
                return;
            }

            // Intentar enviar el SMS
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                textViewStatus.setVisibility(View.VISIBLE);
                textViewStatus.setText("SMS enviado correctamente!");
            } catch (Exception e) {
                textViewStatus.setVisibility(View.VISIBLE);
                textViewStatus.setText("Error al enviar el SMS.");
                e.printStackTrace();
            }
        });

        // Configurar el botón de volver
        buttonBack.setOnClickListener(v -> {
            finish(); // Regresa a la actividad anterior
        });
    }
}
