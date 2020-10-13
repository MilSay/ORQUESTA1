package com.android.app.orquesta.agrupacionDetail;

import android.content.Context;
import android.content.Intent;
import java.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Evento;
import com.android.app.orquesta.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AgruEventoRecylerViewAdapter extends RecyclerView.Adapter<AgruEventoRecylerViewAdapter.ViewHolder> {

    private ArrayList<Evento> eventoList;

    private ArrayList<User> userList;

    //private ArrayList<User> userList;

    public Context context;


    public String variableDemo;


    private User user;

    public AgruEventoRecylerViewAdapter(ArrayList<Evento> eventoList, Context context) {
        this.eventoList = eventoList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_agru_evento_detail, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Evento evento = eventoList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy");
        String fecha = sdf.format(evento.getFecha());
        holder.tvFecha.setText(fecha);
        holder.tvLocalEvento.setText("Local: "+evento.getLocalDeEvento());
        holder.tvTipoEvento.setText("Tipo de evento: "+evento.getTipoEvento());
        holder.tvHora.setText("Hora: "+evento.getHoraInicio() +" - " + evento.getHoraFin());
    }



    @Override
    public int getItemCount() {
        return eventoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public  TextView tvFecha,tvLocalEvento,tvTipoEvento,tvHora;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tv_agru_evento_fecha);
            tvLocalEvento = itemView.findViewById(R.id.tv_agru_evento_localEvento);
            tvTipoEvento = itemView.findViewById(R.id.tv_agru_evento_tipoEvento);
            tvHora = itemView.findViewById(R.id.tv_agru_evento_hora);


        }

    }

}


