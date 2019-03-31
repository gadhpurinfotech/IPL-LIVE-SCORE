package com.sports.livecricketscore.App;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class PVADMob {
    private Context context;
    private InterAdListener interAdListener;
    private int evenOdd = 2;

    // constructor
    public PVADMob(Context context) {
        this.context = context;
    }

    // constructor
    public PVADMob(Context context, InterAdListener interAdListener) {
        this.context = context;
        this.interAdListener = interAdListener;
    }

    public interface InterAdListener {
        void onClick(int position, String type);
    }

    public interface RewardedAdListener {
        void onClick(int position, String type);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
