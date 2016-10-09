package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;

/**
 * Created by Jenny on 22/03/2016.
 */
public class CreateNewEmergency extends Activity {

    public static final String EXTRA_EMERGENCY = "com.example.jenny.lifesavingaidui.extra_emergency";
    private static final String TAG = "CreateNewEmergency";
    emergency new_emergency;
    int position;
    EditText title, editInstruction;
    String title_started;
    Button instruction, back;
    ListView list_instr;
    boolean change_made = false;

    String new_instruction;
    int optionId=0;
    int position_clicked=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_emergency);

        //get the value of position from other activities and decide what to do
        // if value is -100, then create a new emergency
        // else we get emergency from UserProfile
        title = (EditText) findViewById(R.id.createEmergency_title);

        position = getIntent().getIntExtra(EXTRA_EMERGENCY, -1);
        if (position == -1) {
            Log.d(TAG, "position has default value");
        } else {

            if (position == -100) {//create new emergency
                new_emergency = new emergency("");
                title_started = "";

            } else {// position is the index of emergency in UserProfile
                new_emergency = UserProfile.get(this).getEmergencyItem(position);
                title_started = new_emergency.getEmergency_title();
                title.setText(title_started);
            }
        }

        //reference buttons
        editInstruction= (EditText) findViewById(R.id.createEmergency_newInstruction);
        instruction = (Button) findViewById(R.id.createEmergency_addInstruction);
        instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_instruction=editInstruction.getText().toString();
                if(! new_instruction.equals("")){
                   change_made=true;
                   new_emergency.add_instr(new_instruction);
                    editInstruction.setText("");
                    new_instruction="";
                    updateListView();
                }
            }
        });


        //set up the list of instructions and display;
        list_instr = (ListView) findViewById(R.id.createEmergency_list);
        updateListView();
        list_instr.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                position_clicked=position;
                Intent i = new Intent(CreateNewEmergency.this,editEmergencyOption.class);
                startActivityForResult(i,1);
                return true;
            }
        });


        //set up back button and it's behaviour
        back = (Button) findViewById(R.id.createEmergency_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title_end = title.getText().toString();
                // if title has been changed since we start activity
                // then we should update emergency
                if (!(title_end.equals(title_started) || title_end.equals(""))) {
                    Log.d(TAG,"title_end: "+title_end+" title_start: "+title_started);
                    change_made = true;
                    new_emergency.setEmergency_title(title_end);
                }

                if (change_made) {
                    new_emergency.setNewDate();
                    if (position == -100) {
                        UserProfile.get(CreateNewEmergency.this).addEmergencyItem(new_emergency);
                    } else {
                        UserProfile.get(CreateNewEmergency.this)
                                .setEmergencyItem(position, new_emergency);
                    }
                }
                change_made=false;
                finish();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

          Log.d(TAG,"new_instruction in onResume "+new_instruction);
        if(optionId!=0){

            if(optionId==4){}//option is cancel, do nothing
            else if(optionId == 3){//option is delete
                change_made=true;
                new_emergency.remove(position_clicked);
            }else if (optionId==2){//option is modify
                if(!new_instruction.equals("")) {
                    change_made = true;
                    new_emergency.modify(position_clicked, new_instruction);
                }
            }else if (optionId ==1){//option is add an instruction at certain step
                if(!new_instruction.equals("")) {
                    change_made = true;
                    new_emergency.add_instr(position_clicked, new_instruction);
                }
            }

        }

        //reset variables for next round,then display list view
        optionId=0;
        position_clicked=-1;
        new_instruction="";
        updateListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data == null) {
            Log.d(TAG, "result from requested code " + requestCode + " is null");
            return;
        } else {

            if (requestCode == 1) {

                optionId = data.getIntExtra(editEmergencyOption.EXTRA_OPTION,-1);
                if (optionId == 1 || optionId == 2) {
                    Log.d(TAG,"optionId is "+optionId);
                    Intent i = new Intent(this, getNewInstruction.class);
                    startActivityForResult(i, 2);
                }

            } else if (requestCode == 2) {

                 new_instruction= data.getStringExtra(getNewInstruction.EXTRA_INSTRUCTION);
                 Log.d(TAG,"new_instruction is "+new_instruction);
            }
        }
    }


    private void updateListView(){
        TipAdapter2 adapter = new TipAdapter2(this, new_emergency.getEmergency_instrs());
        list_instr.setAdapter(adapter);
    }
}
