package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyTheatreAdapter extends ArrayAdapter<MyTheatre> {


    public MyTheatreAdapter(Context context, MyTheatre[] arr) {
        super(context, R.layout.adapter_item, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MyTheatre theatre = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }

        ((TextView) convertView.findViewById(R.id.textView)).setText(theatre.name);
        ((TextView) convertView.findViewById(R.id.textView2)).setText(String.valueOf(theatre.genre));
        ((TextView) convertView.findViewById(R.id.textView3)).setText(String.valueOf(theatre.volChairs));


        return convertView;
    }


}