package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jenny on 23/03/2016.
 */
public class editEmergencyOption extends Activity {

    public static final String EXTRA_OPTION= "com.example.jenny.lifesavingaidui.option_edit_id";


    private void returnOptionId(int option){
        Intent option_id = new Intent();
        option_id.putExtra(EXTRA_OPTION,option);
        setResult(RESULT_OK,option_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_emergency_option);

        Button add_step_before = (Button) findViewById(R.id.editEmergency_addStep);
        add_step_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnOptionId(1);
                finish();
            }
        });

        Button modify = (Button) findViewById(R.id.editEmergency_modifyStep);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnOptionId(2);
                finish();
            }
        });

        Button delete = (Button) findViewById(R.id.editEmergency_deleteStep);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnOptionId(3);
                finish();
                //Intent i = new Intent(addOption.this,CreateNewEmergency.class);
                //startActivity(i);
            }
        });

        Button cancel = (Button) findViewById(R.id.addOption_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnOptionId(4);
                finish();
            }
        });
    }
}
