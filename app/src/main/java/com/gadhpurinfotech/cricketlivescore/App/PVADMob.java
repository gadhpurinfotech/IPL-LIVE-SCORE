package com.gadhpurinfotech.cricketlivescore.App;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.gadhpurinfotech.cricketlivescore.R;

public class PVADMob {
    private Context context;
    private InterAdListener interAdListener;
    private InterstitialAd interstitialAd;
    private int evenOdd = 2;

    private RewardedAdListener rewardedAdListener;

    private NativeAd nativeAd;

    private AdView adView;

    // constructor
    public PVADMob(Context context) {
        this.context = context;
    }

    // constructor
    public PVADMob(Context context, InterAdListener interAdListener) {
        this.context = context;
        this.interAdListener = interAdListener;
        loadInter();
    }

    public PVADMob(Context context, RewardedAdListener rewardedAdListener) {
        this.context = context;
        this.rewardedAdListener = rewardedAdListener;
        //loadReward();
    }

    public void showBannerAd(LinearLayout lout) {
        adView = new AdView(context, context.getResources().getString(R.string.fb_Banner_Ad), AdSize.BANNER_HEIGHT_50);
        lout.addView(adView);
        adView.loadAd();
    }

    public void showBannerAd(RelativeLayout lout) {
        adView = new AdView(context, context.getResources().getString(R.string.fb_Banner_Ad), AdSize.BANNER_HEIGHT_50);
        lout.addView(adView);
        adView.loadAd();
    }

    public void loadInter() {
        interstitialAd = new InterstitialAd(context, context.getResources().getString(R.string.fb_Interstitial_Ad));
        interstitialAd.loadAd();
        //evenOdd = evenOdd == 1 ? 2 : 1;
    }

    public void showInter(final int pos, final String strType) {
        if (interstitialAd != null && interstitialAd.isAdLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    if (interAdListener != null) {
                        interAdListener.onClick(pos, strType);
                    }
                    interstitialAd.loadAd();
                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });

        } else {
            if (interAdListener != null) {
                interAdListener.onClick(pos, strType);
            }
        }
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

    public void showNativeAd(final LinearLayout loutNativeAds) {
        if (loutNativeAds == null) {
            return;
        }
        nativeAd = new NativeAd(context, context.getResources().getString(R.string.fb_Native_Ad));
        nativeAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                try {
                    View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
                    loutNativeAds.addView(adView);
                } catch (Exception e) {
                }
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        nativeAd.loadAd();
    }
}
