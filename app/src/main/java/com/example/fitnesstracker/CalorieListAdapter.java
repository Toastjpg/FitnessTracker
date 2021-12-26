package com.example.fitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fitnesstracker.model.Entry;

import java.util.ArrayList;

public class CalorieListAdapter extends ArrayAdapter<Entry> {
    private final int resource;
    private final Context context;

    public CalorieListAdapter(Context context, int resource, ArrayList<Entry> items) {
        super(context, resource, items);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
        }

        Entry entry = getItem(position);

        if (entry != null){
            TextView titleText = view.findViewById(R.id.adapterTextViewTitle);
            TextView countText = view.findViewById(R.id.adapterTextViewCount);

            titleText.setText(entry.getName());
            countText.setText(String.valueOf(entry.getNumCalories()));
        }

        return view;
    }
}
