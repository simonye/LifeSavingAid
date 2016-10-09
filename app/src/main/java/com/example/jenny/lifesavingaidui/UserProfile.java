package com.example.jenny.lifesavingaidui;

import android.content.Context;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jenny on 20/03/2016.
 */


// this class is a global structure
public class UserProfile {

    private static UserProfile myUserProfile;
    private Context myAppContext;
    private static Profile user;

    private static ArrayList<String> friends_list;
    private static Profile friend;




    private UserProfile(Context context){
        myAppContext=context;
        user = new Profile();
    }
    public static UserProfile get(Context c){
        if (myUserProfile==null){
            myUserProfile = new UserProfile(c.getApplicationContext());
        }
        return myUserProfile;
    }


    ////code relate to user-----------------------------------
    public Profile getProfile(){return user;}
    public ArrayList<emergency> getEmergencyList(){return user.getEmergencies();}
    public static void updateUserInfo(String name,String weight,String height,String bloodtype,
                               String healthHistory,String SOS_message,String location,
                               String phone,String birthDay){

        user.setName(name);
        user.setWeight(weight);
        user.setHeight(height);
        user.setBlood_type(bloodtype);
        user.setHealthHistory(healthHistory);
        user.setSOS_message(SOS_message);
        user.setLocation(location);
        user.setPhone(phone);
        user.setBirthDay(birthDay);
    }

    public static void addEmergencyItem(emergency em){
          user.addEmergencyItem(em);
    }

    public static void setEmergencyItem(int position, emergency em){
         user.setEmergencyItem(position, em);
    }

    public static emergency getEmergencyItem(int position){
       return user.getEmergencyItem(position);
    }
    public static void removeEmergencyItem(int position){
        user.removeEmergencyItem(position);
    }

    public static void upLoadUserProfile(String[] m){
        String email = m[0];
        String password = m[1];
        ContentValues params = new ContentValues();
        params.put("email", email);
        params.put("password", password);
        params.put("first_name", user.getName());
        params.put("cell", user.getPhone());
        params.put("height", user.getHeight());
        ServerRequest sr = new ServerRequest();
        JSONObject json = sr.getJSON("http://lsaserver-warriorlsa.rhcloud.com/edtprofile", params);
    }
    public static void downLoadUserProfile(String[] m){

        String email = m[0];
        String password = m[1];
        ContentValues params = new ContentValues();
        params.put("email", email);
        params.put("password", password);
        ServerRequest sr = new ServerRequest();
        JSONObject json = sr.getJSON("http://lsaserver-warriorlsa.rhcloud.com/getprofile",params);
        Log.v("here's json", String.valueOf(json));
        if(json != null){
            try{
                //String jsonstr = json.getString("response");

                user.setName(json.getString("first_name"));
                Log.v("Here is the json: ", String.valueOf(json));
                user.setPhone(json.getString("cell"));
                user.setHeight(json.getString("height"));


            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //---------------------------------------


    //code for friends-------------------------------------


    //-----------------------------------------------------
}
