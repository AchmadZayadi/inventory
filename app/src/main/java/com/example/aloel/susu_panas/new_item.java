package com.example.aloel.susu_panas;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class new_item extends AppCompatActivity
{
    EditText editTextTanggal;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    protected Cursor cursor;
    database data_base;
    Button ton1;
    EditText text1, text2, text3,text4;
    String hh;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
     /* getSupportActionBar().setLogo(R.drawable.tokoo);*/
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setContentView(R.layout.activity_new_item);
        // jam
        Time Today = new Time();
        Today.setToNow();
        data_base= new database(this);
        text1 = (EditText) findViewById(R.id.edit_text_11);
        text2 = (EditText) findViewById(R.id.edit_text_22);
        text3 = (EditText) findViewById(R.id.edit_text_33);
        text4 = (EditText) findViewById(R.id.edit_text_12);
        ton1 = (Button) findViewById(R.id.simpan_new_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        ton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                String ID = text4.getText().toString();
                String nama = text1.getText().toString().trim();
                String barang_baru = text2.getText().toString().trim();
                String Tanggal = text3.getText().toString().trim();
                // TODO Auto-generated method stub
                if (ID.equals("")|| nama.equals("")||barang_baru.equals("") || Tanggal.equals(""))
                {
                    Toast.makeText(new_item.this, "data kosong gan", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    postData(ID,nama,barang_baru,Tanggal);

                }
            }
        });
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
        //TANGGAL
        dateFormatter = new SimpleDateFormat("dd MMM yyyy");
        setDateTimeField();
        editTextTanggal = (EditText)findViewById(R.id.edit_text_33);
        editTextTanggal.setFocusableInTouchMode(false);
        editTextTanggal.setFocusable(false);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        String date = df.format(c.getTime());
        //JAM
        hh = (Today.format("%H:%m"));
        editTextTanggal.setText(date) ;
        editTextTanggal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });
        setDateTimeField();
    }

    public void postData(final String ID, final String nama, final String barang_baru , final String Tanggal)
    {
        final ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(new_item.this);
        progressDialog.show();
        String url = "http://192.168.100.54:1777/item";
        StringRequest strReq = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(new_item.this,"DATA BERHASIL BERTAMBAH", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(new_item.this, "Data Masih ada yang kosong", Toast.LENGTH_SHORT).show();
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
                obj.put("nama", nama);
                obj.put("qtyIn", barang_baru);
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
    private void setDateTimeField()
    {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextTanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}
