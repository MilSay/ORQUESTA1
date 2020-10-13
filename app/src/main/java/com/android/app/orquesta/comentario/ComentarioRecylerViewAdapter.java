package com.android.app.orquesta.comentario;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.model.Comentario;
import com.android.app.orquesta.model.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class ComentarioRecylerViewAdapter extends RecyclerView.Adapter<ComentarioRecylerViewAdapter.ViewHolder>  {

    private ArrayList<Comentario> comentarioList;

    private ArrayList<User> userList;

    //private ArrayList<User> userList;

    public Context context;





    private User user;

    public ComentarioRecylerViewAdapter(ArrayList<Comentario> comentarioList, Context context) {
        this.comentarioList = comentarioList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_comentario, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comentario comentario = comentarioList.get(position);
        holder.tvComentario.setText(comentario.getComentario());
        String imageUrl = Constants.PERSONA_IMAGEN_URL + comentario.getFoto();
        Picasso.get().load(imageUrl).into(holder.ivaAgrupacionImage);
        holder.tvComentarioNombre.setText(comentario.getNombres() +" " +comentario.getApellidos());
    }



    @Override
    public int getItemCount() {
        return comentarioList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

      public  TextView tvComentario, tvComentarioNombre;
        public CircleImageView ivaAgrupacionImage;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivaAgrupacionImage= itemView.findViewById(R.id.icon_user);
            tvComentario= itemView.findViewById(R.id.tv_comentarios);
            tvComentarioNombre=itemView.findViewById(R.id.tv_comentario_nombres);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
        }
    }

}


