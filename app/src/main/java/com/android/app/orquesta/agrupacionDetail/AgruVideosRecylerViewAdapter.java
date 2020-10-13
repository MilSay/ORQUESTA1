package com.android.app.orquesta.agrupacionDetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.CustomSharedPrefs;
import com.android.app.orquesta.Util.NetworkHelper;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.evento.EventoRegistrar;
import com.android.app.orquesta.main.MainActivity;
import com.android.app.orquesta.model.Comentario;
import com.android.app.orquesta.model.Evento;
import com.android.app.orquesta.model.Persona;
import com.android.app.orquesta.model.User;
import com.android.app.orquesta.model.Videos;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AgruVideosRecylerViewAdapter extends RecyclerView.Adapter<AgruVideosRecylerViewAdapter.ViewHolder> {

    private ArrayList<Videos> videosList;

    //private ArrayList<User> userList;

    public Context context;


    public String variableDemo;



    public  String idVideo,idPersona, fecha,coment;
    Persona loggedInUser;
    private boolean networkOK;
    private User user;

    public AgruVideosRecylerViewAdapter(ArrayList<Videos> videosList, Context context) {
        this.videosList = videosList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview_agru_videos_detail, parent, false);
        loggedInUser = CustomSharedPrefs.getLoggedInUser(context);
        idPersona = loggedInUser.getIdPersona();
        fechaActual();
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Videos videos = videosList.get(position);
        holder.wvLink.loadData("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+videos.getLink()+"\" frameborder=\"0\" allowfullscreen></iframe>", "text/html" , "utf-8" );
        holder.tvDescripcion.setText(videos.getDescripcion());
        idVideo = String.valueOf( videos.getIdVideos());

    }



    @Override
    public int getItemCount() {
        return videosList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public  TextView tvDescripcion;
        //public ImageButton enviar;
        public EditText comentario1;
        public WebView wvLink;
        public ViewHolder(View itemView) {
            super(itemView);
            tvDescripcion = itemView.findViewById(R.id.tv_agru_video_descripcion);
            comentario1= itemView.findViewById(R.id.et_video_comentario);

            ///enviar = itemView.findViewById(R.id.btn_video_enviar_cometario);
            /*enviar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Comentario comentario = validateComentario(comentario1.getText().toString());
                    if (comentario != null) {
                        networkOK = NetworkHelper.hasNetworkAccess(context);
                        if (networkOK) {
                            comentario1.setText("");
                        //    registrarComentario(comentario);
                        }  else
                            Toast.makeText(context, "No se pudo conectar a internet.", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
            wvLink = (WebView) itemView.findViewById(R.id.iv_agrup__videos_link);

            wvLink.getSettings().setJavaScriptEnabled(true);
            wvLink.setWebChromeClient(new WebChromeClient(){

            } );

        }

    }

   /* private void registrarComentario(final Comentario comentario) {
        String JSON_URL = Constants.REGISTAR_COMENTARIO;
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Comentario enviado", Toast.LENGTH_SHORT).show();

                Log.d("Status:-", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responseV", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("idPersona",comentario.getIdPersona());
                params.put("Comentario", comentario.getComentario());
                params.put("FechaRegistro",fecha);
                params.put("idVideos", comentario.getIdVideos() );

                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);
    }*/

    private Comentario validateComentario(String comt) {
        String idVideos = idVideo;
        String idPerson = idPersona;
        Comentario come = new Comentario();
      //  come.setIdVideos(idVideos);
        come.setComentario(comt);

        come.setIdPersona(idPerson);
            return come;

    }

    private void fechaActual(){
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");//yyyy/MM/dd HH:mm:ss dd/MM/yyyy
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        fecha = thisDate;
    }

}


