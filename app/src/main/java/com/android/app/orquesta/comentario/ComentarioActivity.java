package com.android.app.orquesta.comentario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.CustomSharedPrefs;
import com.android.app.orquesta.Util.NetworkHelper;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Comentario;
import com.android.app.orquesta.model.Persona;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComentarioActivity extends AppCompatActivity
        implements View.OnClickListener {

    // SPONSORED PRODUCTS GRID

    private boolean netwotkOK;
    RecyclerView  search_bar;
    /*START OF MENU VERIABLE*/
    Toolbar toolbar;
    /*END OF MENU VERIABLE*/
    private Agrupacion recAgrupacion;
    public ArrayList<Comentario> comentarioGridList;
    public  TextView tvAgrupacionRazonSocial,tvNosearch;
    public  String idAgrupacion,idPersona, fecha,coment;
    Persona loggedInUser;
    RecyclerView recyclerViewComentario;

    public ImageButton enviar;
    public EditText etComentario;

    private boolean networkOK;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        search_bar = findViewById(R.id.search_bar);
        settingToolBar();
        /*END OF TOOLBAR*/
        tvNosearch = findViewById(R.id.tv_no_search);
        recAgrupacion = UtilityClass.jsonToAgrupacion(getIntent().getStringExtra(Constants.AGRUPACION_DETAIL_INTENT));
        tvAgrupacionRazonSocial = findViewById(R.id.titulo);
        tvAgrupacionRazonSocial.setText(recAgrupacion.getRazonSocial());
        idAgrupacion= String.valueOf(recAgrupacion.getIdAgrupacion());
        recyclerViewComentario = findViewById(R.id.rv_comentario_listt);
        listaAgrupacionGrid();
        //  getGeneroMusical();

        loggedInUser = CustomSharedPrefs.getLoggedInUser(getApplication());
        idPersona = loggedInUser.getIdPersona();
        fechaActual();

        etComentario= findViewById(R.id.et_video_comentario);
        enviar = findViewById(R.id.btn_enviar_cometario);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comentario comentario = validateComentario(etComentario.getText().toString());
                if (comentario != null) {
                    networkOK = NetworkHelper.hasNetworkAccess(getApplication());
                    if (networkOK) {
                        etComentario.setText("");
                        registrarComentario(comentario);
                    }  else
                        Toast.makeText(getApplication(), "No se pudo conectar a internet.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void listaAgrupacionGrid() {

            String JSON_URL = Constants.LISTART_COMENTARIO + idAgrupacion;
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("responseV", response);
                    Type listType = new TypeToken<List<Comentario>>() {
                    }.getType();
                    comentarioGridList = new Gson().fromJson(response, listType);
                    if(comentarioGridList== null){
                        tvNosearch.setVisibility(View.VISIBLE);
                        recyclerViewComentario.setVisibility(View.GONE);
                    }else {
                        tvNosearch.setVisibility(View.GONE);
                        recyclerViewComentario.setVisibility(View.VISIBLE);
                    }
                    setingRecylerView();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.getCache().clear();
            queue.add(stringRequest);
    }

    private void setingRecylerView() {

        ComentarioRecylerViewAdapter agrupacionRecylerViewAdapter = new ComentarioRecylerViewAdapter(comentarioGridList, this);

        recyclerViewComentario.setHasFixedSize(false);
        recyclerViewComentario.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewComentario.setNestedScrollingEnabled(false);
        recyclerViewComentario.setAdapter(agrupacionRecylerViewAdapter);
    }


    private void registrarComentario(final Comentario comentario) {
        String JSON_URL = Constants.REGISTAR_COMENTARIO;
        RequestQueue queue = Volley.newRequestQueue(getApplication());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(getApplication(), "Comentario enviado", Toast.LENGTH_SHORT).show();
                listaAgrupacionGrid();
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
                  params.put("idAgrupacion", comentario.getIdAgrupacion() );

                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);
    }

    private Comentario validateComentario(String comt) {
        String agrupacio = idAgrupacion;
        String idPerson = idPersona;
        Comentario come = new Comentario();
        come.setIdAgrupacion(agrupacio);
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

    @Override
    public void onClick(View v) {
       //switch (v.getId()) {
           // case R.id.btn_product_detail_add_cart:


       // }
    }

    @Override
    protected void onResume() {
        super.onResume();
       // settingNavDrawer();
    }

    public void settingToolBar() {

        TextView toobarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toobarTitle.setText(this.getString(R.string.title_comentario));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*END OF TOOLBAR */
        /*START OF TOOLBAR search */

    }





}
