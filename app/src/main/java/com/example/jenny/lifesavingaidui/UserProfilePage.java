package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jenny on 19/03/2016.
 */
public class UserProfilePage extends Activity {

    Button viewProfileId,back;
    ImageButton add;
    ListView emergency_list;
    Profile userProfile;
    TextView userName;

    String[] messages = new String[2];


    private static final String TAG = "UserProfilePage ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile1);

        messages = getIntent().getStringArrayExtra("Message");


        //set up and display emergency list
        userProfile = UserProfile.get(this).getProfile();
        emergency_list = (ListView) findViewById(R.id.user_emergency_list);
        ArrayList<emergency> user_emergencies = UserProfile.get(this).getEmergencyList();
        emergencyAdapter adapter = new emergencyAdapter(this,user_emergencies);
        emergency_list.setAdapter(adapter);


        //display user name
        userName = (TextView) findViewById(R.id.user_profile);
        userName.setText(userProfile.getName());


        //set up back button
        back = (Button) findViewById(R.id.butonBack_user_profile);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write some code to save all the changes
                backAction();
            }});

        //set up view profile button
        viewProfileId = (Button) findViewById(R.id.view_user_profile);
        viewProfileId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewProfileAction();
            }});

        //set up add button
        add = (ImageButton) findViewById(R.id.imbt_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAction();
            }
        });

        emergency_list.setOnItemClickListener(new emergencyItemClick());
        emergency_list.setOnItemLongClickListener(new emergencyItemLongClick());
    }


    private class emergencyItemClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent i = new Intent(UserProfilePage.this, CreateNewEmergency.class);
            i.putExtra(CreateNewEmergency.EXTRA_EMERGENCY, position);
            startActivity(i);
        }
    }

    private class emergencyItemLongClick implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            //remove item from emergency list
            UserProfile.get(UserProfilePage.this).removeEmergencyItem(position);

            //reset adapter
            ArrayList<emergency> user_emergencies = UserProfile.get(UserProfilePage.this)
                                                               .getEmergencyList();
            emergencyAdapter adapter = new emergencyAdapter(UserProfilePage.this,user_emergencies);
            emergency_list.setAdapter(adapter);
            return true;
        }
    }

    private void backAction(){
        //do something to upload profile to server before finish
        UserProfile.upLoadUserProfile(messages);
        finish();
    }
    private void viewProfileAction(){

        Intent i = new Intent(this, EditUserProfile.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<emergency> user_emergencies = UserProfile.get(this).getEmergencyList();
        emergencyAdapter adapter = new emergencyAdapter(this,user_emergencies);
        emergency_list.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if(requestCode ==1){
                if(data == null){
                    Log.d(TAG,"result from requested code "+requestCode+" is null");
                    return;
                }else {

                     int optionId = data.getIntExtra(addOption.EXTRA_OPTION, -1);
                     identifyAddOption(optionId);
                }
            }else{
                Log.d(TAG,"result from requested code is not identified");
            }

    }


    private void identifyAddOption(int optionId){

            if(optionId==-1){
                Log.d(TAG,"optionId is -1, this should not happen");
            } else if(optionId == 0){}//option is cancel, do nothing
            else if (optionId == 1){// option is pick from tip bank

                Intent i = new Intent(this, SavingTipBank.class );
                startActivity(i);

            }else if (optionId==2){//option is create user's own emergency

                Intent i = new Intent(this,CreateNewEmergency.class);

                //pass in -100 to tell CreateNewEmergency.class creates a
                //new emergency
                i.putExtra(CreateNewEmergency.EXTRA_EMERGENCY,-100);
                startActivity(i);

            }else Log.d(TAG,"option reaches the end, this should not happen");

    }

    private void addAction(){
        Intent i = new Intent(this,addOption.class);
        startActivityForResult(i,1);

    }

}

