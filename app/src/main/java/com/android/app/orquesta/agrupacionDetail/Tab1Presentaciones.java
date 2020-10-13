package com.android.app.orquesta.agrupacionDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
import com.android.app.orquesta.Util.NetworkHelper;
import com.android.app.orquesta.agrupacion.AgrupacionRecylerViewAdapter;
import com.android.app.orquesta.model.Agrupacion;
import com.android.app.orquesta.model.Evento;
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

public class Tab1Presentaciones extends Fragment {


    public static final String ID_AGRUPACION = "idAgrupacion";
    public  int Id_agrupacion;

    public ArrayList<Evento> eventoDetailList;
    RecyclerView recyclerViewGrupEvento;

    public static Tab1Presentaciones newInstancia(int param1) {
        Tab1Presentaciones fragment = new Tab1Presentaciones();
        Bundle args = new Bundle();
        args.putInt(ID_AGRUPACION,param1);
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);
        Id_agrupacion = getArguments().getInt(ID_AGRUPACION);
        recyclerViewGrupEvento =view.findViewById(R.id.rv_agrup_evento_listt);
        listaAgrupacionEvento();
        return view;
    }

    private void listaAgrupacionEvento() {

            String JSON_URL = Constants.AGRUPACION_EVENTO_DETALLE_LIST + Id_agrupacion;
            RequestQueue queue = Volley.newRequestQueue(getContext());

            StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("responseV", response);
                    Type listType = new TypeToken<List<Evento>>() {
                    }.getType();
                    eventoDetailList = new Gson().fromJson(response, listType);
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

        AgruEventoRecylerViewAdapter agrupacionEventoRecylerViewAdapter = new AgruEventoRecylerViewAdapter(eventoDetailList, getContext());

        recyclerViewGrupEvento.setHasFixedSize(false);
        recyclerViewGrupEvento.setLayoutManager(new GridLayoutManager(getContext(), 1));
        recyclerViewGrupEvento.setNestedScrollingEnabled(false);
        recyclerViewGrupEvento.setAdapter(agrupacionEventoRecylerViewAdapter);
    }


}
