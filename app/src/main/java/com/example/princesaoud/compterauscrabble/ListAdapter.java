package com.example.princesaoud.compterauscrabble;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by princesaoud on 4/3/18.
 */

public class ListAdapter extends BaseAdapter {

    private Integer[] players;
    private final Activity mContext;
    private final Integer[] img;
    public ListAdapter(Activity context, Integer[] players, Integer[] img) {
//        super(context, R.layout.list,null);
        this.mContext = context;
        this.players = players;
        this.img = img;
        Log.e("Constructor","listAdapter constractor");
    }

    public void updateScore(Integer score, int position, char sign){
        if(sign == '+'){
            this.players[position] += score;
        }
        if(sign == '-'){
            this.players[position] -= score;
        }
        notifyDataSetChanged();
    }

    public Integer[] getPlayers(){
        return this.players;
    }

    private String getName(int position){
        switch (position){
            case 0:
                return "Player 1";
            case 1:
                return "Player 2";
            case 2:
                return "Player 3";
            case 3:
                return "Player 4";
        }
        return null;
    }


    @Override
    public int getCount() {
        return players.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.e("view","into view");
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getLayoutInflater();
        if(view == null) {
            view = layoutInflater.inflate(R.layout.list, null,true);
//        View rowView = layoutInflater.inflate(R.layout.list, null, true);
        }
        ImageView icon = (ImageView) view.findViewById(R.id.iv_icon);
        TextView name = view.findViewById(R.id.tv_name);
        TextView score = view.findViewById(R.id.tv_score);
//        EditText editText  = view.findViewById(R.id.et_score);

        score.setText(players[i]+"");
        name.setText(getName(i));
        icon.setImageResource(img[i]);

        return view;
    }
}
