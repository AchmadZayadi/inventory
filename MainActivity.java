package com.example.aloel.susu_panas;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    CardView cardView,cardView2,cardView3,cardView4,cardView5,cardView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*getSupportActionBar().setLogo(R.drawable.surti);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_main);

        cardView = (CardView) findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenulain();
            }
        });

        cardView2 = (CardView) findViewById(R.id.card_view2);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input();
            }
        });

        cardView3 = (CardView) findViewById(R.id.card_view3);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stock();
            }
        });

        cardView4 = (CardView) findViewById(R.id.card_view4);
        cardView4.setOnClickListener(new  View.OnClickListener(){

            @Override
                    public void onClick(View v){
                    report_masuk();
            }

        });

        cardView5 = (CardView) findViewById(R.id.card_view5);
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                report_keluar();
            }
        });

        cardView6 = (CardView) findViewById(R.id.card_view6);
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        System.exit(1);
    }

    public void openMenulain(){
        Intent intent = new Intent(this,new_item.class);
        startActivity(intent);
    }

    public void stock(){
        Intent intent = new Intent(this,stock_item.class);
        startActivity(intent);
    }

    public void input(){
        Intent intent = new Intent(this,input_item.class);
        startActivity(intent);
    }
    public void Logout(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    public void report_keluar(){
        Intent intent = new Intent(this,report_send.class);
        startActivity(intent);
    }

    public  void report_masuk(){
        Intent intent = new Intent(this,laporan_barang_masuk.class);
        startActivity(intent);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //MENU KANAN ATAS
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Masuk) {
            return true;
        }
        if (id == R.id.action_Keluar){
            return  true;

        }

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }*/


}
