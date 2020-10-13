package com.android.app.orquesta.agrupacionDetail;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.CustomSharedPrefs;
import com.android.app.orquesta.Util.NetworkHelper;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Calificacion;
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
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgrupacionDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    // SPONSORED PRODUCTS GRID

    /* tab*/
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    /* end tab*/
    private boolean networkOK;

    /*START OF MENU VERIABLE*/
    Toolbar toolbar;
    Button btnAddCart;
    /*END OF MENU VERIABLE*/

    public  boolean response1;
    public RatingBar ratingBar;
    private Agrupacion recAgrupacion;
    Persona loggedInUser;
    public ImageView ivaAgrupacionImage;
    public  TextView tvAgrupacionRazonSocial,tvAgrupacionGeneroMusical,tvAgrupacionHistoria;


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_agrupacion);

        /*START OF TOOLBAR*/
        TextView toobarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toobarTitle.setText(this.getString(R.string.title_detalle_agrupacion));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*END OF TOOLBAR*/
        recAgrupacion = UtilityClass.jsonToAgrupacion(getIntent().getStringExtra(Constants.AGRUPACION_DETAIL_INTENT));
        loggedInUser = CustomSharedPrefs.getLoggedInUser(getApplication());


        ivaAgrupacionImage = findViewById(R.id.cabecera);
        tvAgrupacionRazonSocial = findViewById(R.id.titulo);
        tvAgrupacionGeneroMusical = findViewById(R.id.sub_titulo_genero);
        tvAgrupacionRazonSocial.setText(recAgrupacion.getRazonSocial());
        tvAgrupacionGeneroMusical.setText("Genero: "+recAgrupacion.getGeneroMusical());


        networkOK = NetworkHelper.hasNetworkAccess(this);
        if (networkOK) {

            String imageUrl = Constants.AGRUPACION_IMAGEN_URL + recAgrupacion.getFoto();
            Picasso.get().load(imageUrl).into(ivaAgrupacionImage);

        } else {
            Toast.makeText(this, "No se pudo conectar a internet.", Toast.LENGTH_SHORT).show();
        }


        /* tab*/
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.view_page);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_id);
        tabLayout.setupWithViewPager(mViewPager);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        mostrarCalificacion(recAgrupacion.getIdAgrupacion(),loggedInUser.getIdPersona());
        addListenerOnRatingBar();

    }

    public void mostrarCalificacion( final int idagrupacion,final String idpersona) {

        String JSON_URL = Constants.OBTENER_CALIFICACION + idagrupacion +"&idPersona="+ idpersona;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Calificacion>>() {
                }.getType();
                ArrayList<Calificacion> calificacionGridList = new Gson().fromJson(response, listType);
                if(calificacionGridList.size() > 0){
                    for (Calificacion  obte : calificacionGridList){
                        ratingBar.setRating(Float.parseFloat(obte.getCalificacion()));
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
    }


    public void addListenerOnRatingBar() {
        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                obtenerCalificacion(recAgrupacion.getIdAgrupacion(), loggedInUser.getIdPersona(),String.valueOf(rating));
            }
        });

    }

    private void registrarCalificacion(final int idagrupacion,final String idpersona,final String calificacion) {
        String JSON_URL = Constants.REGISTAR_CALIFICACION;
        RequestQueue queue = Volley.newRequestQueue(getApplication());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText(getApplication(), "Calificación enviado", Toast.LENGTH_SHORT).show();
                Log.d("Status:-", response);
                if(response.equals("200")){
                    CalificacionDialog exampleDialog = new CalificacionDialog();
                    exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }else {
                    Toast.makeText(getApplication(), "algo salio mal", Toast.LENGTH_SHORT).show();
                }
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
                params.put("idAgrupacion",String.valueOf(idagrupacion) );
                params.put("idPersona", idpersona);
                params.put("calificacion",calificacion);

                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);
    }


    private void obtenerCalificacion( final int idagrupacion,final String idpersona ,final String calificacion) {

        String JSON_URL = Constants.OBTENER_CALIFICACION + idagrupacion +"&idPersona="+ idpersona;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Calificacion>>() {
                }.getType();
                ArrayList<Calificacion> calificacionGridList = new Gson().fromJson(response, listType);
                if(calificacionGridList.size() > 0){
                    for (Calificacion  obte : calificacionGridList){
                        updateCalificacion(obte.getIdCalificacion(),calificacion);
                        //  Toast.makeText(getApplication(), "Calificación enviado" + obte.getIdCalificaficion(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    registrarCalificacion(idagrupacion, idpersona,calificacion);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
    }


    private void updateCalificacion(final String idcalificacion,final String calificacion) {
        String JSON_URL = Constants.UPDATE_CALIFICACION ;
        RequestQueue queue = Volley.newRequestQueue(getApplication());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Toast.makeText(getApplication(), "Calificación enviado", Toast.LENGTH_SHORT).show();

                Log.d("Status:-ss", response);
                if(response.equals("200") ){
                    // CalificacionDialog exampleDialog = new CalificacionDialog();
                    //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                }else {
                    Toast.makeText(getApplication(), "algo salio mal", Toast.LENGTH_SHORT).show();
                }
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
                params.put("idCalificacion",idcalificacion );
                params.put("calificacion",calificacion);

                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);
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
        invalidateOptionsMenu();
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = Tab1Presentaciones.newInstancia(recAgrupacion.getIdAgrupacion());
                    break;
                case 1:
                    fragment = Tab2Galeria.newInstancia(recAgrupacion.getIdAgrupacion());
                    break;
                case 2:
                    fragment = Tab3MarcoMusical.newInstancia(recAgrupacion.getIdAgrupacion());
                    break;
                case 3:
                    fragment = Tab4Contratar.newInstancia(recAgrupacion.getIdAgrupacion());
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Presentaciones";
                case 1:
                    return "Galeria";
                case 2:
                    return "Marco musical";
                case 3:
                    return "Contactar";
            }
            return null;
        }
    }

}