package com.android.app.orquesta.agrupacion;

import android.content.Context;
import com.android.app.orquesta.R;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.agrupacionDetail.AgrupacionDetailActivity;
import com.android.app.orquesta.comentario.ComentarioActivity;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Calificacion;
import com.android.app.orquesta.model.User;
import com.android.app.orquesta.userProfile.ProfileActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.like.OnLikeListener;
import com.squareup.picasso.Picasso;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class AgrupacionRecylerViewAdapter  extends RecyclerView.Adapter<AgrupacionRecylerViewAdapter.ViewHolder>  {

    private ArrayList<Agrupacion> agrupaciontList;

    private ArrayList<User> userList;

    //private ArrayList<User> userList;

    public Context context;





    private User user;

    public AgrupacionRecylerViewAdapter(ArrayList<Agrupacion> agrupaciontList, Context context) {
        this.agrupaciontList = agrupaciontList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_agrupacion, parent, false);





        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Agrupacion agrupacion = agrupaciontList.get(position);

        holder.tvAgrupacionRazonSocial.setText(agrupacion.getRazonSocial());
        String imageUrl = Constants.AGRUPACION_IMAGEN_URL + agrupacion.getFoto();
        Picasso.get().load(imageUrl).into(holder.ivaAgrupacionImage);
        holder.tvAgrupacionGeneroMusical.setText(agrupacion.getGeneroMusical());
        holder.tvAgrupacionHistoria.setText(agrupacion.getHistoria());
        holder.ratingBar.setRating(agrupacion.getCalificacion());
    }



    @Override
    public int getItemCount() {
        return agrupaciontList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public ImageView ivaAgrupacionImage;
        public  TextView tvAgrupacionRazonSocial;
        public  TextView tvAgrupacionGeneroMusical;
        public  TextView tvAgrupacionHistoria;
        public RatingBar ratingBar;
        public Button comentario;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ratingBar = (RatingBar) itemView.findViewById(R.id.totalRatingBar);
            ivaAgrupacionImage = itemView.findViewById(R.id.iv_agrupacion_image);
            tvAgrupacionRazonSocial = itemView.findViewById(R.id.tv_agrupacion_razonSocial);
            tvAgrupacionGeneroMusical = itemView.findViewById(R.id.tv_agrupacion_generoMusical);
            tvAgrupacionHistoria = itemView.findViewById(R.id.tv_agrupacion_historia);
            comentario = itemView.findViewById(R.id.tv_comentario);
            UtilityClass.text1ViewScaleIconLeft(context, itemView.findViewById(R.id.tv_comentario), R.drawable.ic_comentario);
            comentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Agrupacion agrupacion = agrupaciontList.get(position);
                    Intent comentario = new Intent(context, ComentarioActivity.class);
                    comentario.putExtra(Constants.AGRUPACION_DETAIL_INTENT, UtilityClass.agrupacionToJson(agrupacion));
                    context.startActivity(comentario);
                }
            });
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Agrupacion agrupacion = agrupaciontList.get(position);

            Intent intent = new Intent(context, AgrupacionDetailActivity.class);
            intent.putExtra(Constants.AGRUPACION_DETAIL_INTENT, UtilityClass.agrupacionToJson(agrupacion));

            context.startActivity(intent);

        }
    }



}


