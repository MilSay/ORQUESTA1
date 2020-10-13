package com.android.app.orquesta.agrupacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.app.orquesta.R;

public class AgrupacionRegistrar extends AppCompatActivity
        implements View.OnClickListener {

    // SPONSORED PRODUCTS GRID



    /*START OF MENU VERIABLE*/
    Toolbar toolbar;
    Button btnAddCart;
    /*END OF MENU VERIABLE*/



    private boolean networkOK;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_agrupacion);

        /*START OF TOOLBAR*/
        TextView toobarTitle = findViewById(R.id.toolbar_title);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toobarTitle.setText(this.getString(R.string.title_agrupacion));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*END OF TOOLBAR*/



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


}
