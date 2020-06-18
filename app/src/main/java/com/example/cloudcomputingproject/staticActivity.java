package com.example.cloudcomputingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class staticActivity extends AppCompatActivity {
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);
        queue = Volley.newRequestQueue(getApplicationContext());


    }


    @Override
    protected void onStart() {
        super.onStart();
        String url = "https://api.covid19api.com/summary";



        ListView lv = findViewById(R.id.lv2);
        final List<statics> ss = new ArrayList<statics>();
        final MyStaticsAdapter nn = new MyStaticsAdapter(getApplicationContext(), ss);
        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.listview_header, lv,false);
        // Add header view to the ListView
        lv.addHeaderView(headerView);
        lv.setAdapter(nn);
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

                        ss.add(statics);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                nn.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        queue.add(jsonObject);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}