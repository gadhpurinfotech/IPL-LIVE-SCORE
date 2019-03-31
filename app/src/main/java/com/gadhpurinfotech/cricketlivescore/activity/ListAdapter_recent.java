package com.gadhpurinfotech.cricketlivescore.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gadhpurinfotech.cricketlivescore.App.PVADMob;
import com.gadhpurinfotech.cricketlivescore.R;
import com.squareup.picasso.Picasso;


public class ListAdapter_recent extends BaseAdapter {

    recent main;

    private PVADMob pvadMob;

    ListAdapter_recent(recent main) {

        this.main = main;
        Log.e("main", main + "");
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

    public static class ViewHolderItem {
        TextView srs, batteamscore, overs, bowl_over, status, content_full_about, team1, team2, bowlteamscore, content_full_livestatus;
        TextView vcity, time;
        TextView txt_score2,txt_score1;
        //        RelativeLayout  bat_score_layout, bowl_score_layout;
        ImageView flag1, flag2;
        String TW, decisn, team1_fName, team2_fName, team1_id, team2_id, flag_url1, flag_url2, srs_str;
        String mchState, batteamid, bowlteamid, batteamscore_str, bowlteamscore_str;
        String matchId, datapath;
        private final int[] bgColors = new int[]{R.color.match_name_display_color, R.color.colorPrimary};
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListAdapter.ViewHolderItem holder = new ListAdapter.ViewHolderItem();

        final String st = this.main.live_data_list.get(position).status;
        final String mDesc = this.main.live_data_list.get(position).mchDesc;
        final ListAdapter.ViewHolderItem finalHolder = holder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.singlelist, null);
            holder.batteamscore = convertView.findViewById(R.id.bateamscore);
            holder.srs = convertView.findViewById(R.id.srs);
//            holder.bg_listview = (RelativeLayout) convertView.findViewById(R.id.layout1);

//            holder.sname = convertView.findViewById(R.id.sname);

            holder.overs = convertView.findViewById(R.id.overs);
            holder.bowl_over = convertView.findViewById(R.id.bowl_over);
            // holder.time_num =  convertView.findViewById(R.id.time_num);
//            holder.runs = convertView.findViewById(R.id.runs);
            holder.status = convertView.findViewById(R.id.status);
//            holder.srs_dot = convertView.findViewById(R.id.srs_dot);

            holder.content_full_about = convertView.findViewById(R.id.content_full_about);
            holder.team1 = convertView.findViewById(R.id.team1);
            holder.team2 = convertView.findViewById(R.id.team2);
            holder.flag1 = (ImageView) convertView.findViewById(R.id.flag1);
            holder.flag2 = (ImageView) convertView.findViewById(R.id.flag2);
//            holder.type = convertView.findViewById(R.id.type);
//            holder.time = convertView.findViewById(R.id.time);
            holder.vcity = (TextView) convertView.findViewById(R.id.grnd);
//            holder.vcountry = (TextView) convertView.findViewById(R.id.country);
//            holder.scnd_sname = convertView.findViewById(R.id.scnd_sname);
            holder.bowlteamscore = convertView.findViewById(R.id.bowlteamscore);
            holder.batteamscore = convertView.findViewById(R.id.bateamscore);
            //   holder.oversright =  convertView.findViewById(R.id.oversright);
//            holder.bowl_score_layout = convertView.findViewById(R.id.layout3);
//            holder.bat_score_layout = convertView.findViewById(R.id.layout2);
            holder.content_full_livestatus = convertView.findViewById(R.id.content_full_livestatus);
            holder.txt_score1=convertView.findViewById(R.id.txt_score1);
            holder.txt_score2=convertView.findViewById(R.id.txt_score2);
            convertView.setTag(holder);

            final ListAdapter.ViewHolderItem finalHolder1 = holder;
            /*pvadMob = new PVADMob(main.getActivity(), new PVADMob.InterAdListener() {
                @Override
                public void onClick(int position, String type) {
                    final String st = main.live_data_list.get(position).status;
                    final String mDesc = main.live_data_list.get(position).mchDesc;
                    Intent opn = new Intent(main.getActivity(), full_scoreboard.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    opn.putExtra("datapath", finalHolder1.datapath);
                    opn.putExtra("status", st);
                    opn.putExtra("matchDesc", mDesc);
                    main.startActivity(opn);
                    main.getActivity().finish();
                }
            });*/
            //pvadMob.loadInter();
        } else {
            holder = (ListAdapter.ViewHolderItem) convertView.getTag();


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
        if (this.main.live_data_list.get(position).overs != null && this.main.live_data_list.get(position).overs != "") {

            holder.overs.setText(this.main.live_data_list.get(position).overs + " Over");
            holder.bowl_over.setText(this.main.live_data_list.get(position).bowler_over + " Over");
        } else {
            holder.txt_score1.setVisibility(View.GONE);
            holder.txt_score2.setVisibility(View.GONE);
        }
        holder.status.setText(this.main.live_data_list.get(position).status);
//        holder.content_full_about.setText(this.main.live_data_list.get(position).mnum);
        holder.content_full_about.setText(this.main.live_data_list.get(position).type + " - " + this.main.live_data_list.get(position).mnum);
        holder.batteamscore.setText(this.main.live_data_list.get(position).batteamscore);
        holder.team1.setText(this.main.live_data_list.get(position).team1_sName);
        holder.team2.setText(this.main.live_data_list.get(position).team2_sName);
        holder.team1.setSelected(true);
        holder.team2.setSelected(true);
//        holder.type.setText(this.main.live_data_list.get(position).type);
        holder.batteamscore.setText(this.main.live_data_list.get(position).batteamscore);
        holder.bowlteamscore.setText(this.main.live_data_list.get(position).bowlteamscore);
        String bScore = this.main.live_data_list.get(position).batteamscore;
        String aScore = this.main.live_data_list.get(position).bowlteamscore;
//        Log.e(aScore+" : score ",bScore);
//        Log.e(this.main.live_data_list.get(position).team2_sName.toString()+" bat-score : ",this.main.live_data_list.get(position).batteamscore.toString());
        holder.vcity.setText(this.main.live_data_list.get(position).vcity);
//        holder.vcountry.setText(this.main.live_data_list.get(position).vcountry);
//        holder.content_full_livestatus
//        holder.time.setText(this.main.live_data_list.get(position).stTme);
//        holder.time.setText(this.main.live_data_list.get(position).startdt);
//        Log.e("stTime",this.main.live_data_list.get(position).stTme);
//        Log.e("startdt",this.main.live_data_list.get(position).startdt);
//        holder.content_full_livestatus.setText(this.main.live_data_list.get(position).mchState);
//        int colorPosition = position % holder.bgColors.length;
        //holder.bg_listview.setBackgroundResource(holder.bgColors[colorPosition]);
        //holder.bowlteamscore.setTextColor(position % 2 == 0 ? Color.parseColor("#91BF17") : Color.parseColor("#3762ff"));
        //holder.batteamscore.setTextColor(position % 2 == 0 ? Color.parseColor("#91BF17") : Color.parseColor("#3762ff"));

        holder.srs.setText(this.main.live_data_list.get(position).srs);
        holder.srs.setSelected(true);

       /* if (holder.srs_str.length() >= 20) {
            holder.srs.setText(this.main.live_data_list.get(position).srs.substring(0, 20) + "...");
        } else {
            holder.srs.setText(this.main.live_data_list.get(position).srs);
        }*/
/*
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
        }*/


        if (holder.mchState.toString().equals("inprogress") || holder.mchState.toString().equals("stump") || holder.mchState.toString().equals("tea")) {
            holder.content_full_livestatus.setText("Live");
//            holder.time.setVisibility(View.GONE);
            //holder.time_num.setVisibility(GONE);
            holder.vcity.setVisibility(View.VISIBLE);
//            holder.vcountry.setVisibility(View.GONE);
//            holder.bowl_score_layout.setVisibility(View.GONE);
            holder.overs.setVisibility(View.VISIBLE);
            holder.bowl_over.setVisibility(View.VISIBLE);
//            holder.bat_score_layout.setVisibility(View.VISIBLE);
        }
        if (holder.mchState.toString().equals("preview")) {
            holder.content_full_livestatus.setText(this.main.live_data_list.get(position).startdt);
            holder.txt_score1.setVisibility(View.GONE);
            holder.txt_score2.setVisibility(View.GONE);
            holder.overs.setVisibility(View.GONE);
            holder.bowl_over.setVisibility(View.GONE);
            holder.content_full_about.setVisibility(View.VISIBLE);

//            holder.time.setVisibility(View.VISIBLE);
            holder.vcity.setVisibility(View.VISIBLE);
//            holder.vcountry.setVisibility(View.VISIBLE);
//            holder.bowl_score_layout.setVisibility(View.GONE);
//            holder.bat_score_layout.setVisibility(View.GONE);

        }

        if (holder.mchState.toString().equals("complete")) {
            holder.content_full_livestatus.setText(this.main.live_data_list.get(position).mchState.toUpperCase());
            holder.overs.setVisibility(View.VISIBLE);
            holder.bowl_over.setVisibility(View.VISIBLE);
            //   holder.oversright.setVisibility(GONE);
//            holder.time.setVisibility(View.GONE);
            //holder.time_num.setVisibility(GONE);
            holder.vcity.setVisibility(View.VISIBLE);
//            holder.vcountry.setVisibility(View.GONE);
//            holder.bowl_score_layout.setVisibility(View.VISIBLE);
//            holder.bat_score_layout.setVisibility(View.VISIBLE);


        }
        if (holder.mchState.toString().equals("delay")) {

            holder.content_full_livestatus.setText(this.main.live_data_list.get(position).mchState.toUpperCase());
            holder.overs.setVisibility(View.GONE);
            holder.bowl_over.setVisibility(View.GONE);
            //  holder.oversright.setVisibility(GONE);
//            holder.time.setVisibility(View.GONE);
            holder.content_full_about.setVisibility(View.GONE);
            holder.status.setVisibility(View.VISIBLE);
            holder.vcity.setVisibility(View.VISIBLE);
//            holder.vcountry.setVisibility(View.GONE);
//            holder.bowl_score_layout.setVisibility(View.GONE);
//            holder.bat_score_layout.setVisibility(View.GONE);
        }

        final ListAdapter.ViewHolderItem finalHolder2 = holder;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pvadMob.showInter(position, "SHOW");
                final String st = main.live_data_list.get(position).status;
                final String mDesc = main.live_data_list.get(position).mchDesc;
                Intent opn = new Intent(main.getActivity(), full_scoreboard.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                opn.putExtra("datapath", finalHolder2.datapath);
                opn.putExtra("status", st);
                opn.putExtra("matchDesc", mDesc);
                main.startActivity(opn);
                main.getActivity().finish();
                // full_scorboard.checkmatchid(finalHolder.datapath);
            }
        });
        return convertView;
    }

}