package com.android.app.orquesta.evento;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.model.Enumerado;
import com.android.app.orquesta.model.Evento;
import com.android.app.orquesta.Util.NetworkHelper;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.widget.TimePicker;

public class EventoRegistrar extends AppCompatActivity
        implements View.OnClickListener {

    // SPONSORED PRODUCTS GRID



    /*START OF MENU VERIABLE*/
    Toolbar toolbar;
    Button btnAddCart;
    private static final String CERO = "0";
    private static final String BARRA = "-";
    private static final String DOS_PUNTOS = ":";
    /*END OF MENU VERIABLE*/
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);

    Button btnRegistrar;

    EditText etFecha, etLocalEvento, etDireccionLocal,etHoraInicio, etHoraFin;
    TextView tvValidationFecha, tvValidationLocalEvento, tvValidationDireccionLocal, tvValidationTipoEventoId, tvValidationTipoEntradaId,tvValidationEstadoEventoId,tvValidationHoraInicio,tvValidationHoraFin;
    Spinner spTipoEvento,spTipoEntrada,spEstadoEvento;
    ArrayList<Enumerado> enumeradoList;

    ArrayList<String> arrayidTipoEvento=new ArrayList<>();
    ArrayList<String> arraynomtipoEvento=new ArrayList<>();

    ArrayList<String> arrayidTipoEntrada=new ArrayList<>();
    ArrayList<String> arraynomtipoEntrada=new ArrayList<>();

    ArrayList<String> arrayidEstado=new ArrayList<>();
    ArrayList<String> arraynomEstado=new ArrayList<>();

    String cod_TipoEvento,cod_TipoEntrada,cod_Estado;
    String selectTipoEvento,selectTipoEntrada,selectEstado;


  ;

    private boolean networkOK;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_resgistrar);

        /*START OF TOOLBAR*/
        TextView toobarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toobarTitle.setText(this.getString(R.string.title_evento));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*END OF TOOLBAR*/

        etFecha = findViewById(R.id.et_evento_fecha);
        etLocalEvento = findViewById(R.id.et_evento_local_evento);
        etDireccionLocal = findViewById(R.id.et_evento_direccion_evento);

        spTipoEvento = findViewById(R.id.sp_evento_tipo_evento);
        spTipoEntrada = findViewById(R.id.sp_evento_entrada);
        spEstadoEvento = findViewById(R.id.sp_evento_estado);

        etHoraInicio = findViewById(R.id.et_evento_hora_inicio);
        etHoraFin = findViewById(R.id.et_evento_hora_fin);

        tvValidationFecha = findViewById(R.id.tv_evento_fecha);
        tvValidationLocalEvento = findViewById(R.id.tv_evento_local_evento);
        tvValidationDireccionLocal = findViewById(R.id.tv_evento_direccion_evento);
        tvValidationTipoEventoId = findViewById(R.id.tv_evento_tipo_evento);

        tvValidationTipoEntradaId = findViewById(R.id.tv_evento_entrada);
        tvValidationEstadoEventoId = findViewById(R.id.tv_evento_estado);
        tvValidationHoraInicio = findViewById(R.id.tv_evento_hora_inicio);
        tvValidationHoraFin = findViewById(R.id.tv_evento_hora_fin);

        btnRegistrar = findViewById(R.id.btn_evento_registrar);
        btnRegistrar.setOnClickListener(this);

        getTipoEvento();
        getTipoEntrada();
        getEstadoEvento();
        spTipoEvento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(adapterView.getItemAtPosition(position).equals("[Selecionar]")){
                    selectTipoEvento=adapterView.getItemAtPosition(position).toString();
                }else
                {
                    selectTipoEvento=adapterView.getItemAtPosition(position).toString();
                    cod_TipoEvento=arrayidTipoEvento.get(position -1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spTipoEntrada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(adapterView.getItemAtPosition(position).equals("[Selecionar]")){
                    selectTipoEntrada=adapterView.getItemAtPosition(position).toString();
                }else
                {
                    selectTipoEntrada=adapterView.getItemAtPosition(position).toString();
                    cod_TipoEntrada=arrayidTipoEntrada.get(position -1);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spEstadoEvento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              if(parent.getItemAtPosition(position).equals("[Selecionar]")){
                  selectEstado=parent.getItemAtPosition(position).toString();
              }else
              {
                  selectEstado=parent.getItemAtPosition(position).toString();
                  cod_Estado=arrayidEstado.get(position -1);
              }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.btn_evento_registrar:
               Evento evento = validateUser();
               if (evento != null) {
                   networkOK = NetworkHelper.hasNetworkAccess(EventoRegistrar.this);
                   if (networkOK) userResistration(evento);
                   else
                       Toast.makeText(EventoRegistrar.this, "No se pudo conectar a internet.", Toast.LENGTH_SHORT).show();
                     }
               break;
        }
    }

    private void userResistration(final Evento evento) {
        String JSON_URL = Constants.REGISTAR_EVENTO;
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EventoRegistrar.this,"Guardado con Exito ", Toast.LENGTH_SHORT).show();
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
                params.put("Fecha",evento.getFechaString());
                params.put("LocalDeEvento", evento.getLocalDeEvento());
                params.put("DireccionLocal", evento.getDireccionLocal());
                params.put("TipoEventoId", evento.getTipoEvento() );
                params.put("TipoEntradaId",evento.getTipoEntrada() );
                params.put("EstadoEventoId",evento.getEstadoEvento() );
                params.put("HoraInicio", evento.getHoraInicio());
                params.put("HoraFin", evento.getHoraFin());

                return params;
            }
        };

        queue.getCache().clear();
        queue.add(stringRequest);
    }

    private Evento validateUser() {

        String fecha = etFecha.getText().toString().trim();
        String localEvento = etLocalEvento.getText().toString().trim();
        String direccionLocal = etDireccionLocal.getText().toString().trim();
        String horaInicio = etHoraInicio.getText().toString().trim();
        String horaFin = etHoraFin.getText().toString().trim();
        String tipoEvento= "", estadoEvento="",tipoEntrada= "";

        boolean isFieldEmpty = false;

        if (isEmpty(fecha)) {
            tvValidationFecha.setVisibility(View.VISIBLE);
            tvValidationFecha.setText("La fecha no puede estar vacío.");
        } else {
            tvValidationFecha.setVisibility(View.GONE);
            tvValidationFecha.setText("");
        }
        if (isEmpty(localEvento)) {
            isFieldEmpty = true;
            tvValidationLocalEvento.setVisibility(View.VISIBLE);
            tvValidationLocalEvento.setText("El local no puede estar vacío.");
        } else {
            tvValidationLocalEvento.setVisibility(View.GONE);
            tvValidationLocalEvento.setText("");
        }

        if (isEmpty(direccionLocal)) {
            isFieldEmpty = true;
            tvValidationDireccionLocal.setVisibility(View.VISIBLE);
            tvValidationDireccionLocal.setText("La dirección no puede estar vacío.");
        } else {
            tvValidationDireccionLocal.setVisibility(View.GONE);
            tvValidationDireccionLocal.setText("");
        }

        if (selectTipoEvento.equals("[Selecionar]")) {
            isFieldEmpty = true;
            tvValidationTipoEventoId.setVisibility(View.VISIBLE);
            tvValidationTipoEventoId.setText("Selecionar tipo evento.");
        } else {
            tipoEvento = cod_TipoEvento;
            tvValidationTipoEventoId.setVisibility(View.GONE);
            tvValidationTipoEventoId.setText("");
        }

        if (selectTipoEntrada.equals("[Selecionar]")) {
            isFieldEmpty = true;
            tvValidationTipoEntradaId.setVisibility(View.VISIBLE);
            tvValidationTipoEntradaId.setText("Selecionar tipo entrada.");
        } else {
            tipoEntrada = cod_TipoEntrada;
            tvValidationTipoEntradaId.setVisibility(View.GONE);
            tvValidationTipoEntradaId.setText("");
        }

        if (selectEstado.equals("[Selecionar]")) {
            isFieldEmpty = true;
            tvValidationEstadoEventoId.setVisibility(View.VISIBLE);
            tvValidationEstadoEventoId.setText("Selecionar estado.");
        } else {
            estadoEvento = cod_Estado;
            tvValidationEstadoEventoId.setVisibility(View.GONE);
            tvValidationEstadoEventoId.setText("");
        }

       if (isEmpty(horaInicio)) {
            isFieldEmpty = true;
            tvValidationHoraInicio.setVisibility(View.VISIBLE);
            tvValidationHoraInicio.setText("La hora inicial no puede estar vacío.");
        } else {
            tvValidationHoraInicio.setVisibility(View.GONE);
            tvValidationHoraInicio.setText("");
        }

        if (isEmpty(horaFin)) {
            isFieldEmpty = true;
            tvValidationHoraFin.setVisibility(View.VISIBLE);
            tvValidationHoraFin.setText("La hora fin no puede estar vacío.");
        }else {
            tvValidationHoraFin.setVisibility(View.GONE);
            tvValidationHoraFin.setText("");
        }


        if (!isFieldEmpty) {
            Evento evento = new Evento();
            evento.setFechaString(fecha);
            evento.setLocalDeEvento(localEvento);
            evento.setDireccionLocal(direccionLocal);
            evento.setTipoEvento(tipoEvento);
            evento.setTipoEntrada(tipoEntrada);
           evento.setEstadoEvento(estadoEvento);
            evento.setHoraInicio(horaInicio);
            evento.setHoraFin(horaFin);
            return evento;
        } else return null;

    }

    private boolean isEmpty(String value) {
        return (value.length() < 1) ? true : false;
    }



    @Override
    protected void onResume() {
        super.onResume();
       // settingNavDrawer();
        invalidateOptionsMenu();
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

    private void obtenerHora(final TextView txt){
        TimePickerDialog recogerHora = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                txt.setText(horaFormateada + DOS_PUNTOS + minutoFormateado);
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }
    public void ObtenerFechaEvento(View view) {
        obtenerFecha(etFecha);
    }
    public void ObtenerFechaFin(View view) {
        obtenerHora(etHoraFin);
    }
    public void ObtenerFechaInicio(View view) {
        obtenerHora(etHoraInicio);
    }
    public void setAdaptadorSpinner(Spinner spinner,ArrayList<String> arrayList){

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(EventoRegistrar.this,android.R.layout.simple_spinner_dropdown_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    private void getTipoEvento(){
        //recoger los datos de la webservices
        String JSON_URL = Constants.LISTAR_TIPO_ENUMERADO +this.getString(R.string.tipo_evento);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<List<Enumerado>>() {
                }.getType();
                enumeradoList = new Gson().fromJson(response, listType);
                arraynomtipoEvento = new ArrayList<>();

                for (Enumerado enumerar : enumeradoList) {
                    arrayidTipoEvento.add(enumerar.getValor_enumerado());
                    arraynomtipoEvento.add(enumerar.getNombre());
                }
                arraynomtipoEvento.add(0,"[Selecionar]");
                setAdaptadorSpinner(spTipoEvento,arraynomtipoEvento);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
    }

    private void getTipoEntrada(){
        //recoger los datos de la webservices
        String JSON_URL = Constants.LISTAR_TIPO_ENUMERADO +this.getString(R.string.tipo_entrada);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<List<Enumerado>>() {
                }.getType();
                enumeradoList = new Gson().fromJson(response, listType);
                arraynomtipoEntrada = new ArrayList<>();
                for (Enumerado enumerar : enumeradoList) {
                   arrayidTipoEntrada.add(enumerar.getValor_enumerado());
                    arraynomtipoEntrada.add(enumerar.getNombre());
                }
                arraynomtipoEntrada.add(0,"[Selecionar]");
                setAdaptadorSpinner(spTipoEntrada,arraynomtipoEntrada);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
    }

    private void getEstadoEvento(){
        //recoger los datos de la webservices
        String JSON_URL = Constants.LISTAR_TIPO_ENUMERADO +this.getString(R.string.tipo_estado_evento);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<List<Enumerado>>() {
                }.getType();
                enumeradoList = new Gson().fromJson(response, listType);
                arraynomEstado = new ArrayList<>();
                for (Enumerado enumerar : enumeradoList) {
                    arrayidEstado.add(enumerar.getValor_enumerado());
                    arraynomEstado.add(enumerar.getNombre());
                }
               arraynomEstado.add(0,"[Selecionar]");
                setAdaptadorSpinner(spEstadoEvento,arraynomEstado);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.getCache().clear();
        queue.add(stringRequest);
    }



}
