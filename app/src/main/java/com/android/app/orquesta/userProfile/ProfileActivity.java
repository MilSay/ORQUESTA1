package com.android.app.orquesta.userProfile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.Util.CustomSharedPrefs;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Departamento;
import com.android.app.orquesta.model.Distrito;
import com.android.app.orquesta.model.Enumerado;
import com.android.app.orquesta.model.Persona;
import com.android.app.orquesta.model.Provincia;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.model.User;
import com.android.app.orquesta.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity
        implements View.OnClickListener {

    TextView toobarTitle;
    android.support.v7.widget.Toolbar toolbar;
    Button btnMyOrders;
    Button btnMyFavourites;
    private static final String CERO = "0";
    private static final String BARRA = "-";
    TextView tvUserName, tvCargo;

    ArrayList<String> arrayidDepar=new ArrayList<>();
    ArrayList<String> arraynomDepar=new ArrayList<>();

    ArrayList<String> arrayidProvincia=new ArrayList<>();
    ArrayList<String> arraynomProvincia=new ArrayList<>();

    ArrayList<String> arrayidDistrito=new ArrayList<>();
    ArrayList<String> arraynomDistrito=new ArrayList<>();

    ArrayList<String> arrayidEnumerado=new ArrayList<>();
    ArrayList<String> arraynomEnumerado=new ArrayList<>();

    boolean isFieldEmpty = false;
    ImageView ivProfileImage;
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    StringRequest stringRequest;
    Spinner spDepartamento,spProvincia,spDistrito,spGenero;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    Persona loggedInUser;
    EditText etNombres,etApellidos,etDni,etGenero, etCelular, etFechaNacimiento, etEmail,etDireccion,etRol;

    FrameLayout btnCompleteAddress;
    RelativeLayout pbarContainer;
    String cod_Departamento="",cod_Provincia="",cod_Distrito="",cod_Genero="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView toobarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toobarTitle.setText("User");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        pbarContainer = findViewById(R.id.pbar_container);
        pbarContainer.setVisibility(View.GONE);

        ivProfileImage = findViewById(R.id.iv_profile_image);

        mostrarDep();
        mostrarGenero();
        if(CustomSharedPrefs.getLoggedInUser(ProfileActivity.this) != null){

            // END OF ORDER/FAVOURITE BUTTON

            loggedInUser = CustomSharedPrefs.getLoggedInUser(ProfileActivity.this);

            if (loggedInUser.getFoto()!=null) {
                String imageUrl = Constants.PERSONA_IMAGEN_URL + loggedInUser.getFoto();
                Picasso.get().load(imageUrl).into(ivProfileImage);
            }
            tvUserName = findViewById(R.id.tv_user_name);
            tvUserName.setText(loggedInUser.getNombres() + "  " + loggedInUser.getApellidos());
            tvCargo = findViewById(R.id.tv_person_rol);
            tvCargo.setText(loggedInUser.getRol());
            etDni = findViewById(R.id.et_person_dni);
            etFechaNacimiento = findViewById(R.id.et_person_fecha_nacimiento);
            etCelular = findViewById(R.id.et_person_celular);
            etEmail = findViewById(R.id.et_person_email);
            spDepartamento = findViewById(R.id.spDepartamento);
            spProvincia = findViewById(R.id.spProvincia);
            spDistrito = findViewById(R.id.spDistrito);
            spGenero = findViewById(R.id.spGenero);
            etDireccion = findViewById(R.id.et_person_direccion);
           // btnCompleteAddress = findViewById(R.id.btn_complete_address);
           // btnCompleteAddress.setOnClickListener(this);


            etDni.setText(loggedInUser.getDni());
            etFechaNacimiento.setText(loggedInUser.getFechaNacimiento());
            etCelular.setText(loggedInUser.getCelular());
            etEmail.setText(loggedInUser.getEmail());
            //etdepartamento.setText(loggedInUser.getCodigoDepartamento());
            //etProvincia.setText(loggedInUser.getCodigoProvincia());
            //etdistrito.setText(loggedInUser.getCodigoDistrito());
           // etDireccion.setText(loggedInUser.get);


//-----------------------------------------------------------------------------------------------------------------------------------
            spDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    if (position > 0) {

                        mostrarProv(arrayidDepar.get(position));
                        cod_Departamento=arrayidDepar.get(position);
                        // Toast.makeText(getApplicationContext(), " Codigo Departamento "+ cod_Departamento   , Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
//-----------------------------------------------------------------------------------------------------------------------------------
            spProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    mostrarDis(cod_Departamento,arrayidProvincia.get(i));
                    cod_Provincia=arrayidProvincia.get(i);
                    // Toast.makeText(getApplicationContext(), " Codigo Departamento "+ arrayidDepar.get(i) + "codigo Provincia"  +arrayidProvincia.get(i)  , Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    cod_Distrito=arrayidDistrito.get(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    cod_Genero=arrayidEnumerado.get(position);
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });




            Persona loggedInUser = CustomSharedPrefs.getLoggedInUser(this);
            //etNombres.setText(loggedInUser.getNombres());
            //etApellidos.setText(loggedInUser.getApellidos());
        }else{
            Toast.makeText(ProfileActivity.this, "Por favor inicie sesión para ver su perfil", Toast.LENGTH_LONG).show();
        }
    }



    private boolean isEmpty(String value) {
        return (value.length() < 1) ? true : false;
    }

    /*private void validateAddress() {

        firstName = etFirstName.getText().toString().trim();
        lastName = etLastName.getText().toString().trim();
        address1 = etAddress1.getText().toString().trim();
        address2 = etAddress2.getText().toString().trim();
        city = etCity.getText().toString().trim();
        zip = etZip.getText().toString().trim();
        state = etState.getText().toString().trim();
        country = etCountry.getText().toString().trim();

        if (isEmpty(firstName)) {
            isFieldEmpty = true;
            etFirstName.setBackgroundResource(R.drawable.edittext_error);
        } else {
            isFieldEmpty = false;
            etFirstName.setBackgroundResource(R.drawable.edittext_round);
        }
        if (isEmpty(lastName)) {
            isFieldEmpty = true;
            etLastName.setBackgroundResource(R.drawable.edittext_error);
        } else {
            isFieldEmpty = false;
            etLastName.setBackgroundResource(R.drawable.edittext_round);
        }
        if (isEmpty(address1)) {
            isFieldEmpty = true;
            etAddress1.setBackgroundResource(R.drawable.edittext_error);
        } else {
            isFieldEmpty = false;
            etAddress1.setBackgroundResource(R.drawable.edittext_round);
        }
        if (isEmpty(city)) {
            isFieldEmpty = true;
            etCity.setBackgroundResource(R.drawable.edittext_error);
        } else {
            isFieldEmpty = false;
            etCity.setBackgroundResource(R.drawable.edittext_round);
        }
        if (isEmpty(zip)) {
            isFieldEmpty = true;
            etZip.setBackgroundResource(R.drawable.edittext_error);
        } else {
            isFieldEmpty = false;
            etZip.setBackgroundResource(R.drawable.edittext_round);
        }
        if (isEmpty(state)) {
            isFieldEmpty = true;
            etState.setBackgroundResource(R.drawable.edittext_error);
        } else {
            isFieldEmpty = false;
            etState.setBackgroundResource(R.drawable.edittext_round);
        }
        if (isEmpty(country)) {
            isFieldEmpty = true;
            etCountry.setBackgroundResource(R.drawable.edittext_error);
        } else {
            isFieldEmpty = false;
            etCountry.setBackgroundResource(R.drawable.edittext_round);
        }
    }*/


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


          /*  case R.id.btn_complete_address:

                validateAddress();

                if (!isFieldEmpty) {

                    final User user = new User();
                    user.setFirst_name(firstName);
                    user.setLast_name(lastName);
                    final String Address = address1 + address2 + ", City : " + city + ", zip Code : " + zip
                            + ", State : " + state + ", Country :" + country;

                    if (isEmpty(address2)) address2 = "-1";
                    user.setAddress(Address);

                  //  updateAddress();

                }
                break;*/
        }
    }
    ArrayList<Departamento> arrayDep=new ArrayList<>();
    public void mostrarDep() {
        // Petición GET
        arrayDep.clear();
        String JSON_URL = Constants.UBIGEO_DEPARTAMENTO;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayDep.clear();
                arrayProv.clear();
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Departamento>>() {
                }.getType();
                arrayDep = new Gson().fromJson(response, listType);
                for(int i=0;i<arrayDep.size();i++){
                    arrayidDepar.add(arrayDep.get(i).getCod_depa());
                    arraynomDepar.add(arrayDep.get(i).getDepartamento());
                }
                setAdaptadorSpinner(spDepartamento,arraynomDepar);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);

    }
    //--------------------------------------------------------------------------------------------------------------------------
    ArrayList<Provincia> arrayProv=new ArrayList<Provincia>();
    public void mostrarProv( final String cod_depart) {
        // Petición GET
        arrayidProvincia.clear();
        arraynomProvincia.clear();
        String JSON_URL = Constants.UBIGEO_PROVINCIA +cod_depart;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayProv.clear();
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Provincia>>() {
                }.getType();
                arrayProv = new Gson().fromJson(response, listType);

                for(int i=0;i<arrayProv.size();i++){
                    arrayidProvincia.add(arrayProv.get(i).getCod_prov());
                    arraynomProvincia.add(arrayProv.get(i).getProvincia());
                }
                setAdaptadorSpinner(spProvincia,arraynomProvincia);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
        setAdaptadorSpinner(spProvincia,arraynomProvincia);


    }

    //--------------------------------------------------------------------------------------------------------------------------
    ArrayList<Distrito> arrayDis=new ArrayList<>();
    public void mostrarDis( final String cod_depart,final String cod_prov) {
        // Petición GET
        arrayidDistrito.clear();
        arraynomDistrito.clear();
        String JSON_URL = Constants.UBIGEO_DISTRITO +"idDepartamento="+cod_depart+"&idProvincia="+cod_prov;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayDis.clear();
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Distrito>>() {
                }.getType();
                arrayDis = new Gson().fromJson(response, listType);

                for(int i=0;i<arrayDis.size();i++){
                    arrayidDistrito.add(arrayDis.get(i).getCod_dist());
                    arraynomDistrito.add(arrayDis.get(i).getDistrito());
                }
                setAdaptadorSpinner(spDistrito,arraynomDistrito);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);

    }

    ArrayList<Enumerado> arrayEnum=new ArrayList<>();
    public void mostrarGenero( ) {
        // Petición GET
        String JSON_URL = Constants.ENUMERADO_TIPO +"GENERO";
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                arrayEnum.clear();
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Enumerado>>() {
                }.getType();
                arrayEnum = new Gson().fromJson(response, listType);

                for(int i=0;i<arrayEnum.size();i++){
                    arrayidEnumerado.add(String.valueOf(arrayEnum.get(i).getIdEnumerado()));
                    arraynomEnumerado.add(arrayEnum.get(i).getNombre());
                }
                setAdaptadorSpinner(spGenero,arraynomEnumerado);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);

    }



    public void setAdaptadorSpinner(Spinner spinner,ArrayList<String> arrayList){

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(ProfileActivity.this,android.R.layout.simple_spinner_dropdown_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void ObtenerFechaEvento(View view) {
        obtenerFecha(etFechaNacimiento);
    }
    private void obtenerFecha(final TextView txt){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                txt.setText(year + BARRA + mesFormateado + BARRA + diaFormateado);


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();

    }
}
