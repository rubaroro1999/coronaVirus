package com.example.cloudcomputingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class customActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    String[] users = { "Albania", "Palestinian Territory", "Pakistan", "Korea (South)", "India" };

    RequestQueue queue;
    TextView textView , textView2 , textView3 , textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        queue = Volley.newRequestQueue(getApplicationContext());
        textView = findViewById(R.id.tv1);
        textView2 = findViewById(R.id.tv2);
        textView3 = findViewById(R.id.tv3);
        textView4 = findViewById(R.id.tv4);

        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

        String url = "https://api.covid19api.com/summary";

        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("Countries");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject country = jsonArray.getJSONObject(i);
                        statics statics = new statics();

                        String Country = country.getString("Country");
                        statics.setCountry(Country);
                        String TotalConfirmed = country.getString("TotalConfirmed");
                        statics.setTotalConfirmed(TotalConfirmed);
                        String TotalDeaths = country.getString("TotalDeaths");
                        statics.setTotalDeaths(TotalDeaths);
                        String TotalRecovered = country.getString("TotalRecovered");
                        statics.setTotalRecovered(TotalRecovered);
                        if(Country.equals(users[position])){

                            textView.setText(statics.getCountry());
                            textView2.setText(statics.getTotalConfirmed());
                            textView3.setText(statics.getTotalRecovered());
                            textView4.setText(statics.getTotalDeaths());

                        }
                        else{
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        queue.add(jsonObject);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
