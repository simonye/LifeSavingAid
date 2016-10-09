package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.jenny.lifesavingaidui.R.id.addOption_cancel;
import static com.example.jenny.lifesavingaidui.R.id.pick_from_bank;

/**
 * Created by Jenny on 22/03/2016.
 */
public class addOption extends Activity {

   public static final String EXTRA_OPTION= "com.example.jenny.lifesavingaidui.option_add_id";


    private void returnOptionId(int option){
        Intent option_id = new Intent();
        option_id.putExtra(EXTRA_OPTION,option);
        setResult(RESULT_OK,option_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_option);

        Button pick = (Button) findViewById(R.id.pick_from_bank);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnOptionId(1);
               finish();

                  //Intent i = new Intent(addOption.this,SavingTipBank.class);
                  //startActivity(i);
            }
        });

        Button add_own = (Button) findViewById(R.id.create_own);
        add_own.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnOptionId(2);
                finish();
                //Intent i = new Intent(addOption.this,CreateNewEmergency.class);
                //startActivity(i);
            }
        });

        Button cancel = (Button) findViewById(R.id.addOption_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnOptionId(0);
                finish();
            }
        });
    }
}
