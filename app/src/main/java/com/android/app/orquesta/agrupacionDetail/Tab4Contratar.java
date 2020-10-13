package com.android.app.orquesta.agrupacionDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.UtilityClass;
import com.android.app.orquesta.model.AgrupacionDetalle;
import com.android.app.orquesta.model.Videos;
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
import java.util.List;

/**
 * Created by User on 2/28/2017.
 */

public class Tab4Contratar extends Fragment {
    public static final String ID_AGRUPACION = "idAgrupacion";
    public  int Id_agrupacion;

    public ArrayList<AgrupacionDetalle> contactarDetailList;
    public AgrupacionDetalle contactar;
    public TextView nombres,cargo,celular,fb,tw,youtuve,email, direccion;

    public static Tab4Contratar newInstancia(int param1) {
        Tab4Contratar fragment = new Tab4Contratar();
        Bundle args = new Bundle();
        args.putInt(ID_AGRUPACION,param1);
        fragment.setArguments(args);
        return fragment;

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4_fragment,container,false);
        Id_agrupacion = getArguments().getInt(ID_AGRUPACION);

        nombres = view.findViewById(R.id.tv_agru_contratar_nombres);
        cargo = view.findViewById(R.id.tv_agru_contratar_cargo);
        celular = view.findViewById(R.id.tv_agru_contratar_celular);
        fb = view.findViewById(R.id.tv_agru_contratar_facebook);
        tw = view.findViewById(R.id.tv_agru_contratar_twitter);
        youtuve = view.findViewById(R.id.tv_agru_contratar_youtuve);

        email = view.findViewById(R.id.tv_agru_contratar_email);
        direccion = view.findViewById(R.id.tv_agru_contratar_direccion);

        listaAgrupacionContratar();
        UtilityClass.text1ViewScaleIconLeft(getContext(), view.findViewById(R.id.tv_agru_contratar_facebook), R.drawable.ic_facebook);
        UtilityClass.text1ViewScaleIconLeft(getContext(), view.findViewById(R.id.tv_agru_contratar_twitter),R.drawable.ic_twitter );
        UtilityClass.text1ViewScaleIconLeft(getContext(), view.findViewById(R.id.tv_agru_contratar_youtuve),R.drawable.ic_youtube);

        return view;
    }


    private void listaAgrupacionContratar() {

        String JSON_URL = Constants.AGRUPACION_CONTACTAR_DETALLE_LIST + Id_agrupacion;
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseV", response);
                Type listType = new TypeToken<List<AgrupacionDetalle>>() {
                }.getType();
                contactarDetailList = new Gson().fromJson(response, listType);

                for (AgrupacionDetalle contDetalle :contactarDetailList) {
                    contactar=contDetalle;
                    nombres.setText(contactar.getNombres() +" "+contactar.getApellidos());
                    cargo.setText(contactar.getTipoPersona());
                    celular.setText(contactar.getCelular());
                    fb.setText(contactar.getFacebook());
                    tw.setText(contactar.getTwitter());
                    youtuve.setText(contactar.getYoutuve());
                    email.setText(contactar.getEmail());
                    direccion.setText(contactar.getDireccion());
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

}
