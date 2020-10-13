package com.android.app.orquesta.evento;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.model.Contrato;
import com.android.app.orquesta.model.Evento;
import com.android.app.orquesta.model.User;

import java.util.ArrayList;


public class EventoRecylerViewAdapter extends RecyclerView.Adapter<EventoRecylerViewAdapter.ViewHolder> {

    private ArrayList<Evento> eventooList;

    //private ArrayList<User> userList;

    public Context context;


    public String variableDemo;


    private User user;

    public EventoRecylerViewAdapter(ArrayList<Evento> eventooList, Context context) {
        this.eventooList = eventooList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_evento, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Evento evento = eventooList.get(position);

        holder.tvLocal.setText(evento.getLocalDeEvento());
        holder.tvDirecion.setText("Dirección: "+evento.getDireccionLocal());

        holder.tvFecha.setText("Fecha: "+UtilityClass.stringFecha(evento.getFecha()));
        holder.tvHora.setText("Hora: "+ evento.getHoraInicio() +" - "+evento.getHoraFin());
        holder.tvTipos.setText("Tipo de evento: "+ evento.getTipoEvento());
        holder.tvEstado.setText("Estado: "+ evento.getEstadoEvento());
    }



    @Override
    public int getItemCount() {
        return eventooList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
      public  TextView tvLocal,tvDirecion,tvFecha,tvHora,tvEstado,tvTipos;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLocal = itemView.findViewById(R.id.tv_evento_local);
            tvDirecion = itemView.findViewById(R.id.tv_evento_direccion);
            tvFecha = itemView.findViewById(R.id.tv_evento_fecha);
            tvHora = itemView.findViewById(R.id.tv_evento_hora);
            tvTipos = itemView.findViewById(R.id.tv_evento_tipos);
            tvEstado = itemView.findViewById(R.id.tv_evento_estado);



        }


    }

}


