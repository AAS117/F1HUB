package com.example.f1hub;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

// https://www.youtube.com/watch?v=tPV8xA7m-iw&list=FLg8UcRtLyjxPkR6VDnYsNHg
public class ClassementPiloteFragment extends Fragment {

    private TextView textViewResult;
    private RequestQueue queue;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_classement_pilote, container, false);

        textViewResult = v.findViewById(R.id.text_view_result);
        Button buttonParse = v.findViewById(R.id.button_parse);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        buttonParse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                jsonParseClassementPilotes();
            }
        });

        return v;
    }

    private void jsonParseClassementPilotes(){
        String url = "https://ergast.com/api/f1/current/driverStandings.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                // appellé si requête successfull
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject mrdata = response.getJSONObject("MRData");
                            JSONObject standingsTable = mrdata.getJSONObject("StandingsTable");
                            JSONArray standingsList = standingsTable.getJSONArray("StandingsLists");
                            JSONObject jsonObject = standingsList.getJSONObject(0);
                            JSONArray jsonArray = jsonObject.getJSONArray("DriverStandings");
                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject driverStandings = jsonArray.getJSONObject(i);
                                String position = driverStandings.getString("positionText");
                                String nbPoints = driverStandings.getString("points");

                                JSONObject driverInfo = driverStandings.getJSONObject("Driver");
                                String prenom = driverInfo.getString("givenName");
                                String nom = driverInfo.getString("familyName");

                                textViewResult.append(position + " --- " + nom + " " + prenom + "------------" + nbPoints + "\n\n");
                                //textViewResult.append(prenom + ", " + nom + ", " + position + ", " + nbPoints);
                                System.out.println(textViewResult);0
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            // appellé si requête error
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }


}
