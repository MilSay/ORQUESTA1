package com.android.app.orquesta.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.android.app.orquesta.Util.HttpHelper;

import java.io.IOException;

public class ProductGridService extends IntentService {

    public static final String MY_MESSAGE = "myMessage";
    public static final String MY_PAYLOAD = "mypayload";



    public ProductGridService() {
        super("MyServices");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {



        Uri url = intent.getData();
        String returnValues = null;
        try {
            returnValues = HttpHelper.downloadUrl(url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
       // Product[] Products = gson.fromJson(returnValues, Product[].class);

        Intent myIntent = new Intent(MY_MESSAGE);
        //myIntent.putExtra(MY_PAYLOAD, Products);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(myIntent);

    }
}
