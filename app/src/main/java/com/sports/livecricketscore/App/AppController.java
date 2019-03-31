package com.sports.livecricketscore.App;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sports.livecricketscore.BuildConfig;
import com.sports.livecricketscore.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by zeeshan on 3/17/2017.
 */


public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static Context ctx;
    private static InterstitialAd interstitialAd;

    private RequestQueue mRequestQueue;

    private static AppController mInstance;

    public int fullScoreAdCounter = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        ctx = getApplicationContext();
        MobileAds.initialize(this);
        load_interstitial();
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    private static AdListener mFbAdListener = new AdListener() {
        @Override
        public void onAdClosed() {
            super.onAdClosed();
            load_interstitial();
        }

        @Override
        public void onAdFailedToLoad(int i) {
            super.onAdFailedToLoad(i);
        }

        @Override
        public void onAdLeftApplication() {
            super.onAdLeftApplication();
        }

        @Override
        public void onAdOpened() {
            super.onAdOpened();
        }

        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
        }

        @Override
        public void onAdClicked() {
            super.onAdClicked();
        }

        @Override
        public void onAdImpression() {
            super.onAdImpression();
        }
    };


    private static void load_interstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        if (BuildConfig.DEBUG)
            Log.e("TEST", "===== LOADING INTERSTITIAL =====");
        interstitialAd = new InterstitialAd(ctx);
        interstitialAd.setAdUnitId(ctx.getString(R.string.interstitial_ad_id));
        interstitialAd.setAdListener(mFbAdListener);
        interstitialAd.loadAd(adRequest);
    }

    public static void show_interstitial() {
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            load_interstitial();
        }
    }

    public static void get_banner_ad(String type, LinearLayout parent) {
        parent.removeAllViews();
        if (type.equalsIgnoreCase("BANNER")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            AdView bannerAdView = new AdView(ctx);
            bannerAdView.setAdSize(AdSize.SMART_BANNER);
            bannerAdView.setAdUnitId(ctx.getString(R.string.banner_ad_id));
            bannerAdView.loadAd(adRequest);
            parent.addView(bannerAdView);
        } else if (type.equalsIgnoreCase("RECTANGLE")) {
            AdRequest adRequest = new AdRequest.Builder().build();
            AdView bannerAdView = new AdView(ctx);
            bannerAdView.setAdSize(AdSize.MEDIUM_RECTANGLE);
            bannerAdView.setAdUnitId(ctx.getString(R.string.banner_ad_id));
            bannerAdView.loadAd(adRequest);
            parent.addView(bannerAdView);
        }
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /*public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }*/

    /***
     * Tracking screen view
     *
     * @param screenName screen name to be displayed on GA dashboard
     */
    /*public void trackScreenView(String screenName) {
        Tracker t = getGoogleAnalyticsTracker();

        // Set screen name.
        t.setScreenName(screenName);

        // Send a screen view.
        t.send(new HitBuilders.ScreenViewBuilder().build());

        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }*/

    /***
     * Tracking exception
     *
     * @param e exception to be tracked
     */
    /*public void trackException(Exception e) {
        if (e != null) {
            Tracker t = getGoogleAnalyticsTracker();

            t.send(new HitBuilders.ExceptionBuilder()
                    .setDescription(
                            new StandardExceptionParser(this, null)
                                    .getDescription(Thread.currentThread().getName(), e))
                    .setFatal(false)
                    .build()
            );
        }
    }*/

    /***
     * Tracking event
     *
     * @param category event category
     * @param action   action of the event
     * @param label    label
     */


}/*public void trackEvent(String category, String action, String label) {
        Tracker t = getGoogleAnalyticsTracker();

        // Build and send an Event.
        t.send(new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
    }*/