package com.android.app.orquesta.agrupacionDetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.model.AgrupacionDetalle;
import com.android.app.orquesta.model.Evento;
import com.android.app.orquesta.model.User;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class AgruPersonaRecylerViewAdapter extends RecyclerView.Adapter<AgruPersonaRecylerViewAdapter.ViewHolder> {

    private ArrayList<AgrupacionDetalle> detallePersonList;

    //private ArrayList<User> userList;

    public Context context;


    public String variableDemo;


    private User user;

    public AgruPersonaRecylerViewAdapter(ArrayList<AgrupacionDetalle> detallePersonList, Context context) {
        this.detallePersonList = detallePersonList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_agru_persona_detail, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AgrupacionDetalle person = detallePersonList.get(position);

        String imageUrl = Constants.PERSONA_IMAGEN_URL + person.getFoto();
        Picasso.get().load(imageUrl).into(holder.ivImage);
        holder.tvNombres.setText(person.getNombres()+" "+ person.getApellidos());
        holder.tvTipoPersona.setText(person.getTipoPersona());
    }



    @Override
    public int getItemCount() {
        return detallePersonList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivImage;
        public  TextView tvNombres,tvTipoPersona;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_agrup__person_image);
            tvNombres = itemView.findViewById(R.id.tv_agru_person_nombres);
            tvTipoPersona = itemView.findViewById(R.id.tv_agru_person_tipo_persona);
        }

    }

}


