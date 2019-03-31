package com.sports.livecricketscore.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.sports.livecricketscore.R;
import com.ypyproductions.dialog.utils.AlertDialogUtils;
import com.ypyproductions.utils.ApplicationUtils;

public class SplashActivity extends Activity {

    public static final String TAG = SplashActivity.class.getSimpleName();

    private boolean isPressBack;

    private Handler mHandler = new Handler();

    protected boolean isShowingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ApplicationUtils.isOnline(this)) {
            if (!isShowingDialog) {
                isShowingDialog = true;
                showDialogTurnOnInternetConnection();
            }
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(mIntent);
                    finish();
                }
            }, 3000);
        }

    }

    private void showDialogTurnOnInternetConnection() {
        Dialog mDialog = AlertDialogUtils.createFullDialog(this, 0, R.string.title_warning, R.string.title_settings, R.string.title_cancel,
                R.string.info_lose_internet, new AlertDialogUtils.IOnDialogListener() {
                    @Override
                    public void onClickButtonPositive() {
                        isShowingDialog = false;
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }

                    @Override
                    public void onClickButtonNegative() {
                        isShowingDialog = false;
                        finish();
                    }
                });
        mDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isPressBack) {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
