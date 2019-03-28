package com.rjn.livescore.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rjn.livescore.R;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by zeeshan on 3/18/2017.
 */

public class ListAdapter_lastestnews extends BaseAdapter {

    Lastestnews main;

    ListAdapter_lastestnews(Lastestnews main) {
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

    public static class ViewHolderItem {
        TextView news_id, hline, intro, location, date, src, isE, topic_name;
        TextView author_name, relatedStoriesCount, img, ipath, iwth, iht, ximg, ximg_ipath, ximg_iwth, ximg_iht;
        ImageView img_datapath;
        String img_datapath_url, date_mon;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) main.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.news_list_cell, null);
            holder.img_datapath = (ImageView) convertView.findViewById(R.id.news_img);
            holder.hline = (TextView) convertView.findViewById(R.id.hline);
            holder.location = (TextView) convertView.findViewById(R.id.location);
            holder.date = (TextView) convertView.findViewById(R.id.news_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }
        holder.hline.setText(this.main.live_data_list.get(position).hline);

        holder.img_datapath_url = this.main.live_data_list.get(position).ximg_ipath;
        holder.date_mon = this.main.live_data_list.get(position).news_date;

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(5);
        shape.setColor(Color.LTGRAY);
        holder.img_datapath.setBackground(shape);

        Picasso.with(main.getContext()).load(holder.img_datapath_url).into(holder.img_datapath);

        Log.i("Piyush Image : ", "" + holder.img_datapath_url);

        DateFormat df = new SimpleDateFormat("EEE, MMM d,yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        String location_str = this.main.live_data_list.get(position).location;

        if (location_str != null && !location_str.isEmpty()) {
            holder.location.setText(this.main.live_data_list.get(position).location);
        } else {
            holder.location.setVisibility(View.GONE);
        }

        holder.date.setText(date);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        return convertView;
    }
}
