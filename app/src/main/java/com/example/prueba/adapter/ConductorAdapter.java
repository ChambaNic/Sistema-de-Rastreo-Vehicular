package com.example.prueba.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prueba.R;
import com.example.prueba.model.Conductor;
import java.util.List;

public class ConductorAdapter extends RecyclerView.Adapter<ConductorAdapter.ViewHolder> {

    private List<Conductor> conductores;

    public ConductorAdapter(List<Conductor> conductores){ this.conductores = conductores; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvLicencia, tvTelefono, tvCorreo;
        public ViewHolder(View itemView){
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvLicencia = itemView.findViewById(R.id.tvLicencia);
            tvTelefono = itemView.findViewById(R.id.tvTelefono);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conductor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Conductor c = conductores.get(position);
        holder.tvNombre.setText("Nombre: " + c.nombre);
        holder.tvLicencia.setText("Licencia: " + c.licencia);
        holder.tvTelefono.setText("Teléfono: " + c.telefono);
        holder.tvCorreo.setText("Correo: " + c.correo);
    }

    @Override
    public int getItemCount(){ return conductores.size(); }
}
