package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;





public class login extends Activity {
    EditText email,password,res_email,code,newpass;
    Button login,cont,cont_code,cancel,cancel1,register,forpass;
    String emailtxt,passwordtxt,email_res_txt,code_txt,npass_txt;
    ContentValues params;
    SharedPreferences pref;
    Dialog reset;
    ServerRequest sr;

    String[]  message  = new String[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sr = new ServerRequest();

        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.loginbtn);
        register = (Button)findViewById(R.id.register);
        forpass = (Button)findViewById(R.id.forgotpass);

        pref = getSharedPreferences("AppPref", MODE_PRIVATE);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("Register Button Click:", " Intent not created");
                Intent regactivity = new Intent(login.this,Register.class);
                Log.v("Register Button Click:", " Intent created");
                startActivity(regactivity);
                Log.v("Start Activity:", " Finished");
                finish();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                params = new ContentValues();
                params.put("email", emailtxt);
                params.put("password", passwordtxt);
                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://lsaserver-warriorlsa.rhcloud.com/login",params);
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        if(json.getBoolean("res")){
                            String token = json.getString("token");
                            String grav = json.getString("grav");
                            SharedPreferences.Editor edit = pref.edit();
                            //Storing Data using SharedPreferences
                            edit.putString("token", token);
                            edit.putString("grav", grav);
                            edit.commit();

                            message[0] = emailtxt;
                            message[1] = passwordtxt;



                            Intent profactivity = new Intent(login.this,LifeSavingAidMain.class);
                            profactivity.putExtra("Message", message);

                            startActivity(profactivity);
                            finish();
                        }

                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        forpass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reset = new Dialog(login.this);
                reset.setTitle("Reset Password");
                reset.setContentView(R.layout.reset_pass_init);
                cont = (Button)reset.findViewById(R.id.resbtn);
                cancel = (Button)reset.findViewById(R.id.cancelbtn);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset.dismiss();
                    }
                });
                res_email = (EditText)reset.findViewById(R.id.email);

                cont.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        email_res_txt = res_email.getText().toString();

                        params = new ContentValues();
                        params.put("email", email_res_txt);

                        //  JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass", params);
                        JSONObject json = sr.getJSON("http://lsaserver-warriorlsa.rhcloud.com/api/resetpass", params);

                        if (json != null) {
                            try {
                                String jsonstr = json.getString("response");
                                if(json.getBoolean("res")){
                                    Log.e("JSON", jsonstr);
                                    Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();
                                    reset.setContentView(R.layout.reset_pass_code);
                                    cont_code = (Button)reset.findViewById(R.id.conbtn);
                                    code = (EditText)reset.findViewById(R.id.code);
                                    newpass = (EditText)reset.findViewById(R.id.npass);
                                    cancel1 = (Button)reset.findViewById(R.id.cancel);
                                    cancel1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            reset.dismiss();
                                        }
                                    });
                                    cont_code.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            code_txt = code.getText().toString();
                                            npass_txt = newpass.getText().toString();
                                            Log.e("Code",code_txt);
                                            Log.e("New pass",npass_txt);
                                            params = new ContentValues();
                                            params.put("email", email_res_txt);
                                            params.put("code", code_txt);
                                            params.put("newpass", npass_txt);

                                            JSONObject json = sr.getJSON("http://lsaserver-warriorlsa.rhcloud.com/api/resetpass/chg", params);
                                            //   JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass/chg", params);

                                            if (json != null) {
                                                try {

                                                    String jsonstr = json.getString("response");
                                                    if(json.getBoolean("res")){
                                                        reset.dismiss();
                                                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                                    }else{
                                                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }
                                    });
                                }else{

                                    Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });


                reset.show();
            }
        });
    }




}
