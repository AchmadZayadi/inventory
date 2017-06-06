package com.example.aloel.susu_panas;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aloel.susu_panas.Model.Model_Barang;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class input_item extends AppCompatActivity
{

    EditText jmlBrg;
    SearchableSpinner searchableSpinner;
    EditText editTanggal;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    // Add buttn
    Button btnAdd;
    // Input text
    EditText inputLabel;
    String hh;
    EditText text2 ,text3,text4;
    Button btn ;
    private ProgressDialog pDialog;
    Model_Barang model_barang;
    private static final String url = "http://192.168.100.54:1777/item";
    private String idBarang;
    private ArrayList<Model_Barang> barangList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      /*  getSupportActionBar().setLogo(R.drawable.tokoo);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_input_item);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        Time Today = new Time();
        Today.setToNow();
        dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        setDateTimeField();
        editTanggal = (EditText) findViewById(R.id.edit_text_33);
        editTanggal.setFocusableInTouchMode(false);
        editTanggal.setFocusable(false);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        String date = df.format(c.getTime());
        editTanggal.setText(date);
        editTanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });
        // Spinner element
        searchableSpinner = (SearchableSpinner) findViewById(R.id.spinner);
        // add button
        btnAdd = (Button) findViewById(R.id.simpan_new_item);
        // new label input field
        inputLabel = (EditText) findViewById(R.id.edit_text_11);
        // Spinner click listener
        // Loading spinner data from database
        ParsingSpinner();
        searchableSpinner = (SearchableSpinner) findViewById(R.id.spinner);
       // spinner2 = (Spinner) findViewById(R.id.spinner2);
        text2 = (EditText) findViewById(R.id.edit_text_12);
        text3 = (EditText) findViewById(R.id.edit_text_33);
        btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String ID = searchableSpinner.getSelectedItem().toString();
                String Jumlah_masuk = text2.getText().toString().trim();
                String Tanggal = text3.getText().toString().trim();
               // String Status = spinner2.getSelectedItem().toString();
                // TODO Auto-generated method stub
                if (text2.equals("")||text3.equals(""))
                {
                    Toast.makeText(input_item.this, "data kosong gan", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    postData(Jumlah_masuk,Tanggal);
                    Log.d("test",Jumlah_masuk+","+Tanggal);

                }
            }
        });
    }

    public void postData(final String Jumlah_masuk , final String Tanggal)
    {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(input_item.this);
        progressDialog.show();
        String url = "http://192.168.100.54:1777/transaction/in";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                     //   Log.d("sss", "jay response = "+response);
                        Toast.makeText(input_item.this,"Barang Berhasil Bertambah", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        progressDialog.dismiss();
                    }
                }
                , new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.dismiss();
                Toast.makeText(input_item.this, "Data Masih ada yang kosong", Toast.LENGTH_SHORT).show();
                VolleyLog.e("PostQuestion", "Error" + error.getMessage());
            }
        })
        {
            @Override
            public String getBodyContentType()
            {
                return "application/x-www-form-urlencoded";

            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> obj = new HashMap<String, String>();
                Log.d("testis",idBarang);
                obj.put("id_item",idBarang);
                obj.put("qtyIn", Jumlah_masuk);
                obj.put("tgl", Tanggal);
                return obj;
            }
        };

        strReq.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this).add(strReq);

    }

    private void ParsingSpinner()
    {
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
                            for (int i = 0; i < jsonArray.length(); i++) {
                                model_barang = new Model_Barang();
                                JSONObject object = jsonArray.getJSONObject(i);
                                model_barang.nama = object.getString("nama");
                                model_barang.idTransaksi = object.getString("_id");
                                barangList.add(model_barang);
                            }
                            populateSpinner();
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
        RequestQueue queue = Volley.newRequestQueue(input_item.this);
        queue.add(stringRequest);

    }
    private void hidePDialog()
    {
        if (pDialog != null)
        {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void populateSpinner()
    {
        List<String> lables = new ArrayList<String>();
        for (int i = 0; i < barangList.size(); i++)
        {
            model_barang = barangList.get(i);
            lables.add(model_barang.nama);
        }
        // Creating adapter for spinner
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
             spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        searchableSpinner.setAdapter(spinnerAdapter);
        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //String selectedItem = parent.getItemAtPosition(position).toString();
                model_barang = barangList.get(position);
                String idx = model_barang.idTransaksi;
                //Toast.makeText(input_item.this,idx,Toast.LENGTH_SHORT).show();
                idBarang = idx;

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private void setDateTimeField()
    {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
}