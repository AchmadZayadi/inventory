package com.example.aloel.susu_panas;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class report_send extends AppCompatActivity {

    private ArrayList<model_report_send> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    /*    getSupportActionBar().setLogo(R.drawable.tokoo);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_report_send);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);


    }
}

