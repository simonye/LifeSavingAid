package com.example.jenny.lifesavingaidui;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LifeSavingAidMain extends AppCompatActivity {

    ImageButton me,friends,tip_bank;
    TextView log_out,change_password;
    Profile user;
    String[] messages = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_saving_aid_main);

        messages = getIntent().getStringArrayExtra("Message");

        user = UserProfile.get(this).getProfile();

        //underline the "Log Out" text in .xml file
        log_out= (TextView) findViewById(R.id.Log_Out);
        log_out.setPaintFlags(log_out.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //underline the "Change Password" text in .xml file
        change_password = (TextView) findViewById(R.id.Change_password);
        change_password.setPaintFlags(change_password.getPaintFlags()|Paint.UNDERLINE_TEXT_FLAG);


        //reference all the buttons
        me = (ImageButton) findViewById(R.id.imgbt_me);
        friends = (ImageButton) findViewById(R.id.imgbt_friends);
        tip_bank = (ImageButton) findViewById(R.id.imgbt_tips);

       tip_bank.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                Intent i= new Intent(LifeSavingAidMain.this,SavingTipBank.class);
               startActivity(i);
           }
       });

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfile.get(LifeSavingAidMain.this).downLoadUserProfile(messages);
                Intent i=new Intent(LifeSavingAidMain.this,UserProfilePage.class);
                i.putExtra("Message", messages);
                startActivity(i);
            }
        });
    }
}
