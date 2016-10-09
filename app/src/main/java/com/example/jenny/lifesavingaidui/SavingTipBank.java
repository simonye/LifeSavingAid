package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Jenny on 17/03/2016.
 */
public class SavingTipBank extends Activity {


    String[] first_aid_tips= {"Allergies","Asthma Attack","Bleeding","Broken Bone","Burns",
                              "Chocking","Diabetic Emergency","Head Injury","Heat Stroke",
                              "Hypothermia","Meningitis","Poisoning","Seizure","Stroke",
                              "Strains and Sprians","Unconscious"};
    ListView listView;
    ArrayAdapter<String> adapter;
    Button back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saving_tip_bank);

        back = (Button) findViewById(R.id.butonBack2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.listView2);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,first_aid_tips);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ItemListClick());
        listView.setOnItemLongClickListener(new ItemListLongClick());
    }

    private class ItemListClick implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent i = new Intent(SavingTipBank.this, ShowContent.class);
            i.putExtra(ShowContent.EXTRA_POSITION_ID, position);
            startActivity(i);
        }
    }

    private class ItemListLongClick implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            addEmergencyItem(position);
            return true;
        }
    }

    private void addEmergencyItem(int position){

        String[] content=null;
        String content_title="";
        int content_length=0;

        switch (position){

            case 0:
                content_title= TipStrings.allergies;
                content = TipStrings.allergies_instrs;
                content_length=TipStrings.allergies_length;
                break;

            case 1:
                content_title= TipStrings.asthma_attack;
                content = TipStrings.asthma_attack_instrs;
                content_length=TipStrings.asthma_attack_length;
                break;

            case 2:
                content_title= TipStrings.bleeding;
                content=TipStrings.bleeding_instrs;
                content_length=TipStrings.bleeding_length;
                break;

            case 3:
                content_title= TipStrings.broken_bone;
                content=TipStrings.broken_bone_instrs;
                content_length=TipStrings.broken_bone_length;
                break;

            case 4:
                content_title= TipStrings.burns;
                content=TipStrings.burns_instrs;
                content_length=TipStrings.burns_length;
                break;

            case 5:
                content_title= TipStrings.chocking;
                content=TipStrings.chocking_instrs;
                content_length=TipStrings.chocking_length;
                break;

            case 6:
                content_title= TipStrings.diabetic_emergency;
                content=TipStrings.diabetic_emergency_instrs;
                content_length=TipStrings.diabetic_emergency_length;
                break;

            case 7:
                content_title= TipStrings.head_injury;
                content=TipStrings.head_injury_instrs;
                content_length=TipStrings.head_injury_length;
                break;

            case 8:
                content_title= TipStrings.heat_stroke;
                content=TipStrings.heat_stoke_instrs;
                content_length=TipStrings.heat_stoke_length;
                break;

            case 9:
                content_title= TipStrings.hypothermia;
                content=TipStrings.hypothermia_instrs;
                content_length=TipStrings.hypothermia_length;
                break;

            case 10:
                content_title= TipStrings.meningitis;
                content=TipStrings.meningitis_instrs;
                content_length=TipStrings.meningitis_length;
                break;

            case 11:
                content_title= TipStrings.poisoning;
                content=TipStrings.poisoning_instrs;
                content_length=TipStrings.poisoning_length;
                break;

            case 12:
                content_title= TipStrings.seizure;
                content=TipStrings.seizure_instrs;
                content_length=TipStrings.seizure_length;
                break;


            case 13:
                content_title= TipStrings.stroke;
                content=TipStrings.stroke_instrs;
                content_length=TipStrings.stoke_length;
                break;


            case 14:
                content_title= TipStrings.strains_and_sprains;
                content=TipStrings.strains_and_sprains_instrs;
                content_length=TipStrings.strains_and_sprains_length;
                break;

            case 15:
                content_title= TipStrings.unconscious;
                content=TipStrings.unconscious_instrs;
                content_length=TipStrings.unconscious_length;
                break;
        }

        emergency em = new emergency(content_title);
        em.pickedFromTipBank(content,content_length);
        UserProfile.get(this).addEmergencyItem(em);
    }
}
