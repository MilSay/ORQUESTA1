package com.android.app.orquesta.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.android.app.orquesta.Util.HttpHelper;
import com.android.app.orquesta.Util.RequestPackage;

import java.io.IOException;

public class ProductGridGetService extends IntentService {

    public static final String MY_MESSAGE_GET = "myMessageGet";
    public static final String MY_PAYLOAD_GET = "mypayloadGet";
    public static final String PRODUCT_REQUEST_PACKAGE = "productRequestPackage";

    public ProductGridGetService() {
        super("ProductGridGetService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        RequestPackage requestPackage = (RequestPackage) intent.getParcelableExtra(PRODUCT_REQUEST_PACKAGE);

        String returnValues = null;
        try {
            returnValues = HttpHelper.downloadUrl(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
       // Product[] Products = gson.fromJson(returnValues, Product[].class);
        Intent myIntent = new Intent(MY_MESSAGE_GET);
       // myIntent.putExtra(MY_PAYLOAD_GET, Products);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(myIntent);

    }

}
