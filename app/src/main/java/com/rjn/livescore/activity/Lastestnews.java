package com.rjn.livescore.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
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
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.rjn.livescore.App.AppController;
import com.rjn.livescore.App.Constants;
import com.rjn.livescore.R;
import com.ypyproductions.task.DBTask;
import com.ypyproductions.task.IDBTaskListener;
import com.ypyproductions.utils.ApplicationUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lastestnews extends Fragment {

    // json array response url
    private String urlJsonArry = "http://mapps.cricbuzz.com/cricbuzz-android/news/index";

    private static String TAG = MainActivity.class.getSimpleName();
    private PullToRefreshListView mListView;
    // Progress dialog
    private ProgressDialog pDialog;
    public ListAdapter_lastestnews listAdapter;
    private ListView listView;
    public ArrayList<Constants> live_data_list = new ArrayList<Constants>();
    RelativeLayout content_main;
    Constants constants;
    private DBTask mDBTask;
    Boolean start = true;

    public Lastestnews() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.lastestnews, container, false);

        // listView = (ListView) rootView.findViewById(R.id.listView);
        content_main = (RelativeLayout) rootView.findViewById(R.id.content_main);
        this.mListView = (PullToRefreshListView) rootView.findViewById(R.id.list_tracks);
        listAdapter = new ListAdapter_lastestnews(this);
        // listView.setAdapter(listAdapter);
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
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray stories = jsonObject.getJSONArray("stories");
                    for (int i = 0; i < stories.length(); i++) {
                        constants = new Constants();
                        JSONObject jsonObject1 = stories.getJSONObject(i);
                        if (jsonObject1.has("id")) {

                            constants.news_id = jsonObject1.getString("id");
                        }
                        constants.news_datapath = jsonObject1.getString("datapath");
                        constants.hline = jsonObject1.getString("hline");
                        // Toast.makeText(Main2Activity.this, constants.team1_vs_team2, Toast.LENGTH_SHORT).show();
                        constants.intro = jsonObject1.getString("intro");
                        JSONObject header = jsonObject1.getJSONObject("header");
                        constants.location = header.getString("location");
                        constants.news_date = header.getString("date");
                        constants.src = header.getString("src");
                        constants.isE = header.getString("isE");
                        constants.topic_name = header.getString("topic_name");
                        constants.author_name = header.getString("author_name");
                        constants.relatedStoriesCount = header.getString("relatedStoriesCount");
                        JSONObject img = jsonObject1.getJSONObject("img");
                        constants.ipath = img.getString("ipath");
                        constants.iwth = img.getString("iwth");
                        constants.iht = img.getString("iht");
                        JSONObject ximg = jsonObject1.getJSONObject("ximg");
                        constants.ximg_ipath = ximg.getString("ipath");
                        constants.ximg_iwth = ximg.getString("iwth");
                        constants.ximg_iht = ximg.getString("iht");
                        live_data_list.add(constants);
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


                //    Toast.makeText(getContext(), message + "income", Toast.LENGTH_LONG).show();

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
