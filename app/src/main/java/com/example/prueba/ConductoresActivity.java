package com.example.prueba;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.example.prueba.adapter.ConductorAdapter;
import com.example.prueba.model.Conductor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ConductoresActivity extends AppCompatActivity {

    RecyclerView rvConductores;
    Button btnAgregarConductor;
    ArrayList<Conductor> listaConductores;
    ConductorAdapter adapter;
    private final String ARCHIVO = "conductores.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductores);

        rvConductores = findViewById(R.id.rvConductores);
        btnAgregarConductor = findViewById(R.id.btnAgregarConductor);

        listaConductores = new ArrayList<>();
        adapter = new ConductorAdapter(listaConductores);
        rvConductores.setLayoutManager(new LinearLayoutManager(this));
        rvConductores.setAdapter(adapter);

        cargarConductores();

        btnAgregarConductor.setOnClickListener(v -> {
            Conductor cNuevo = new Conductor("Juan Perez","LIC123","0998765432","juan@mail.com");
            listaConductores.add(cNuevo);
            adapter.notifyDataSetChanged();
            guardarConductor(cNuevo);
            Toast.makeText(this, "Conductor agregado", Toast.LENGTH_SHORT).show();
        });
    }

    private void cargarConductores(){
        try {
            FileInputStream fis = openFileInput(ARCHIVO);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while((linea = reader.readLine()) != null){
                String[] partes = linea.split(",");
                if(partes.length == 4){
                    listaConductores.add(new Conductor(partes[0], partes[1], partes[2], partes[3]));
                }
            }
            reader.close(); fis.close();
            adapter.notifyDataSetChanged();
        } catch (IOException e){ e.printStackTrace(); }
    }

    private void guardarConductor(Conductor c){
        try {
            FileOutputStream fos = openFileOutput(ARCHIVO, MODE_APPEND);
            String linea = c.nombre + "," + c.licencia + "," + c.telefono + "," + c.correo + "\n";
            fos.write(linea.getBytes());
            fos.close();
        } catch (IOException e){ e.printStackTrace(); }
    }
}

