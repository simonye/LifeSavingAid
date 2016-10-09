package com.example.jenny.lifesavingaidui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Jenny on 17/03/2016.
 */
public class ShowContent extends Activity {

    //variables for determine which position is clicked on tip list
    int position;
    public static final String EXTRA_POSITION_ID= "com.example.jenny.lifesavingaidui.postion_id";

    //debugging purpose
    private static final String TAG="ShowContent";

    Button back,previous,next,call;
    TextView first_aid_title;
    ListView first_aid_insctructions;

    //variables for what to display
    String[] content;
    String content_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_tips);

        first_aid_title = (TextView) findViewById(R.id.text_first_aid);
        first_aid_insctructions = (ListView) findViewById(R.id.listView);

        //get which position is clicked from SavingTipBank activity
        position= ShowContent.this.getIntent().getIntExtra(EXTRA_POSITION_ID,-1);

        back= (Button) findViewById(R.id.butonBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        previous = (Button) findViewById(R.id.buttonPrevious);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position>0) position--;
                displayContent(position);
            }
        });

        next = (Button) findViewById(R.id.buttonNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position<15) position++;
                displayContent(position);
            }
        });


        call= (Button) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent call= new Intent(Intent.ACTION_CALL, Uri.parse("tel:519-588-9460"));
                startActivity(call);
            }
        });

        if(position==-1) Log.d(TAG,"position takes the default value");
        else displayContent(position);
    }

    private void displayContent(int position){

            content=null;

            switch (position){

                case 0:
                    content_title= TipStrings.allergies;
                    content = TipStrings.allergies_instrs;
                    break;

                case 1:
                    content_title= TipStrings.asthma_attack;
                    content = TipStrings.asthma_attack_instrs;
                    break;

                case 2:
                    content_title= TipStrings.bleeding;
                    content=TipStrings.bleeding_instrs;
                    break;

                case 3:
                    content_title= TipStrings.broken_bone;
                    content=TipStrings.broken_bone_instrs;
                    break;

                case 4:
                    content_title= TipStrings.burns;
                    content=TipStrings.burns_instrs;
                    break;

                case 5:
                    content_title= TipStrings.chocking;
                    content=TipStrings.chocking_instrs;
                    break;

                case 6:
                    content_title= TipStrings.diabetic_emergency;
                    content=TipStrings.diabetic_emergency_instrs;
                    break;

                case 7:
                    content_title= TipStrings.head_injury;
                    content=TipStrings.head_injury_instrs;
                    break;

                case 8:
                    content_title= TipStrings.heat_stroke;
                    content=TipStrings.heat_stoke_instrs;
                    break;

                case 9:
                    content_title= TipStrings.hypothermia;
                    content=TipStrings.hypothermia_instrs;
                    break;

                case 10:
                    content_title= TipStrings.meningitis;
                    content=TipStrings.meningitis_instrs;
                    break;

                case 11:
                    content_title= TipStrings.poisoning;
                    content=TipStrings.poisoning_instrs;
                    break;

                case 12:
                    content_title= TipStrings.seizure;
                    content=TipStrings.seizure_instrs;
                    break;


                case 13:
                    content_title= TipStrings.stroke;
                    content=TipStrings.stroke_instrs;
                    break;


                case 14:
                    content_title= TipStrings.strains_and_sprains;
                    content=TipStrings.strains_and_sprains_instrs;
                    break;

                case 15:
                    content_title= TipStrings.unconscious;
                    content=TipStrings.unconscious_instrs;
                    break;
            }

            first_aid_title.setText(content_title);
            first_aid_insctructions.setAdapter(new TipAdapter(this,content));
    }

}
