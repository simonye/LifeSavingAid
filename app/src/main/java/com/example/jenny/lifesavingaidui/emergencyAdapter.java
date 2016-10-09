package com.example.jenny.lifesavingaidui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jenny on 21/03/2016.
 */
public class emergencyAdapter extends ArrayAdapter<emergency> {

    ArrayList<emergency> list;

    public emergencyAdapter(Context c, ArrayList<emergency> emergencies){
        super(c.getApplicationContext(),R.layout.emergency_item_layout,emergencies);
        list = emergencies;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.emergency_item_layout,parent,false);
        }

        //display title
        TextView title = (TextView) convertView.findViewById(R.id.emergency_title);
        title.setText(list.get(position).getEmergency_title());

        //display date
        TextView date = (TextView) convertView.findViewById(R.id.emergency_date);
        date.setText(list.get(position).getDate());

        return convertView;
    }
}
