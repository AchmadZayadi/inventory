package com.example.aloel.susu_panas;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aloel.susu_panas.Adapter.Adapter_Stock_barang;
import com.example.aloel.susu_panas.Model.Model_Stock_Barang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class stock_item extends Activity {
    // Movies json url
    private static final String url = "http://192.168.88.44:1777/item";
    private ProgressDialog pDialog;
    private ArrayList<Model_Stock_Barang> barangList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter_Stock_barang adapter;
    private Model_Stock_Barang model_barang;
    private GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* getSupportActionBar().setDisplayShowHomeEnabled(true);
        *//*getSupportActionBar().setLogo(R.drawable.tokoo);*//*
        getSupportActionBar().setDisplayUseLogoEnabled(true);
       */
        setContentView(R.layout.activity_stock_item);


      /*  ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
*/
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        mLayoutManager =new GridLayoutManager(stock_item.this,1);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);



        initControls();
    }
    private void initControls() {
        recyclerView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responya",response);
                        hidePDialog();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                model_barang = new Model_Stock_Barang();
                                JSONObject object = jsonArray.getJSONObject(i);
                                model_barang.kode = object.getString("kode");
                                model_barang.nama = object.getString("nama");
                                barangList.add(model_barang);
                            }
                            showList(barangList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("tot", "Error: " + error.getMessage());
                hidePDialog();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(stock_item.this);
        queue.add(stringRequest);
    }
    private void showList(ArrayList<Model_Stock_Barang>mList){
        adapter = new Adapter_Stock_barang(mList,stock_item.this);
        recyclerView.setAdapter(adapter);
        adapter.setData(mList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}

