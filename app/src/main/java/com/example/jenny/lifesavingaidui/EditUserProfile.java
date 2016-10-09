package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jenny on 20/03/2016.
 */
public class EditUserProfile extends Activity {

    EditText name,height,weight,blood_type,birthDay,healthHistory,SOS_message,location,phone;
    Profile user;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        user= UserProfile.get(this).getProfile();

        name = (EditText) findViewById(R.id.userprofile_name);
        name.setText(user.getName(), TextView.BufferType.EDITABLE);

        height = (EditText) findViewById(R.id.userprofile_height);
        height.setText(user.getHeight(), TextView.BufferType.EDITABLE);

        weight = (EditText) findViewById(R.id.userprofile_weight);
        weight.setText(user.getWeight(), TextView.BufferType.EDITABLE);

        blood_type = (EditText) findViewById(R.id.userprofile_bloodtype);
        blood_type.setText(user.getBlood_type(), TextView.BufferType.EDITABLE);

        birthDay = (EditText) findViewById(R.id.userprofile_birthDate);
        birthDay.setText(user.getBirthDay(), TextView.BufferType.EDITABLE);

        healthHistory = (EditText) findViewById(R.id.userprofile_health);
        healthHistory.setText(user.getHealthHistory(), TextView.BufferType.EDITABLE);

        SOS_message = (EditText) findViewById(R.id.userprofile_sos);
        SOS_message.setText(user.getSOS_message(), TextView.BufferType.EDITABLE);

        location = (EditText) findViewById(R.id.userprofile_location);
        location.setText(user.getLocation(), TextView.BufferType.EDITABLE);

        phone= (EditText) findViewById(R.id.userprofile_phone);
        phone.setText(user.getPhone(), TextView.BufferType.EDITABLE);

        back = (Button) findViewById(R.id.userprofile_buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfile.updateUserInfo( name.getText().toString(),
                                            weight.getText().toString(),
                                            height.getText().toString(),
                                            blood_type.getText().toString(),
                                            healthHistory.getText().toString(),
                                            SOS_message.getText().toString(),
                                            location.getText().toString(),
                                            phone.getText().toString(),
                                            birthDay.getText().toString());
                finish();
            }});
    }
}
