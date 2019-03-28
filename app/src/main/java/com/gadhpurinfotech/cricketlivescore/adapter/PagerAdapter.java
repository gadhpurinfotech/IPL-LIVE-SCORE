package com.gadhpurinfotech.cricketlivescore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.gadhpurinfotech.cricketlivescore.activity.Lastestnews;
import com.gadhpurinfotech.cricketlivescore.activity.iplschedule;
import com.gadhpurinfotech.cricketlivescore.activity.livecricket;
import com.gadhpurinfotech.cricketlivescore.activity.recent;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                livecricket tab1 = new livecricket();
                return tab1;
            case 1:
                recent tab2 = new recent();
                return tab2;
            case 2:
                iplschedule tab4 = new iplschedule();
            return tab4;
            case 3:
                Lastestnews tab3 = new Lastestnews();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}