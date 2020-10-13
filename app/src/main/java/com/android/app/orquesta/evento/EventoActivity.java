package com.android.app.orquesta.evento;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.CustomSharedPrefs;
import com.android.app.orquesta.Util.NetworkHelper;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.agrupacion.AgrupacionActivity;
import com.android.app.orquesta.contrato.ContratoActivity;
import com.android.app.orquesta.contrato.ContratoRecylerViewAdapter;
import com.android.app.orquesta.model.Contrato;
import com.android.app.orquesta.model.Evento;
import com.android.app.orquesta.model.Persona;
import com.android.app.orquesta.userLogin.LoginActivity;
import com.android.app.orquesta.userProfile.ProfileActivity;
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
import java.util.List;

public class EventoActivity extends AppCompatActivity
        implements View.OnClickListener {

    // SPONSORED PRODUCTS GRID


    private boolean netwotkOK;
    /*START OF MENU VERIABLE*/
    ImageButton btnSearch;
    EditText etSearch;
    TextView tvNosearch;

    TextView toobarTitle;
    ImageView toobarLogo;
    Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView nvMainNav;

    public ArrayList<Evento> eventoGridList;


    RecyclerView recyclerViewEvento;

    private boolean networkOK;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        /*START OF TOOLBAR*/
        settingToolBar();
        /*END OF TOOLBAR*/

        tvNosearch = findViewById(R.id.tv_no_search);

        recyclerViewEvento = findViewById(R.id.rv_evento_listt);
        listaEventoGrid();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seacrhedtext = etSearch.getText().toString().trim();
                if (!seacrhedtext.equals("")) {
                    String  temp=seacrhedtext.replace(" ", "%20");
                    getSearchEvento(temp);
                }else {listaEventoGrid();}

            }
        });



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
        settingNavDrawer();
    }
    public void settingToolBar() {

        toolbar = findViewById(R.id.toolbar);
        toobarTitle = findViewById(R.id.toolbar_title);
        toobarLogo = findViewById(R.id.toolbar_logo);
        toolbar.setTitle("");
        toobarTitle.setText(this.getString(R.string.app_name));
        toobarLogo.setVisibility(View.VISIBLE);
        toobarTitle.setVisibility(View.GONE);
        setSupportActionBar(toolbar);
        /*END OF TOOLBAR */
        /*START OF TOOLBAR search */
        btnSearch = findViewById(R.id.btn_search);
        etSearch = findViewById(R.id.et_seach_product);
        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if(etSearch.getText().toString().trim().length() > 0){
                        String seacrhedtext = etSearch.getText().toString().trim();
                        if (!seacrhedtext.equals("")) {
                            String  temp=seacrhedtext.replace(" ", "%20");
                            getSearchEvento(temp);

                        }else {listaEventoGrid();}
                    }
                    return true;
                }
                return false;

            }
        });

    }

    private void listaEventoGrid() {

        String JSON_URL = Constants.EVENTO_LIST;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Evento>>() {
                }.getType();
                eventoGridList = new Gson().fromJson(response, listType);
                setingRecylerView();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("responseV", error.toString());
            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
    }

    private void setingRecylerView() {

        EventoRecylerViewAdapter eventoRecylerViewAdapter = new EventoRecylerViewAdapter(eventoGridList, this);

        recyclerViewEvento.setHasFixedSize(false);
        recyclerViewEvento.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewEvento.setNestedScrollingEnabled(false);
        recyclerViewEvento.setAdapter(eventoRecylerViewAdapter);
    }
    private void getSearchEvento(String search){
        //recoger los datos de la webservices
        netwotkOK = NetworkHelper.hasNetworkAccess(this);
        if (netwotkOK) {

            String JSON_URL = Constants.EVENTO_SEARCH + search;
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("responseV", response);
                    Type listType = new TypeToken<List<Evento>>() {
                    }.getType();
                    eventoGridList = new Gson().fromJson(response, listType);
                    if(eventoGridList!=null){

                        setingRecylerView();
                        tvNosearch.setVisibility(View.GONE);
                    }else{
                        tvNosearch.setVisibility(View.VISIBLE);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.getCache().clear();
            queue.add(stringRequest);

        } else Toast.makeText(this, "No se pudo conectar a internet.", Toast.LENGTH_SHORT).show();
    }

    Persona loggedInUser;
    private void settingNavDrawer() {

        /*START OF NAVIGATION DRAWER */
        drawerLayout = findViewById(R.id.dl_navigation_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        loggedInUser = CustomSharedPrefs.getLoggedInUser(EventoActivity.this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv_main_nav);
        View header = navigationView.getHeaderView(0);
        TextView tvName = header.findViewById(R.id.menu_profile_name);
        tvName.setText("Iniciar sesión");
        ImageView ivProfileImage = header.findViewById(R.id.iv_menu_profile_image);
        Picasso.get().load(R.drawable.profile_image).into(ivProfileImage);

        if (loggedInUser.getFoto()!=null) {
            String imageUrl = Constants.PERSONA_IMAGEN_URL + loggedInUser.getFoto();
            Picasso.get().load(imageUrl).into(ivProfileImage);
        }

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loggedInUser == null) {
                    Intent intentLogin = new Intent(EventoActivity.this, LoginActivity.class);
                    intentLogin.putExtra(Constants.LOGIN_PREV_ACTIVITY, Constants.LOGIN_PREV_MAIN_ACTIVITY);
                    startActivity(intentLogin);
                } else {
                    Intent intent = new Intent(EventoActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
            }
        });

        if (loggedInUser != null) tvName.setText(loggedInUser.getNombres() +" "+loggedInUser.getApellidos());

        nvMainNav = findViewById(R.id.nv_main_nav);

        MenuItem loggedOut = nvMainNav.getMenu().findItem(R.id.nav_logout);
        if (loggedInUser == null) loggedOut.setTitle(getString(R.string.string_login));
        else loggedOut.setTitle(getString(R.string.string_logout));

        nvMainNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                   /* case R.id.nav_home:
                        Intent intentMain = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intentMain);
                        drawerLayout.closeDrawers();
                        break;*/

                    case R.id.nav_account:
                        if (loggedInUser == null) {
                            Intent intentLogin = new Intent(EventoActivity.this, LoginActivity.class);
                            startActivity(intentLogin);
                        } else {
                            Intent intent = new Intent(EventoActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }
                        break;

                    case R.id.nav_evento:
                        Intent intentEvento = new Intent(EventoActivity.this, EventoActivity.class);
                        startActivity(intentEvento);
                        break;

                    case R.id.nav_contrato:
                        Intent intentContrato = new Intent(EventoActivity.this, ContratoActivity.class);
                        startActivity(intentContrato);
                        break;

                    case R.id.nav_agrupacion:
                        Intent intentAgrupacion = new Intent(EventoActivity.this, AgrupacionActivity.class);
                        startActivity(intentAgrupacion);
                        break;

                    case R.id.nav_logout:
                        if (loggedInUser != null) {
                            new AlertDialog.Builder(EventoActivity.this)
                                    .setTitle("Cerrar sesión")
                                    .setMessage("¿Quieres cerrar sesión?")
                                    .setIcon(R.drawable.ic_logout)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            CustomSharedPrefs.setProfileUrl(EventoActivity.this, "");
                                            UtilityClass.signOutFB();
                                            UtilityClass.signOutEmail(EventoActivity.this);
                                            Intent intentLogin = new Intent(EventoActivity.this, LoginActivity.class);
                                            startActivity(intentLogin);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null).show();

                        } else {
                            item.setTitle(getString(R.string.string_login));
                            Intent intentLogin = new Intent(EventoActivity.this, LoginActivity.class);
                            startActivity(intentLogin);
                        }
                        break;
                }

                return false;
            }
        });
        /*END OF NAVIGATION DRAWER */

    }



}
