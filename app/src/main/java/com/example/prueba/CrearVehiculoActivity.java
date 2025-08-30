package com.example.prueba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.OutputStreamWriter;

public class CrearVehiculoActivity extends AppCompatActivity {

    private EditText etPlaca, etModelo, etTipo, etCapacidad, etEstado;
    private Button btnGuardarVehiculo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_vehiculo);

        // Inicializar vistas
        etPlaca = findViewById(R.id.etPlaca);
        etModelo = findViewById(R.id.etModelo);
        etTipo = findViewById(R.id.etTipo);
        etCapacidad = findViewById(R.id.etCapacidad);
        etEstado = findViewById(R.id.etEstado);
        btnGuardarVehiculo = findViewById(R.id.btnGuardarVehiculo);

        // Configurar el listener del botón
        btnGuardarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatosVehiculo();
            }
        });
    }

    private void guardarDatosVehiculo() {
        String placa = etPlaca.getText().toString().trim();
        String modelo = etModelo.getText().toString().trim();
        String tipo = etTipo.getText().toString().trim();
        String capacidad = etCapacidad.getText().toString().trim();
        String estado = etEstado.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (placa.isEmpty() || modelo.isEmpty() || tipo.isEmpty() || capacidad.isEmpty() || estado.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Formato de la línea para el archivo
        String datosVehiculo = String.format("%s, %s, %s, %s, %s\n", placa, modelo, tipo, capacidad, estado);

        try {
            // Abrir o crear el archivo y escribir los datos
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("vehiculos.txt", MODE_APPEND));
            osw.write(datosVehiculo);
            osw.close();

            // Mostrar un mensaje de éxito
            Toast.makeText(this, R.string.msg_guardado_exitoso, Toast.LENGTH_SHORT).show();

            // Limpiar los campos
            etPlaca.setText("");
            etModelo.setText("");
            etTipo.setText("");
            etCapacidad.setText("");
            etEstado.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al guardar el vehículo.", Toast.LENGTH_SHORT).show();
        }
    }
}