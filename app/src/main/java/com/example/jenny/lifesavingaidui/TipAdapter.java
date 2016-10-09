package com.example.jenny.lifesavingaidui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Jenny on 21/03/2016.
 */
public class TipAdapter extends ArrayAdapter<String> {
    String[] content;

    public TipAdapter(Context c,String[] content){
        super(c.getApplicationContext(),R.layout.tip_item_layout,content);
        this.content=content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.tip_item_layout,parent,false);
        }

        //display the step
        TextView index = (TextView) convertView.findViewById(R.id.steps);
        index.setText(Integer.toString(position+1));

        //display the tip
        TextView instruction = (TextView) convertView.findViewById(R.id.txt_tip);
        instruction.setText(content[position]);

        return convertView;
    }
}
