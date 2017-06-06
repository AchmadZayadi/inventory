package com.example.aloel.susu_panas;

import android.app.ProgressDialog;
import android.os.Build;
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
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aloel.susu_panas.Adapter.Adapter_Barang_Keluar;
import com.example.aloel.susu_panas.Adapter.Adapter_Barang_Masuk;
import com.example.aloel.susu_panas.Adapter.Adapter_Stock_barang;
import com.example.aloel.susu_panas.Model.Model_Barang_Keluar;
import com.example.aloel.susu_panas.Model.Model_Barang_Masuk;
import com.example.aloel.susu_panas.Model.Model_Stock_Barang;
import com.example.aloel.susu_panas.util.TLSSocketFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class laporan_barang_masuk extends AppCompatActivity
{
    // Movies json url
    private static final String url = "http://192.168.100.54:1777/history/in";
    private ProgressDialog pDialog;
    private ArrayList<Model_Barang_Masuk> barangList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter_Barang_Masuk adapter;
    private Model_Barang_Masuk model_barang_msk;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
     /* getSupportActionBar().setLogo(R.drawable.tokoo);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_laporan_barang_masuk);
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        mLayoutManager =new GridLayoutManager(laporan_barang_masuk.this,1);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        initControls();
    }

    private void initControls()
    {
        recyclerView.setAdapter(adapter);
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("responya",response);
                        hidePDialog();
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                model_barang_msk = new Model_Barang_Masuk();
                                JSONObject object = jsonArray.getJSONObject(i);

                                JSONObject idItem = object.getJSONObject("id_item");

                                model_barang_msk.nama_msk = idItem.getString("nama");
                                model_barang_msk.brg_msk = object.getString("qtyIn");
                                model_barang_msk.tgl_msk = object.getInt("tglTransaksi");
                                barangList.add(model_barang_msk);
                            }
                            showList(barangList);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                VolleyLog.d("tot", "Error: " + error.getMessage());
                hidePDialog();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(laporan_barang_masuk.this);
        queue.add(stringRequest);
    }
    private void showList(ArrayList<Model_Barang_Masuk>mList)
    {
        adapter = new Adapter_Barang_Masuk(mList,laporan_barang_masuk.this);
        recyclerView.setAdapter(adapter);
        adapter.setData(mList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog()
    {
        if (pDialog != null)
        {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}


