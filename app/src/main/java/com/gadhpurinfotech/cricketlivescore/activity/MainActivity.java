package com.gadhpurinfotech.cricketlivescore.activity;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gadhpurinfotech.cricketlivescore.App.AppController;
import com.gadhpurinfotech.cricketlivescore.App.PVADMob;
import com.gadhpurinfotech.cricketlivescore.R;
import com.gadhpurinfotech.cricketlivescore.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    // ProgressBar
    // https://github.com/Taishi-Y/FlipProgressDialog

    //http://mapps.cricbuzz.com/cbzios/match/livematches

    public ViewPager viewPager;
    private static String TAG = MainActivity.class.getSimpleName();
    FloatingActionButton foting;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private ActionBar actionBar;
    FrameLayout ly;
    ImageView draw_image;
    Runnable refresh;

    private PVADMob pvadMob;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppController.getInstance().trackScreenView("Live Cricket");

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        foting = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(mToolbar);
        ly = (FrameLayout) findViewById(R.id.container_body);
        ly.setVisibility(View.GONE);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final int[] arrImgFooter = new int[]{
                R.drawable.icn_footer_live_cricket_unselected,
                R.drawable.icn_footer_recent_match_unselected,
                R.drawable.icn_footer_ipl_unselected,
                R.drawable.icn_footer_latest_news_unselected};
        final int[] arrImgSelectFooter=new int[]{
                R.drawable.icn_footer_live_cricket,
                R.drawable.icn_footer_recent_match,
                R.drawable.icn_footer_ipl,
                R.drawable.icn_footer_latest_news
        };

        String[] arrTitleFooter = new String[]{getResources().getString(R.string.title_home),
                getResources().getString(R.string.title_friends),
                getResources().getString(R.string.title_IPL),
                getResources().getString(R.string.title_messages)};

        for (int i = 0; i < arrTitleFooter.length; i++) {
            // inflate the Parent LinearLayout Container for the tab
            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.raw_dashboard_footer, null);

            // get child TextView and ImageView from this layout for the icon and label
            ImageView imgIcon = tab.findViewById(R.id.imgIcon);
            TextView txtTitle = tab.findViewById(R.id.txtTitle);
            View viewBorder = tab.findViewById(R.id.viewBorder);

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            txtTitle.setText(arrTitleFooter[i]);
            imgIcon.setImageResource(arrImgFooter[i]);

           /* // set the home to be active at first
            if (i == 0) {
                txtTitle.setTextColor(getResources().getColor(R.color.efent_color));
                imgIcon.setImageResource(navIconsActive[i]);
            } else {
                imgIcon.setImageResource(navIcons[i]);
            }*/

            if (i == 0) {
                viewBorder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                imgIcon.setImageResource(arrImgSelectFooter[0]);
                txtTitle.setTextColor(getResources().getColor(R.color.colorPrimary));
            } else {
                viewBorder.setBackgroundColor(Color.TRANSPARENT);
            }

            // finally publish this custom view to navigation tab
            tabLayout.addTab(tabLayout.newTab().setCustomView(tab));
        }

        pvadMob = new PVADMob(MainActivity.this);
        RelativeLayout layout = findViewById(R.id.layout_ad);
        pvadMob.showBannerAd(layout);

        viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                View tabView = tab.getCustomView();

                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    View view = tabLayout.getTabAt(i).getCustomView();
                    ImageView imgIcon = view.findViewById(R.id.imgIcon);
                    View viewBorder = view.findViewById(R.id.viewBorder);
                    TextView txtTitle = view.findViewById(R.id.txtTitle);
                    txtTitle.setTextColor(getResources().getColor(R.color.c_464646));
                    viewBorder.setBackgroundColor(Color.TRANSPARENT);
                    imgIcon.setImageResource(arrImgFooter[i]);
                }

                ImageView imgIcon = tabView.findViewById(R.id.imgIcon);
                View viewBorder = tabView.findViewById(R.id.viewBorder);
                viewBorder.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                TextView txtTitle = tabView.findViewById(R.id.txtTitle);

                viewPager.setCurrentItem(tab.getPosition());
                imgIcon.setImageResource(arrImgSelectFooter[tab.getPosition()]);
                txtTitle.setTextColor(getResources().getColor(R.color.colorPrimary));
                viewPager.setVisibility(View.VISIBLE);
                ly.setVisibility(View.GONE);
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {

                    mToolbar.setTitle(R.string.title_home);
                }
                if (tab.getPosition() == 1) {
                    mToolbar.setTitle(R.string.title_friends);
                }
                if (tab.getPosition() == 2) {
                    mToolbar.setTitle(R.string.title_IPL);
                }
                if (tab.getPosition() == 3) {
                    mToolbar.setTitle(R.string.title_messages);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        displayView(0);


        foting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;

                FragmentTabHost tabHost3 = (FragmentTabHost) findViewById(android.R.id.tabhost);
                int current_tab = viewPager.getCurrentItem();

                switch (current_tab) {
                    case 0:
                        fragment = new livecricket();

                        break;
                    case 1:
                        fragment = new recent();

                        break;
                    case 2:

                        fragment = new iplschedule();
                        break;
                    case 3:
                        fragment = new Lastestnews();
                        break;

                    default:
                        break;
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        pvadMob = new PVADMob(MainActivity.this, new PVADMob.InterAdListener() {
            @Override
            public void onClick(int position, String type) {
                switch (type) {
                    case "SIDEMENU":
                        displayView(position);
                        break;
                }
            }
        });
        pvadMob.loadInter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Disclaimer) {
            showDdisclaimerdialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        pvadMob.showInter(position, "SIDEMENU");
        ly.setVisibility(View.GONE);
        viewPager.setCurrentItem(position);
        viewPager.setVisibility(View.VISIBLE);

    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new livecricket();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new recent();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new iplschedule();
                title = getString(R.string.title_IPL);

                break;
            case 3:
                fragment = new Lastestnews();
                title = getString(R.string.title_messages);

                break;
            case 4:
                showDiaglogAboutUs();
                break;
            case 5:
                rateme();
                break;
            case 6:
                share();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }


   /* private void setUpLayoutAdmob() {

        if (ApplicationUtils.isOnline(this)) {
            boolean b = true;
            if (b) {
                pvadMob.showBannerAd(layout);
            } else {
                layout.setVisibility(View.GONE);
            }
        } else {
            layout.setVisibility(View.GONE);
        }
    }*/

    public void rateme() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        //Copy App URL from Google Play Store.
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName()));

        startActivity(intent);

        Log.i("Code2care ", "Cancel button Clicked!");
    }

    public void more() {

        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);

        //Copy App URL from Google Play Store.
        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=6143525667302402635"));

        startActivity(intent);

        Log.i("Code2care ", "Cancel button Clicked!");
    }

    public void privacy_policy() {

        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://pamsquad.com/Privacy_Policy/Livescrore.html"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void share() {

        String message = "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(share, "Share the link of Live Cricket"));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    private void showDiaglogAboutUs() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_about)
                .setTitle("About Us")
                .setNegativeButton("close", null)
                .show();


    }


        private void showDdisclaimerdialog() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(R.layout.dialog_disclaimer)
                .setTitle("Disclaimer")
                .setNegativeButton("close", null)
                .show();
    }
}