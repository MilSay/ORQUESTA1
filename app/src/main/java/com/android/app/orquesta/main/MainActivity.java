package com.android.app.orquesta.main;

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
import android.view.View;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.app.orquesta.Util.CustomSharedPrefs;
import com.android.app.orquesta.agrupacion.AgrupacionActivity;
import com.android.app.orquesta.agrupacion.AgrupacionRecylerViewAdapter;
import com.android.app.orquesta.contrato.ContratoActivity;
import com.android.app.orquesta.evento.EventoActivity;
import com.android.app.orquesta.evento.EventoRegistrar;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Enumerado;
import com.android.app.orquesta.model.Persona;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import com.android.app.orquesta.userLogin.LoginActivity;
import com.android.app.orquesta.userProfile.ProfileActivity;
import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.Util.NetworkHelper;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private boolean netwotkOK;



    RecyclerView  search_bar;

    ImageButton btnSearch;
    EditText etSearch;
    TextView tvNosearch;

    TextView toobarTitle;
    ImageView toobarLogo;
    Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView nvMainNav;

    ArrayList<String> arrayGenero=new ArrayList<>();
    ArrayList<Enumerado> enumeradoList;
    Spinner spGeneroMusical;
    String selectGeneroMusical;
    public ArrayList<Agrupacion> agrupacionGridList;


    RecyclerView recyclerViewAgrupacion;


    private boolean filtreList = true;

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

        settingNavDrawer();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_bar = findViewById(R.id.search_bar);
        recyclerViewAgrupacion = findViewById(R.id.rv_agrupacion_listt);
        tvNosearch = findViewById(R.id.tv_no_search);
        spGeneroMusical = findViewById(R.id.sp_genero_musical);

        settingToolBar();
        listaAgrupacionGrid();
        getGeneroMusical();
        spGeneroMusical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(adapterView.getItemAtPosition(position).equals("[Selecionar genero musical]")){
                    selectGeneroMusical=adapterView.getItemAtPosition(position).toString();
                    listaAgrupacionGrid();
                }else
                {
                    selectGeneroMusical=adapterView.getItemAtPosition(position).toString();
                        getSearchAgrupacion(selectGeneroMusical);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String seacrhedtext = etSearch.getText().toString().trim();
                if (!seacrhedtext.equals("")) {
                    String  temp=seacrhedtext.replace(" ", "%20");
                    getSearchAgrupacion(temp);
                }else {listaAgrupacionGrid();}

            }
        });

    }


    private void listaAgrupacionGrid() {
        netwotkOK = NetworkHelper.hasNetworkAccess(this);
        if (netwotkOK) {

            String JSON_URL = Constants.AGRUPACION_LIST;
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("responseV", response);
                    Type listType = new TypeToken<List<Agrupacion>>() {
                    }.getType();
                    agrupacionGridList = new Gson().fromJson(response, listType);
                    setingRecylerView();

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

    private void setingRecylerView() {

        AgrupacionRecylerViewAdapter agrupacionRecylerViewAdapter = new AgrupacionRecylerViewAdapter(agrupacionGridList, this);

        recyclerViewAgrupacion.setHasFixedSize(false);
        recyclerViewAgrupacion.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewAgrupacion.setNestedScrollingEnabled(false);
        recyclerViewAgrupacion.setAdapter(agrupacionRecylerViewAdapter);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }




    public void settingToolBar() {

        /*START OF TOOLBAR */
        toolbar = findViewById(R.id.toolbar);
        toobarTitle = findViewById(R.id.toolbar_title);
        toobarLogo = findViewById(R.id.toolbar_logo);
        toolbar.setTitle("");
        toobarTitle.setText(this.getString(R.string.app_name));
        toobarLogo.setVisibility(View.VISIBLE);
        toobarTitle.setVisibility(View.GONE);
        setSupportActionBar(toolbar);

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
                            getSearchAgrupacion(temp);

                        }else {listaAgrupacionGrid();}
                    }
                    return true;
                }
                return false;

            }
        });
        /*END OF TOOLBAR */
        /*START OF TOOLBAR search */


    }

    public void setAdaptadorSpinner(Spinner spinner, ArrayList<String> arrayList){

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    private void getGeneroMusical(){
        //recoger los datos de la webservices
        String JSON_URL = Constants.LISTAR_TIPO_ENUMERADO +this.getString(R.string.tipo_generol_muusical);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<List<Enumerado>>() {
                }.getType();
                enumeradoList = new Gson().fromJson(response, listType);
                arrayGenero = new ArrayList<>();
                for (Enumerado enumerar : enumeradoList) {
                    arrayGenero.add(enumerar.getNombre());
                }
                arrayGenero.add(0,"[Selecionar genero musical]");
                setAdaptadorSpinner(spGeneroMusical,arrayGenero);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
    }

    private void getSearchAgrupacion(String search){
        //recoger los datos de la webservices
        netwotkOK = NetworkHelper.hasNetworkAccess(this);
        if (netwotkOK) {

            String JSON_URL = Constants.AGRUPACION_SEARCH + search;
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("responseV", response);
                    Type listType = new TypeToken<List<Agrupacion>>() {
                    }.getType();

                    agrupacionGridList = new Gson().fromJson(response, listType);

                    if(agrupacionGridList!=null){

                        setingRecylerView();
                        tvNosearch.setVisibility(View.GONE);
                    }else{
                        tvNosearch.setVisibility(View.VISIBLE);

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("responseV", "error");
                }
            });
            queue.getCache().clear();
            queue.add(stringRequest);

        } else Toast.makeText(this, "No se pudo conectar a internet.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        /*START FOR NAVIGATION DRAWER*/
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        /*END FOR NAVIGATION DRAWER*/
        /*CLICK BTON SEARCH*/
     /*   btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String seacrhed = etSearch.getText().toString().trim();
                Toast.makeText(getApplicationContext(), "El texto es :" + seacrhed, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });*/

        return super.onOptionsItemSelected(item);
    }

    Persona loggedInUser;

    private void settingNavDrawer() {

        /*START OF NAVIGATION DRAWER */
        drawerLayout = findViewById(R.id.dl_navigation_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        loggedInUser = CustomSharedPrefs.getLoggedInUser(MainActivity.this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nv_main_nav);
        View header = navigationView.getHeaderView(0);
        TextView tvName = header.findViewById(R.id.menu_profile_name);
        tvName.setText("Iniciar sesión");
        ImageView ivProfileImage = header.findViewById(R.id.iv_menu_profile_image);
        Picasso.get().load(R.drawable.profile_image).into(ivProfileImage);

//        if (loggedInUser.getFoto()!=null) {
//            String imageUrl = Constants.PERSONA_IMAGEN_URL + loggedInUser.getFoto();
//            Picasso.get().load(imageUrl).into(ivProfileImage);
//        }

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (loggedInUser == null) {
                    Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                    intentLogin.putExtra(Constants.LOGIN_PREV_ACTIVITY, Constants.LOGIN_PREV_MAIN_ACTIVITY);
                    startActivity(intentLogin);
                } else {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
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
                            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intentLogin);
                        } else {
                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        }
                        break;

                    case R.id.nav_evento:
                            Intent intentEvento = new Intent(MainActivity.this, EventoActivity.class);
                            startActivity(intentEvento);
                        break;

                    case R.id.nav_contrato:
                        Intent intentContrato = new Intent(MainActivity.this, ContratoActivity.class);
                        startActivity(intentContrato);
                        break;

                    case R.id.nav_agrupacion:
                        Intent intentAgrupacion = new Intent(MainActivity.this, AgrupacionActivity.class);
                        startActivity(intentAgrupacion);
                        break;

                    case R.id.nav_logout:
                        if (loggedInUser != null) {
                            new AlertDialog.Builder(MainActivity.this)
                                    .setTitle("Cerrar sesión")
                                    .setMessage("¿Quieres cerrar sesión?")
                                    .setIcon(R.drawable.ic_logout)
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            CustomSharedPrefs.setProfileUrl(MainActivity.this, "");
                                            UtilityClass.signOutGoogle(mGoogleApiClient);
                                            UtilityClass.signOutFB();
                                            UtilityClass.signOutEmail(MainActivity.this);
                                            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                                            startActivity(intentLogin);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null).show();

                        } else {
                            item.setTitle(getString(R.string.string_login));
                            Intent intentLogin = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intentLogin);
                        }
                        break;
                }

                return false;
            }
        });
        /*END OF NAVIGATION DRAWER */

    }

    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }


}
