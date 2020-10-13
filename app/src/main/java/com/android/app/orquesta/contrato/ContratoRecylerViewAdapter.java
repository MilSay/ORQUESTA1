package com.android.app.orquesta.contrato;

import android.content.Context;
import android.content.Intent;
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
import com.android.app.orquesta.agrupacionDetail.AgrupacionDetailActivity;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Contrato;
import com.android.app.orquesta.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ContratoRecylerViewAdapter extends RecyclerView.Adapter<ContratoRecylerViewAdapter.ViewHolder> {

    private ArrayList<Contrato> contratoList;

    private ArrayList<User> userList;

    //private ArrayList<User> userList;

    public Context context;


    public String variableDemo;


    private User user;

    public ContratoRecylerViewAdapter(ArrayList<Contrato> contratoList, Context context) {
        this.contratoList = contratoList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_contrato, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contrato contrato = contratoList.get(position);

        holder.tvContratoRazonSocial.setText(contrato.getRazonSocial());
        holder.tvContratoLocal.setText("Local: "+contrato.getLocalDeEvento());
        holder.tvContratoDireccion.setText("Dirección: "+contrato.getDireccionLocal());
        holder.tvContratoEstado.setText("Estado:"+ contrato.getEstadoEvento());
    }



    @Override
    public int getItemCount() {
        return contratoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
      public  TextView tvContratoRazonSocial,tvContratoLocal,tvContratoDireccion,tvContratoEstado;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContratoRazonSocial = itemView.findViewById(R.id.tv_contrato_razonSocial);
            tvContratoLocal = itemView.findViewById(R.id.tv_contrato_local);
            tvContratoDireccion = itemView.findViewById(R.id.tv_contrato_direccion);
            tvContratoEstado = itemView.findViewById(R.id.tv_contrato_estado);



        }


    }

}


