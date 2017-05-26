package com.example.aloel.susu_panas;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class form_report extends AppCompatActivity {

    CardView cardView5;
    CardView cardView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      /*  getSupportActionBar().setLogo(R.drawable.tokoo);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_form_report);

        cardView5 = (CardView) findViewById(R.id.card_view5);
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formreport();
            }
        });

        cardView6 = (CardView) findViewById(R.id.card_view6);
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laporan();
            }
        });
    }

    public void formreport(){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    public void laporan(){
        Intent intent = new Intent(this,report_send.class);
        startActivity(intent);
    }
}
