package com.gadhpurinfotech.cricketlivescore.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sakhi on 3/27/2017.
 */
public class bat_bowl_ining_2 extends Fragment {
    public bat_inig2_Adapter bat_inig1_adapter;
    public bowl_ining2_Adapter bowl_ining1_adapter;
    private ListView listView,list_bowlings;
    TextView bat_description;
    public ArrayList<Constants> batsmen_data_list = new ArrayList<Constants>();
    public ArrayList<Constants> bowling_data_list = new ArrayList<Constants>();
    full_scoreboard full_scoreboard;
    Constants constants;
    LinearLayout tablayout_bating,tablayout_bowling;
    public JSONObject batsmen_object;

    public bat_bowl_ining_2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bat_bowl_ining_1, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);
        list_bowlings = (ListView) rootView.findViewById(R.id.list_bowlings);
        bat_description = (TextView) rootView.findViewById(R.id.Batteam_description);
        tablayout_bating = (LinearLayout) rootView.findViewById(R.id.tabllayout);
        tablayout_bowling = (LinearLayout) rootView.findViewById(R.id.tabllayout_bowl);
        bat_inig1_adapter = new bat_inig2_Adapter(this);
        bowl_ining1_adapter= new bowl_ining2_Adapter(this);
        listView.setAdapter(bat_inig1_adapter);
        list_bowlings.setAdapter(bowl_ining1_adapter);
        score_board();
        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void score_board() {

        String SCOR_BOARD = "http://synd.cricbuzz.com/cbzandroid/3.0/match/" + full_scoreboard.datapath + "scorecard.json";
        // Toast.makeText(this, datapath, Toast.LENGTH_LONG).show();

        final String tag_string_req1 = "string_req";
        // Defined Array values to show in ListView
        final ProgressDialog pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();


        StringRequest strReq1 = new StringRequest(Request.Method.GET,
                SCOR_BOARD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject header = null;
                    tablayout_bating.setVisibility(View.VISIBLE);
                    tablayout_bowling.setVisibility(View.VISIBLE);
                    JSONObject match = new JSONObject(response);
                    if (match.has("matchId")) {
                        String matchId = match.getString("matchId");
                        String srsid = match.getString("srsid");
                        String srs = match.getString("srs");
                        String datapath = match.getString("datapath");
                    }
                    if (match.has("header")) {
                        header = match.getJSONObject("header");
                        String startdte = header.getString("startdt");
                        String stTme = header.getString("stTme");
                        String stTmeGMT = header.getString("stTmeGMT");
                        String enddt = header.getString("enddt");
                        String mnum = header.getString("mnum");
                        String type = header.getString("type");
                        String mchDesc = header.getString("mchDesc");
                        String mchState = header.getString("mchState");
                        String TW = header.getString("TW");
                        String decisn = header.getString("decisn");
                        String grnd = header.getString("grnd");
                        String vcity = header.getString("vcity");
                        String vcountry = header.getString("vcountry");
                        String status = header.getString("status");
                        // split_status = status.split("in");
                        String addnStatus = header.getString("addnStatus");
                        String MOM = header.getString("MOM");
                        String NoOfIngs = header.getString("NoOfIngs");
                    }

                    if (match.has("Innings")) {

                        JSONObject Innings = match.getJSONObject("Innings");
                        if (Innings.has("2")) {
                            JSONObject Inning_num_2 = Innings.getJSONObject("2");
                            String battingteamid = Inning_num_2.getString("battingteamid");
                            String battingteam = Inning_num_2.getString("battingteam");
                            String bowlingteam = Inning_num_2.getString("bowlingteam");
                            String bowlingteamid = Inning_num_2.getString("bowlingteamid");
                            String runs = Inning_num_2.getString("runs");
                            String wickets = Inning_num_2.getString("wickets");
                            String overs = Inning_num_2.getString("overs");
                            String innDesc = Inning_num_2.getString("innDesc");
                            String RR = Inning_num_2.getString("RR");
                            bat_description.setText(battingteam + " " + runs + " / "+ wickets  + " (" + overs + " Over)");
                            if (Inning_num_2.has("batsmen")) {
                                if (match.has("players")) {
                                    JSONArray players = match.getJSONArray("players");
                                    JSONArray batsmen = Inning_num_2.getJSONArray("batsmen");
                                    for (int j = 0; j < batsmen.length(); j++) {
                                        constants = new Constants();
                                        batsmen_object = batsmen.getJSONObject(j);
                                        constants.outdescription_ing1 = batsmen_object.getString("outdescription");
                                        for (int q = 0; q < players.length(); q++) {
                                            JSONObject players_object = players.getJSONObject(q);
                                            if (players_object.getString("id").equals(batsmen_object.getString("batsmanId"))) {
                                                constants.batsmanId_ing1 = players_object.getString("fName");
                                                if (constants.outdescription_ing1.equals("batting"))
                                                    constants.batsmanId_ing1 = constants.batsmanId_ing1 + " *";
                                            }
                                        }
                                        constants.run_ing1 = batsmen_object.getString("run");
                                        constants.ball_ing1 = batsmen_object.getString("ball");
                                        constants.four_ing1 = batsmen_object.getString("four");
                                        constants.six_ing1 = batsmen_object.getString("six");
                                        constants.sr_ing1 = batsmen_object.getString("sr");
                                        batsmen_data_list.add(constants);

                                    }


                                    if (Inning_num_2.has("bowlers")) {
                                        JSONArray bowlers = Inning_num_2.getJSONArray("bowlers");
                                        for (int j = 0; j < bowlers.length(); j++) {
                                            constants = new Constants();
                                            JSONObject bowler_object = bowlers.getJSONObject(j);
                                            for (int q = 0; q < players.length(); q++) {
                                                JSONObject players_object = players.getJSONObject(q);
                                                if (players_object.getString("id").equals(bowler_object.getString("bowlerId"))) {
                                                    constants.bowlerId = players_object.getString("fName");
                                                }
                                            }
                                            constants.bowler_over = bowler_object.getString("over");
                                            constants.maiden = bowler_object.getString("maiden");
                                            constants.run = bowler_object.getString("run");
                                            constants.bowler_wicket = bowler_object.getString("wicket");
                                            constants.noball = bowler_object.getString("noball");
                                            constants.wideball = bowler_object.getString("wideball");
                                            constants.sr = bowler_object.getString("sr");
                                            bowling_data_list.add(constants);
                                        }
                                    }

                                }

                            }
                            if (Inning_num_2.has("nextbatsman")) {
                                String nextbatsman = Inning_num_2.getString("nextbatsman");
                            }
                            if (Inning_num_2.has("extras")) {
                                JSONObject extras = Inning_num_2.getJSONObject("extras");
                                String total = extras.getString("total");
                                String byes = extras.getString("byes");
                                String legByes = extras.getString("legByes");
                                String wideBalls = extras.getString("wideBalls");
                                String noBalls = extras.getString("noBalls");
                                String penalty = extras.getString("penalty");
                            }
                            if (Inning_num_2.has("fow")) {
                                JSONArray fow = Inning_num_2.getJSONArray("fow");
                                for (int j = 0; j < fow.length(); j++) {
                                    JSONObject fow_object = fow.getJSONObject(j);
                                    String outBatsmanId = fow_object.getString("outBatsmanId");
                                    String run = fow_object.getString("run");
                                    String wicketnbr = fow_object.getString("wicketnbr");
                                    String overnbr = fow_object.getString("overnbr");
                                }
                            }
                        }
                    }
                    if (match.has("official")) {
                        JSONObject official = match.getJSONObject("official");

                        if (official.has("umpire1")) {
                            JSONObject umpire1 = official.getJSONObject("umpire1");
                            String umpire1_name = umpire1.getString("umpirename");
                            String umpire1_country = umpire1.getString("country");
                            // Toast.makeText(full_scoreboard.this, umpire1_country+umpire1_name, Toast.LENGTH_SHORT).show();
                        }
                        if (official.has("umpire2")) {
                            JSONObject umpire2 = official.getJSONObject("umpire2");
                            String umpire2_name = umpire2.getString("umpirename");
                            String umpire2_country = umpire2.getString("country");
                            // Toast.makeText(full_scoreboard.this, umpire2_country+umpire2_name, Toast.LENGTH_SHORT).show();

                        }
                        if (official.has("umpire3")) {
                            JSONObject umpire3 = official.getJSONObject("umpire3");
                            String umpire3_name = umpire3.getString("umpirename");
                            String umpire3_country = umpire3.getString("country");
                        }
                        if (official.has("referee")) {
                            JSONObject referee = official.getJSONObject("referee");
                            String referee_name = referee.getString("refereename");
                            String referee_country = referee.getString("country");
                        }
                    }
                    if (match.has("team1")) {
                        JSONObject team1 = match.getJSONObject("team1");
                        String team1_id = team1.getString("id");
                        String team1_name = team1.getString("name");
                        String team1_fName = team1.getString("fName");
                        String team1_sName = team1.getString("sName");
                        String flag = team1.getString("flag");
                        JSONArray squad = team1.getJSONArray("squad");
                        for (int k = 0; k < squad.length(); k++) {
                            String value_squad_team1 = squad.getString(k);
                            //Toast.makeText(full_scoreboard.this, value_squad_team1, Toast.LENGTH_SHORT).show();
                        }

                    }
                    if (match.has("team2")) {
                        JSONObject team2 = match.getJSONObject("team2");
                        String team2_id = team2.getString("id");
                        String team2_name = team2.getString("name");
                        String team2_fName = team2.getString("fName");
                        String team2_sName = team2.getString("sName");
                        String team2_flag = team2.getString("flag");
                        JSONArray squad_team2 = team2.getJSONArray("squad");
                        for (int k = 0; k < squad_team2.length(); k++) {
                            String value_squad_team2 = squad_team2.getString(k);
                        }

                    }
                    if (match.has("valueAdd")) {
                        JSONObject valueAdd = match.getJSONObject("valueAdd");
                        JSONObject alerts = valueAdd.getJSONObject("alerts");
                        String enabled = alerts.getString("enabled");
                        String alerts_type = alerts.getString("type");
                        JSONObject pointstable = valueAdd.getJSONObject("pointsTable");

                    }

                    bat_inig1_adapter.notifyDataSetChanged();
                    bowl_ining1_adapter.notifyDataSetChanged();
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


                //   Toast.makeText(getApplicationContext(), message + "income", Toast.LENGTH_LONG).show();

                //  pDialog.dismiss();
            }
        }) {

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq1, tag_string_req1);
    }
}