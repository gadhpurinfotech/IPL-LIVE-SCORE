package com.gadhpurinfotech.cricketlivescore.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gadhpurinfotech.cricketlivescore.App.AppController;
import com.gadhpurinfotech.cricketlivescore.App.Constants;
import com.gadhpurinfotech.cricketlivescore.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.ypyproductions.task.DBTask;
import com.ypyproductions.task.IDBTaskListener;
import com.ypyproductions.utils.ApplicationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ravi on 29/07/15.
 */
public class recent extends Fragment {

    // json array response url
    private String urlJsonArry = "http://mapps.cricbuzz.com/cbzandroid/2.0/archivematches.json";

    private static String TAG = MainActivity.class.getSimpleName();

    // Progress dialog
    private ProgressDialog pDialog;
    public ListAdapter_recent listAdapter;
    private ListView listView;
    public ArrayList<Constants> live_data_list = new ArrayList<Constants>();
    RelativeLayout content_main;
    Constants constants;
    private DBTask mDBTask;
    private PullToRefreshListView mListView;

    public recent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recent_match, container, false);


       // listView = (ListView) rootView.findViewById(R.id.listView);
        content_main = (RelativeLayout) rootView.findViewById(R.id.content_main);
        this.mListView = (PullToRefreshListView) rootView.findViewById(R.id.list_tracks);
        listAdapter = new ListAdapter_recent(this);
      //  listView.setAdapter(listAdapter);
        mListView.setAdapter(listAdapter);
        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        this.mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {

                mListView.setVisibility(View.VISIBLE);
                startGetData(true);

            }
        });

        makeJsonArrayRequest();

        return rootView;
    }


    private void startGetData(final boolean isRefresh) {
        if (!ApplicationUtils.isOnline(getActivity())) {
            mListView.onRefreshComplete();
            if (isRefresh) {


                //showToast(R.string.info_lose_internet);
            }


            return;
        }
        mDBTask = new DBTask(new IDBTaskListener() {

            @Override
            public void onPreExcute() {
                if (!isRefresh) {

                }
            }


            @Override
            public void onPostExcute() {
                mListView.onRefreshComplete();
                final ProgressDialog progress = new ProgressDialog(getContext());
                progress.setMessage("Loading...");
                progress.show();

                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        progress.cancel();
                    }
                };

                Handler pdCanceller = new Handler();
                pdCanceller.postDelayed(progressRunnable, 500);
            }

            @Override
            public void onDoInBackground() {

            }

        });
        mDBTask.execute();

    }




    private void makeJsonArrayRequest() {
        String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();
        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                urlJsonArry, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONObject header = null;
                    JSONArray jsonArray = new JSONArray(response);
                    Log.e("Response ",jsonArray+"");
                    int numberOfItemsInResp = jsonArray.length();
                    for (int i = 0; i < numberOfItemsInResp; i++) {
                        constants = new Constants();
                        JSONObject match = jsonArray.getJSONObject(i);
                        constants.matchId = match.getString("matchId");
                        String srsid = match.getString("srsid");
                        constants.srs = match.getString("srs");
                        constants.datapath = match.getString("datapath");

                        if (match.has("header")) {
                            header = match.getJSONObject("header");
                            if(header.has("start_time")||header.has("end_tim")||header.has("vzone")) {
                                String start_time = header.getString("start_time");
                                String end_time = header.getString("end_time");
                                String vzone = header.getString("vzone");
                            }
                            String startdte = header.getString("startdt");
                            constants.stTme = header.getString("stTme");
                            String stTmeGMT = header.getString("stTmeGMT");
                            String enddt = header.getString("enddt");
                            constants.mnum = header.getString("mnum");
                            constants.type = header.getString("type");
                            constants.mchDesc = header.getString("mchDesc");
                            constants.mchState = header.getString("mchState");
                            constants.TW = header.getString("TW");
                            constants.decisn = header.getString("decisn");
                            constants.vcity = header.getString("vcity");
                            constants.vcountry = header.getString("vcountry");
                            constants.status = header.getString("status");
                            String addnStatus = header.getString("addnStatus");
                            String MOM = header.getString("MOM");
                            String NoOfIngs = header.getString("NoOfIngs");
                            //Toast.makeText(MainActivity.this, constants.TW+constants.decisn, Toast.LENGTH_SHORT).show();
                        }
                        if (match.has("miniscore")) {
                            JSONObject miniscore = match.getJSONObject("miniscore");
                            constants.batteamid = miniscore.getString("batteamid");
                            constants.batteamscore = miniscore.getString("batteamscore");
                            constants.bowlteamid = miniscore.getString("bowlteamid");
                            constants.bowlteamscore = miniscore.getString("bowlteamscore");
                            constants.overs = miniscore.getString("overs");
                            String bowlteamovers = miniscore.getString("bowlteamovers");
                            constants.bowler_over=bowlteamovers;
//                            Log.e("null over",constants.bowler_over);
                            String rrr = miniscore.getString("rrr");
                            String crr = miniscore.getString("crr");
                            String cprtshp = miniscore.getString("cprtshp");
                            String prevOvers = miniscore.getString("prevOvers");
                            String lWkt = miniscore.getString("lWkt");
                            String oversleft = miniscore.getString("oversleft");

                            JSONObject striker = miniscore.getJSONObject("striker");
                            String fullName = striker.getString("fullName");
                            constants.runs = striker.getString("runs");
                            String balls = striker.getString("balls");
                            String fours = striker.getString("fours");
                            String sixes = striker.getString("sixes");
                            JSONObject nonStriker = miniscore.getJSONObject("nonStriker");
                            String nonStriker_fullName = nonStriker.getString("fullName");
                            String nonStriker_runs = nonStriker.getString("runs");
                            String nonStriker_balls = nonStriker.getString("balls");
                            String nonStriker_fours = nonStriker.getString("fours");
                            String nonStriker_sixes = nonStriker.getString("sixes");
                            JSONObject bowler = miniscore.getJSONObject("bowler");
                            String bowler_fullName = bowler.getString("fullName");
                            String bowler_overs = bowler.getString("overs");
                            String bowler_maidens = bowler.getString("maidens");
                            String bowler_runs = bowler.getString("runs");
                            String bowler_wicket = bowler.getString("wicket");
                            JSONObject nsbowler = miniscore.getJSONObject("nsbowler");
                            String nsbowler_fullName = nsbowler.getString("fullName");
                            String nsbowler_overs = nsbowler.getString("overs");
                            String nsbowler_maidens = nsbowler.getString("maidens");
                            String nsbowler_runs = nsbowler.getString("runs");
                            String nsbowler_wicket = nsbowler.getString("wicket");
                        }
                        if (match.has("team1")) {
                            JSONObject team1 = match.getJSONObject("team1");
                            constants.team1_id = team1.getString("id");
                            String team1_name = team1.getString("name");
                            constants.team1_fName = team1.getString("fName");
                            constants.team1_sName = team1.getString("sName");
                            constants.flag = team1.getString("flag");

                        }
                        if (match.has("team2")) {
                            JSONObject team2 = match.getJSONObject("team2");
                            constants.team2_id = team2.getString("id");
                            String team2_name = team2.getString("name");
                            constants.team2_fName = team2.getString("fName");
                            constants.team2_sName = team2.getString("sName");
                            constants.team2_flag = team2.getString("flag");

                        }
                        if (match.has("valueAdd")) {
                            JSONObject valueAdd = match.getJSONObject("valueAdd");
                            JSONObject alerts = valueAdd.getJSONObject("alerts");
                            String enabled = alerts.getString("enabled");
                            String alerts_type = alerts.getString("type");
                        }
                        String bowlScore = constants.bowlteamscore;
                        String batScore = constants.batteamscore;
                        String bowlOver = constants.bowler_over;
                        String batOver = constants.overs;
                        if (constants.team1_id.equals(constants.batteamid)) {
                            Log.e("Bat id: "+constants.batteamid," Team 1 id: "+constants.team1_id);
                            Log.e("Bowl id: "+constants.bowlerId," Team 1 id: "+constants.team2_id);
                            constants.overs = bowlOver;
                            constants.bowler_over = batOver;
                            constants.bowlteamscore = batScore;
                            constants.batteamscore = bowlScore;
                        }
                        live_data_list.add(constants);
                        System.out.println(i+" : "+ constants.overs);
//                        Log.e("Constant Over : "+i,constants.overs.toString());

                    }

                    listAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                pDialog.dismiss();

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof NetworkError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ServerError) {
                    message = "The server could not be found. Please try again after some time!!";
                } else if (error instanceof AuthFailureError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof ParseError) {
                    message = "Parsing error! Please try again after some time!!";
                } else if (error instanceof NoConnectionError) {
                    message = "Cannot connect to Internet...Please check your connection!";
                } else if (error instanceof TimeoutError) {
                    message = "Connection TimeOut! Please check your internet connection.";
                }


               /// Toast.makeText(getContext(), message + "income", Toast.LENGTH_LONG).show();

                pDialog.dismiss();
            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq1, tag_string_req1);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }







    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
