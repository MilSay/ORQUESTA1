package com.android.app.orquesta.agrupacionDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.app.orquesta.R;
import com.android.app.orquesta.Util.Constants;
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
import java.util.Vector;

/**
 * Created by User on 2/28/2017.
 */

public class Tab2Galeria extends Fragment {
    public static final String ID_AGRUPACION = "idAgrupacion";
    public  int Id_agrupacion;

    public ArrayList<Videos> videosDetailList;
    RecyclerView recyclerViewGrupvideos;
   // Vector<Videos> youtubeVideos = new Vector<Videos>();

    public static Tab2Galeria newInstancia(int param1) {
        Tab2Galeria fragment = new Tab2Galeria();
        Bundle args = new Bundle();
        args.putInt(ID_AGRUPACION,param1);
        fragment.setArguments(args);
        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);
        Id_agrupacion = getArguments().getInt(ID_AGRUPACION);
        recyclerViewGrupvideos =view.findViewById(R.id.rv_agrup_video_listt);
        listaAgrupacionVideos();

        return view;
    }

    private void listaAgrupacionVideos() {

        String JSON_URL = Constants.AGRUPACION_VIDEOS_DETALLE_LIST + Id_agrupacion;
        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("responseV", response);
                Type listType = new TypeToken<List<Videos>>() {
                }.getType();
                videosDetailList = new Gson().fromJson(response, listType);
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
        //youtubeVideos.add(new Videos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>","lalal"));

        AgruVideosRecylerViewAdapter agruVideosRecylerViewAdapter = new AgruVideosRecylerViewAdapter(videosDetailList, getContext());

        recyclerViewGrupvideos.setHasFixedSize(true);
        recyclerViewGrupvideos.setLayoutManager(new LinearLayoutManager(getContext()) );
        recyclerViewGrupvideos.setAdapter(agruVideosRecylerViewAdapter);
    }
}
