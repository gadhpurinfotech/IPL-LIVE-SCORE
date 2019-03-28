package com.rjn.livescore.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rjn.livescore.R;

/**
 * Created by sakhi on 3/27/2017.
 */
public class bowl_ining1_Adapter extends BaseAdapter {
    bat_bowl_ining_1 main;

    bowl_ining1_Adapter(bat_bowl_ining_1 main) {

        this.main = main;
    }


    @Override
    public int getCount() {
        return main.bowling_data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolderItem {
        TextView bowlename, o, w, wd, Nb, M,R;
        public TextView vcity, vcountry, time;
        public RelativeLayout bat_score_layout, bowl_score_layout, vcity_vcountry_layout;
        public ImageView flag1, flag2;
        public String TW, decisn, team1_fName, team2_fName, team1_id, team2_id, flag_url1, flag_url2;
        public String mchState, batteamid, bowlteamid, batteamscore_str, bowlteamscore_str;
        String matchId, datapath;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        bowl_ining1_Adapter.ViewHolderItem holder = new bowl_ining1_Adapter.ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bowling_list_cell, null);
            holder.bowlename = (TextView) convertView.findViewById(R.id.bowlename);
            holder.o = (TextView) convertView.findViewById(R.id.O);
            holder.M = (TextView) convertView.findViewById(R.id.M);
            holder.w = (TextView) convertView.findViewById(R.id.W);
            holder.wd = (TextView) convertView.findViewById(R.id.wd);
            holder.Nb = (TextView) convertView.findViewById(R.id.Nb);
            holder.R = (TextView) convertView.findViewById(R.id.R);
            convertView.setTag(holder);
        } else {
            holder = (bowl_ining1_Adapter.ViewHolderItem) convertView.getTag();
        }
      /*  holder.TW = this.main.live_data_list.get(position).TW;
        holder.decisn = this.main.live_data_list.get(position).decisn;
        holder.matchId = this.main.live_data_list.get(position).matchId;
        holder.datapath = this.main.live_data_list.get(position).datapath;
        holder.team1_fName = this.main.live_data_list.get(position).team1_fName;

        holder.batteamid = this.main.live_data_list.get(position).batteamid;
        holder.bowlteamid = this.main.live_data_list.get(position).bowlteamid;
        holder.team2_fName = this.main.live_data_list.get(position).team2_fName;
        holder.mchState = this.main.live_data_list.get(position).mchState;
        holder.batteamscore_str = this.main.live_data_list.get(position).batteamscore;
        holder.bowlteamscore_str = this.main.live_data_list.get(position).bowlteamscore;*/
        //holder.team1_id = this.main.batsmen_data_list.get(position).batteamid;
        //holder.team2_id = this.main.batsmen_data_list.get(position).team2_id;

        holder.bowlename.setText(this.main.bowling_data_list.get(position).bowlerId);
        holder.o.setText(this.main.bowling_data_list.get(position).bowler_over);
        holder.M.setText(this.main.bowling_data_list.get(position).maiden);
        holder.R.setText(this.main.bowling_data_list.get(position).run);
        holder.w.setText(this.main.bowling_data_list.get(position).bowler_wicket);
        holder.Nb.setText(this.main.bowling_data_list.get(position).noball);
        holder.wd.setText(this.main.bowling_data_list.get(position).wideball);




        return convertView;
    }
}