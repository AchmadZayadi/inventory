package com.example.aloel.susu_panas;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aloel.susu_panas.Adapter.Adapter_Barang;
import com.example.aloel.susu_panas.Model.Model_Barang;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tabel_barang extends AppCompatActivity {

    // Movies json url
    private static final String url = "http://192.168.88.25:1777/transaction";
    private ProgressDialog pDialog;
    private ArrayList<Model_Barang> barangList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter_Barang adapter;
    private Model_Barang model_barang;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabel_barang);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        mLayoutManager =new GridLayoutManager(tabel_barang.this,1);
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
                                model_barang = new Model_Barang();
                                JSONObject object = jsonArray.getJSONObject(i);
                                model_barang.idTransaksi = object.getString("_id");
                                JSONObject iname = object.getJSONObject("id_item");
                                model_barang.nama = iname.getString("nama");
                                model_barang.createdAt = object.getString("createdAt");
                                model_barang.jmlMasuk = object.has("jmlMasuk")? object.getString("jmlMasuk") :"";
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
        RequestQueue queue = Volley.newRequestQueue(tabel_barang.this);
        queue.add(stringRequest);
    }
    private void showList(ArrayList<Model_Barang>mList){
        adapter = new Adapter_Barang(mList,tabel_barang.this);
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
