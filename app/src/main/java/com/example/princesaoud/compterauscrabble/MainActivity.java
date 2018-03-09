package com.example.princesaoud.compterauscrabble;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    protected Integer[] player = {0,0,0,0};
    protected ListView listView;
    protected Integer[] img = {R.drawable.man1,R.drawable.man2,R.drawable.man3,R.drawable.man4};

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ListAdapter listAdapter  = new ListAdapter(this, player, img);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        listView = (ListView) findViewById(R.id.listview);


            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i){
                        case 0:
                            dialogue(0);
//                            Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_LONG).show();
                            break;

                        case 1:
                            dialogue(1);
//                            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();
                            break;

                        case 2:
                            dialogue(2);
//                            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();
                            break;

                        case 3:
                            dialogue(3);
//                            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            });


    }

    @Override
    protected void onStop() {
        super.onStop();
        final Integer[] total = listAdapter.getPlayers();
        sharedPreferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putInt("player1",total[0]);
        editor.putInt("player2",total[1]);
        editor.putInt("player3",total[2]);
        editor.putInt("player4",total[3]);
        Log.e("onStop", Arrays.toString(total));
        editor.apply();

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_main);
        Log.e("onRestart","on est dans restart ooooo");
        sharedPreferences = getApplicationContext().getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        player[0] = sharedPreferences.getInt("player1",0);
        player[1] = sharedPreferences.getInt("player2",0);
        player[2] = sharedPreferences.getInt("player3",0);
        player[3] = sharedPreferences.getInt("player4",0);


        listAdapter = new ListAdapter(this, player, img);

        listView = (ListView) findViewById(R.id.listview);


        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        dialogue(0);
//                            Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_LONG).show();
                        break;

                    case 1:
                        dialogue(1);
//                            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        dialogue(2);
//                            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();
                        break;

                    case 3:
                        dialogue(3);
//                            Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    private void dialogue(final Integer position){

        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Update Score");

        final EditText dialogEt = (EditText) dialog.findViewById(R.id.dialogEt);
        Button add = (Button) dialog.findViewById(R.id.dialogBtn);
        Button rem = (Button) dialog.findViewById(R.id.dialogRem);
        dialog.show();

        ImageView img = (ImageView) dialog.findViewById(R.id.dialogImg);
        if(position==0) {
            img.setImageResource(R.drawable.man1);
        }
        if (position==1) {
            img.setImageResource(R.drawable.man2);
        }
        if(position==2) {
            img.setImageResource(R.drawable.man3);
        }
        if (position==3) {
            img.setImageResource(R.drawable.man4);
        }

        rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (dialogEt == null) {
                        Toast.makeText(getApplicationContext(), "Fill the input", Toast.LENGTH_LONG).show();
                    }
                    assert dialogEt != null;
                    int score = Integer.valueOf(dialogEt.getText().toString());

                    listAdapter.updateScore(score, position, '-');
                    dialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (dialogEt == null) {
                        Toast.makeText(getApplicationContext(), "Fill the input", Toast.LENGTH_LONG).show();
                    }
                    assert dialogEt != null;
                    int score = Integer.valueOf(dialogEt.getText().toString());
                    listAdapter.updateScore(score, position, '+');

                    dialog.dismiss();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
