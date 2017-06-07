package com.example.aloel.susu_panas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity
{
    Button Close;
    Button Create;
    CardView cardView,cardView2,cardView3,cardView4,cardView5,cardView6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.exit);
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
                Barang_klr();
            }
        });

        cardView4 = (CardView) findViewById(R.id.card_view4);
        cardView4.setOnClickListener(new  View.OnClickListener(){

            @Override
                    public void onClick(View v){
                    stock_brg();
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
                LAPORAN_MSK();
            }
        });


    }

    public void exit(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        // set the title of the Alert Dialog
        alertDialogBuilder.setTitle("Anda Yakin Keluar?");

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes Untuk Keluar!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                Intent intent = new Intent();
                                setResult(RESULT_OK,intent);
                                finish();

                            }
                        })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                // if no is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }


    @Override
    public void onBackPressed()
    {

    }

    public void openMenulain()
    {
        Intent intent = new Intent(this,new_item.class);
        startActivity(intent);
    }

    public void Barang_klr()
    {
        Intent intent = new Intent(this,Barang_Keluar.class);
        startActivity(intent);
    }

    public void input()
    {
        Intent intent = new Intent(this,input_item.class);
        startActivity(intent);
    }
    public void LAPORAN_MSK()
    {
        Intent intent = new Intent(this,laporan_barang_masuk.class);
        startActivity(intent);
    }

    public void report_keluar()
    {
        Intent intent = new Intent(this,report_send.class);
        startActivity(intent);
    }

    public  void stock_brg()
    {
        Intent intent = new Intent(this,stock_item.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        //MENU KANAN ATAS
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();


        if (id == R.id.action_Keluar)
        {
            exit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
