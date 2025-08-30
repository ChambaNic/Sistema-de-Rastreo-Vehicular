package com.example.prueba.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prueba.R;
import com.example.prueba.model.Ruta;
import java.util.List;

public class RutaAdapter extends RecyclerView.Adapter<RutaAdapter.ViewHolder> {

    private List<Ruta> rutas;

    public RutaAdapter(List<Ruta> rutas){ this.rutas = rutas; }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFecha, tvConductor, tvVehiculo, tvPaquetes;
        public ViewHolder(View itemView){
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvConductor = itemView.findViewById(R.id.tvConductor);
            tvVehiculo = itemView.findViewById(R.id.tvVehiculo);
            tvPaquetes = itemView.findViewById(R.id.tvPaquetes);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Ruta r = rutas.get(position);
        holder.tvFecha.setText("Fecha: " + r.fecha);
        holder.tvConductor.setText("Conductor: " + r.conductor);
        holder.tvVehiculo.setText("Vehículo: " + r.vehiculo);
        holder.tvPaquetes.setText("Paquetes: " + r.numeroPaquetes);
    }

    @Override
    public int getItemCount(){ return rutas.size(); }
}
