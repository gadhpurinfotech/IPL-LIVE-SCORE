package com.gadhpurinfotech.cricketlivescore.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gadhpurinfotech.cricketlivescore.App.PVADMob;
import com.rjn.cricketlivescore.R;
import com.squareup.picasso.Picasso;

import static android.view.View.GONE;


public class ListAdapter_recent extends BaseAdapter {

    recent main;

    private PVADMob pvadMob;

    ListAdapter_recent(recent main) {

        this.main = main;
    }


    @Override
    public int getCount() {
        return main.live_data_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {
        TextView srs, sname, batteamscore, overs, runs, status, mnum, team1, team2, type, bowlteamscore, scnd_sname;
        TextView vcity, vcountry, time, oversright, time_num;
        RelativeLayout bat_score_layout, bowl_score_layout, vcity_vcountry_layout, bg_listview;
        ImageView flag1, flag2;
        String TW, decisn, team1_fName, team2_fName, team1_id, team2_id, flag_url1, flag_url2;
        String mchState, batteamid, bowlteamid, batteamscore_str, bowlteamscore_str;
        String matchId, datapath, srs_str;
        private final int[] bgColors = new int[]{R.color.match_name_display_color, R.color.colorPrimary};

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.singlelist, null);
            holder.batteamscore = (TextView) convertView.findViewById(R.id.bateamscore);
            holder.srs = (TextView) convertView.findViewById(R.id.srs);
            holder.bg_listview = (RelativeLayout) convertView.findViewById(R.id.layout1);

            holder.sname = (TextView) convertView.findViewById(R.id.sname);

            holder.overs = (TextView) convertView.findViewById(R.id.overs);
            // holder.time_num = (TextView) convertView.findViewById(R.id.time_num);
            //   holder.runs = (TextView) convertView.findViewById(R.id.runs);
            holder.status = (TextView) convertView.findViewById(R.id.status);
            holder.mnum = (TextView) convertView.findViewById(R.id.mnum);
            holder.team1 = (TextView) convertView.findViewById(R.id.team1);
            holder.team2 = (TextView) convertView.findViewById(R.id.team2);
            holder.flag1 = (ImageView) convertView.findViewById(R.id.flag1);
            holder.flag2 = (ImageView) convertView.findViewById(R.id.flag2);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.vcity = (TextView) convertView.findViewById(R.id.grnd);
            holder.vcountry = (TextView) convertView.findViewById(R.id.country);
            holder.scnd_sname = (TextView) convertView.findViewById(R.id.scnd_sname);
            holder.bowlteamscore = (TextView) convertView.findViewById(R.id.bowlteamscore);
            holder.batteamscore = (TextView) convertView.findViewById(R.id.bateamscore);
            //   holder.oversright = (TextView) convertView.findViewById(R.id.oversright);
            holder.bowl_score_layout = (RelativeLayout) convertView.findViewById(R.id.layout3);
            holder.bat_score_layout = (RelativeLayout) convertView.findViewById(R.id.layout2);
            holder.vcity_vcountry_layout = (RelativeLayout) convertView.findViewById(R.id.layout4);
            convertView.setTag(holder);

          /*  pvadMob = new PVADMob(main.getActivity(), new PVADMob.InterAdListener() {
                @Override
                public void onClick(int position, String type) {

                }
            });
            pvadMob.loadInter();*/
        } else {
            holder = (ViewHolderItem) convertView.getTag();


        }
        holder.TW = this.main.live_data_list.get(position).TW;

        holder.decisn = this.main.live_data_list.get(position).decisn;
        holder.matchId = this.main.live_data_list.get(position).matchId;
        holder.datapath = this.main.live_data_list.get(position).datapath;
        holder.team1_fName = this.main.live_data_list.get(position).team1_fName;
        holder.team1_id = this.main.live_data_list.get(position).team1_id;
        holder.team2_id = this.main.live_data_list.get(position).team2_id;
        holder.batteamid = this.main.live_data_list.get(position).batteamid;
        holder.bowlteamid = this.main.live_data_list.get(position).bowlteamid;
        holder.team2_fName = this.main.live_data_list.get(position).team2_fName;
        holder.mchState = this.main.live_data_list.get(position).mchState;
        holder.batteamscore_str = this.main.live_data_list.get(position).batteamscore;
        holder.bowlteamscore_str = this.main.live_data_list.get(position).bowlteamscore;
        holder.flag_url1 = "http://i.cricketcb.com/cbzandroid/2.0/flags/team_" + holder.team1_id + ".png";
        holder.flag_url2 = "http://i.cricketcb.com/cbzandroid/2.0/flags/team_" + holder.team2_id + ".png";


        Picasso.with(main.getActivity()).load(holder.flag_url1).into(holder.flag1);
        Picasso.with(main.getActivity()).load(holder.flag_url2).into(holder.flag2);


        holder.srs_str = this.main.live_data_list.get(position).srs;
        holder.overs.setText(this.main.live_data_list.get(position).overs);
        holder.status.setText(this.main.live_data_list.get(position).status);
        holder.mnum.setText(this.main.live_data_list.get(position).mnum);
        holder.batteamscore.setText(this.main.live_data_list.get(position).batteamscore);
        holder.team1.setText(this.main.live_data_list.get(position).team1_sName);
        holder.team2.setText(this.main.live_data_list.get(position).team2_sName);
        holder.type.setText(this.main.live_data_list.get(position).type);
        holder.batteamscore.setText(this.main.live_data_list.get(position).batteamscore);
        holder.bowlteamscore.setText(this.main.live_data_list.get(position).bowlteamscore);
        holder.vcity.setText(this.main.live_data_list.get(position).vcity);
        holder.vcountry.setText(this.main.live_data_list.get(position).vcountry);
        holder.time.setText(this.main.live_data_list.get(position).stTme);
        int colorPosition = position % holder.bgColors.length;
        //holder.bg_listview.setBackgroundResource(holder.bgColors[colorPosition]);
        holder.bowlteamscore.setTextColor(position % 2 == 0 ? Color.parseColor("#91BF17") : Color.parseColor("#3762ff"));
        holder.batteamscore.setTextColor(position % 2 == 0 ? Color.parseColor("#91BF17") : Color.parseColor("#3762ff"));

        // String[] split_batteam_score,split_bowlteam_score;
/*        String[] split_batteam_score = holder.batteamscore_str.split("&");
        String[] split_bowlteam_score = holder.bowlteamscore_str.split("&");*/
        holder.srs.setText(this.main.live_data_list.get(position).srs);
        holder.srs.setSelected(true);

        /*f (holder.srs_str.length() >= 20) {

            holder.srs.setText(this.main.live_data_list.get(position).srs.substring(0,20)+"...");
        } else {

            holder.srs.setText(this.main.live_data_list.get(position).srs);

        }*/
        if (holder.team1_id.toString().equals(holder.batteamid)) {

            holder.sname.setText(this.main.live_data_list.get(position).team1_sName + ": ");
            holder.scnd_sname.setText(this.main.live_data_list.get(position).team2_sName + ": ");

        } else if (holder.team1_id.toString().equals(holder.bowlteamid)) {

            holder.sname.setText(this.main.live_data_list.get(position).team2_sName + ": ");
            holder.scnd_sname.setText(this.main.live_data_list.get(position).team1_sName + ": ");

        } else if (holder.team2_id.toString().equals(holder.batteamid)) {

            holder.sname.setText(this.main.live_data_list.get(position).team2_sName + ": ");
            holder.scnd_sname.setText(this.main.live_data_list.get(position).team1_sName + ": ");

        } else if (holder.team2_id.toString().equals(holder.bowlteamid)) {

            holder.sname.setText(this.main.live_data_list.get(position).team1_sName + ": ");
            holder.scnd_sname.setText(this.main.live_data_list.get(position).team2_sName + ": ");

        } else {

            holder.sname.setText("");
            holder.scnd_sname.setText("");

        }
        if (holder.mchState.toString().equals("inprogress") || holder.mchState.toString().equals("stump") || holder.mchState.toString().equals("tea")) {
            holder.time.setVisibility(GONE);
            // holder.time_num.setVisibility(GONE);
            holder.vcity.setVisibility(GONE);
            holder.vcountry.setVisibility(GONE);
            holder.bowl_score_layout.setVisibility(GONE);
            holder.overs.setVisibility(View.VISIBLE);
            holder.bat_score_layout.setVisibility(View.VISIBLE);
        }
        if (holder.mchState.toString().equals("preview")) {
            holder.overs.setVisibility(View.GONE);
            holder.mnum.setVisibility(View.VISIBLE);
            holder.time.setVisibility(View.VISIBLE);
            holder.vcity.setVisibility(View.VISIBLE);
            holder.vcountry.setVisibility(View.VISIBLE);
            holder.bowl_score_layout.setVisibility(GONE);
            holder.bowl_score_layout.setVisibility(View.GONE);
            holder.bat_score_layout.setVisibility(View.GONE);

        }

        if (holder.mchState.toString().equals("complete")) {

            holder.overs.setVisibility(GONE);
            //   holder.oversright.setVisibility(GONE);
            holder.time.setVisibility(GONE);
            // holder.time_num.setVisibility(GONE);
            holder.vcity.setVisibility(GONE);
            holder.vcountry.setVisibility(GONE);
            holder.bowl_score_layout.setVisibility(View.VISIBLE);
            holder.bat_score_layout.setVisibility(View.VISIBLE);


        }
        if (holder.mchState.toString().equals("delay")) {
            holder.overs.setVisibility(GONE);
            //  holder.oversright.setVisibility(GONE);
            holder.time.setVisibility(GONE);
            holder.mnum.setVisibility(GONE);
            holder.status.setVisibility(View.VISIBLE);
            holder.vcity.setVisibility(GONE);
            holder.vcountry.setVisibility(GONE);
            holder.bowl_score_layout.setVisibility(View.GONE);
            holder.bat_score_layout.setVisibility(View.GONE);
        }
        final String st = this.main.live_data_list.get(position).status;
        final String mDesc = this.main.live_data_list.get(position).mchDesc;
        final ViewHolderItem finalHolder = holder;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opn = new Intent(main.getActivity(), full_scoreboard.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                opn.putExtra("datapath", finalHolder.datapath);
                opn.putExtra("status", st);
                opn.putExtra("matchDesc", mDesc);
                main.startActivity(opn);

                // full_scorboard.checkmatchid(finalHolder.datapath);
            }
        });


        return convertView;
    }

}
