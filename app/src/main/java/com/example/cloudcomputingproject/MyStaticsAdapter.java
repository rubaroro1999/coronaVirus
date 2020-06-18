package com.example.cloudcomputingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyStaticsAdapter extends BaseAdapter {
    Context context;
    List<statics> statics;


    public  MyStaticsAdapter (Context context, List<statics> statics){
        this.context = context;
        this.statics = statics;

    }
    @Override
    public int getCount() {
        return statics.size();
    }

    @Override
    public Object getItem(int position) {
        return statics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null){
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView= inflater.inflate(R.layout.staticslayout, parent, false);
           viewHolder vh = new viewHolder();
           vh.TotalConfirmed = convertView.findViewById(R.id.confirm);
           vh.TotalDeaths = convertView.findViewById(R.id.death);
           vh.TotalRecovered = convertView.findViewById(R.id.recover);
           vh.Country = convertView.findViewById(R.id.country);

           convertView.setTag(vh);
       }
       viewHolder vh = ( viewHolder ) convertView.getTag();
       vh.TotalConfirmed.setText(statics.get(position).getTotalConfirmed());

       vh.TotalDeaths.setText(statics.get(position).getTotalDeaths());
        vh.TotalRecovered.setText(statics.get(position).getTotalRecovered());
       vh.Country.setText(statics.get(position).getCountry());
        return convertView;
    }

    class viewHolder {
        TextView TotalConfirmed;
        TextView TotalDeaths;
        TextView TotalRecovered;
        TextView Country;
    }
}
