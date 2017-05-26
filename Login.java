package com.example.aloel.susu_panas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private Button btn;
    private TextView text;
    private TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.drawable.surti);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        btn = (Button) findViewById(R.id.btnLogin);


        //kita set onClick si button start
        // setelah kita set onClick si Button start, kita pergi ke
        // Activity selanjutnya dengan bantuan INTENT

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Akun_login.class); // dari MainActivity/posisi saat ini ke SecondActivity
                startActivity(intent);
            }
        });

        text = (TextView) findViewById(R.id.register);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class); // dari MainActivity/posisi saat ini ke SecondActivity
                startActivity(intent);
            }
        });

        text1 = (TextView) findViewById(R.id.pass_lupa);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,lupa_password.class); // dari MainActivity/posisi saat ini ke SecondActivity
                startActivity(intent);
            }
        });
    }
}
