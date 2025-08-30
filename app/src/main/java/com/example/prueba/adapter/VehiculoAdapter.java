package com.example.prueba.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prueba.R;
import com.example.prueba.model.Vehiculo;
import java.util.List;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    private List<Vehiculo> vehiculos;

    public VehiculoAdapter(List<Vehiculo> vehiculos){ this.vehiculos = vehiculos; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlaca, tvModelo, tvEstado;
        public ViewHolder(View itemView){
            super(itemView);
            tvPlaca = itemView.findViewById(R.id.tvPlaca);
            tvModelo = itemView.findViewById(R.id.tvModelo);
            tvEstado = itemView.findViewById(R.id.tvEstado);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehiculo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Vehiculo v = vehiculos.get(position);
        holder.tvPlaca.setText("Placa: " + v.placa);
        holder.tvModelo.setText("Modelo: " + v.modelo);
        holder.tvEstado.setText("Estado: " + v.estado);
    }

    @Override
    public int getItemCount(){ return vehiculos.size(); }
}
