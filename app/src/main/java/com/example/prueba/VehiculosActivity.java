package com.example.prueba;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.example.prueba.adapter.VehiculoAdapter;
import com.example.prueba.model.Vehiculo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VehiculosActivity extends AppCompatActivity {

    RecyclerView rvVehiculos;
    Button btnAgregarVehiculo;
    ArrayList<Vehiculo> listaVehiculos;
    VehiculoAdapter adapter;
    private final String ARCHIVO = "vehiculos.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos);

        rvVehiculos = findViewById(R.id.rvVehiculos);
        btnAgregarVehiculo = findViewById(R.id.btnAgregarVehiculo);

        listaVehiculos = new ArrayList<>();
        adapter = new VehiculoAdapter(listaVehiculos);
        rvVehiculos.setLayoutManager(new LinearLayoutManager(this));
        rvVehiculos.setAdapter(adapter);


        new Thread(() -> {
            cargarVehiculos(); // Lee archivo en segundo plano
            runOnUiThread(() -> adapter.notifyDataSetChanged()); // Refresca UI
        }).start();


        btnAgregarVehiculo.setOnClickListener(v -> {
            // Ejemplo simple: agregar vehículo ficticio
            Vehiculo vNuevo = new Vehiculo("ABC-123", "Toyota Corolla", "Operativo");
            listaVehiculos.add(vNuevo);
            adapter.notifyDataSetChanged();
            guardarVehiculo(vNuevo);
            Toast.makeText(this, "Vehículo agregado", Toast.LENGTH_SHORT).show();
        });
    }

    private void cargarVehiculos() {
        try {
            FileInputStream fis = openFileInput(ARCHIVO);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    listaVehiculos.add(new Vehiculo(partes[0], partes[1], partes[2]));
                }
            }
            reader.close();
            fis.close();
            adapter.notifyDataSetChanged();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarVehiculo(Vehiculo v) {
        new Thread(() -> {
            try {
                FileOutputStream fos = openFileOutput(ARCHIVO, MODE_APPEND);
                String linea = v.placa + "," + v.modelo + "," + v.estado + "\n";
                fos.write(linea.getBytes());
                fos.close();

                // Actualización en el hilo principal (UI)
                runOnUiThread(() ->
                        Toast.makeText(this, "Vehículo guardado en archivo", Toast.LENGTH_SHORT).show()
                );

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
