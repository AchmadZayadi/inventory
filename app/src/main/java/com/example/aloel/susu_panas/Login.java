package com.example.aloel.susu_panas;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity
{

    private Button btn;
    private TextView text;
    private TextView text1;
    EditText mEtPwd;
    CheckBox mCbShowPwd;
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setLogo(R.drawable.surti);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        info = (TextView)findViewById(R.id.info);


      /*  btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });*/
      /*  text = (TextView) findViewById(R.id.register);
        text.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        text1 = (TextView) findViewById(R.id.pass_lupa);
        text1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,lupa_password.class);
                startActivity(intent);
            }
        });

        // get the password EditText
        mEtPwd = (EditText) findViewById(R.id.password_login);
        // get the show/hide password Checkbox
        mCbShowPwd = (CheckBox) findViewById(R.id.cbShowPassword);

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        mCbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    mEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    mEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
      */  printHashKey(Login.this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);

                /*info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
*/

            }

            @Override
            public void onCancel() {
                info.setText("Login Di Batalkan.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Gagal LOGIN ");
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    public void printHashKey(Context pContext) {
        try {
            PackageManager pm = pContext.getPackageManager();
            PackageInfo info = pm.getPackageInfo("com.example.aloel.susu_panas",PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.d("login", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e)
        {
            Log.d("login", "printHashKey()", e);
        }
        catch (Exception e)
        {
            Log.d("login", "printHashKey()", e);
        }
    }
}

