package com.example.aloel.susu_panas;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
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
import com.example.aloel.susu_panas.Adapter.Adapter_Barang_Keluar;
import com.example.aloel.susu_panas.Model.Model_Barang_Keluar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class report_send extends AppCompatActivity
{
    // Movies json url
    private static final String url = "http://192.168.100.54:1777/history/out";
    private static final String url2 = "http://192.168.100.54:1777/item";
    private ProgressDialog pDialog;
    private ArrayList<Model_Barang_Keluar> barangList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter_Barang_Keluar adapter;
    private Model_Barang_Keluar model_barang;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    /*    getSupportActionBar().setLogo(R.drawable.tokoo);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_report_send);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        mLayoutManager =new GridLayoutManager(report_send.this,1);
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
                                model_barang = new Model_Barang_Keluar();
                                JSONObject object = jsonArray.getJSONObject(i);
                                //deklarasi
                                JSONObject idItem = object.getJSONObject("id_item");
                                model_barang.nama_klr = idItem.getString("nama");
                                model_barang.brg_klr = object.getString("qtyOut");
                                model_barang.tgl_klr = object.getInt("tglTransaksi");
                                barangList.add(model_barang);
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
        RequestQueue queue = Volley.newRequestQueue(report_send.this);
        queue.add(stringRequest);
    }
    private void showList(ArrayList<Model_Barang_Keluar>mList)
    {
        adapter = new Adapter_Barang_Keluar(mList,report_send.this);
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


