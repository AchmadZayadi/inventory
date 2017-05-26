package com.example.aloel.susu_panas;

import android.app.DatePickerDialog;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class send_item extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editTextTanggal;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private ProgressDialog pDialog;
    Model_Barang model_barang;
    private ArrayList<Model_Barang> barangList = new ArrayList<>();
    Spinner spinner;
    private static final String url = "http://192.168.88.25:1777/item";
    EditText text1 , text2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      /*  getSupportActionBar().setLogo(R.drawable.tokoo);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_send_item);

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar newCalendar = Calendar.getInstance();
        setDateTimeField();
        editTextTanggal = (EditText)findViewById(R.id.edit_text_33);
        editTextTanggal.setFocusableInTouchMode(false);
        editTextTanggal.setFocusable(false);
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String date = df.format(c.getTime());
        editTextTanggal.setText(date);

        editTextTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });

        ParsingSpinner();

        spinner = (Spinner) findViewById(R.id.spinner);
        text1 = (EditText) findViewById(R.id.edit_text_12);
        text2 = (EditText) findViewById(R.id.edit_text_33);
        btn = (Button) findViewById(R.id.btnLogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String ID = spinner.toString().trim();
                String Jumlah_keluar = text1.getText().toString().trim();
                String Tanggal = text2.getText().toString().trim();
                // TODO Auto-generated method stub
                if (text1.equals("")||text2.equals(""))
                {
                    Toast.makeText(send_item.this, "data kosong gan", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    postData(ID,Jumlah_keluar,Tanggal);

                }
            }


        });
    }

    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextTanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public void postData(final String ID, final String Jumlah_keluar , final String Tanggal){
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(send_item.this);
        progressDialog.show();
        String url = "http://192.168.88.25:1777/transaction";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(send_item.this,"DATA BERHASIL BERTAMBAH", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        //postData_JumlahMasuk(jm);
                        // refreshFeed();
                        progressDialog.dismiss();
                    }
                }
                , new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(send_item.this, "Data Masih ada yang kosong", Toast.LENGTH_SHORT).show();
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
                obj.put("kode",ID);
                obj.put("jmlKeluar", Jumlah_keluar);
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

    private void ParsingSpinner(){

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
                                model_barang.idTransaksi = object.getString("kode");
                                barangList.add(model_barang);

                            }
                            populateSpinner();
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
        RequestQueue queue = Volley.newRequestQueue(send_item.this);
        queue.add(stringRequest);

    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void populateSpinner() {
        List<String> lables = new ArrayList<String>();
        // mList = new ArrayList<String>();

        //txtCategory.setText("");

        for (int i = 0; i < barangList.size(); i++) {
            model_barang = barangList.get(i);
            lables.add(model_barang.idTransaksi);
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(spinnerAdapter);
    }

    private void loadSpinnerData()
    {
        // database handler
        database db = new database(getApplicationContext());

        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        /*Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
*/
        database db = new database(this);
        String stock = db.getKEY_stock(label);

        // editText_stock.setText(stock);

    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }



    }
