package com.geotag.tagx5.geotag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.ArrayList;

/**
 * Created by csastudent2015 on 4/14/16.
 */
public class HomeFragment extends Fragment {

    private TextView mScore;
    private TextView mLivesRemaining;
    private Button mLocUpdate;
    private int score;
    private int livesRemaining;
    public static final String TAG = "HomeFragment";
    ArrayList<String> hitPlayers = new ArrayList<String>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mScore = (TextView) rootView.findViewById(R.id.text_score);
        mLivesRemaining = (TextView) rootView.findViewById(R.id.text_lives_remaining);

        //display user's lives remaining & score
        BackendlessUser k = Backendless.UserService.CurrentUser();
        mLivesRemaining.setText("Lives Remaining: " + k.getProperty("livesRemaining"));
        mScore.setText("Score: " + k.getProperty("score"));


        mLocUpdate = (Button) rootView.findViewById(R.id.button_location_update);

        mLocUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();

            }
        });


        Intent i = new Intent();
        i = getActivity().getIntent();


        return rootView;
    }

    public boolean hit()
    {
        boolean m = false;
        hitPlayers.clear(); //this way it'll only be the most recent hits

        //loop through every player to check distance between user and other players
        //if within range (or hit) - set m to true, add point to user, subtract points from hit player, add hit player to hitPlayers


        return m;
    }

    public void fireWeapon()
    {

        //if hit() then update mLivesRemaining & mScore
        //if hit() maybe use a toast to display hitPlayers
        //else use a toast to say something like "you better work on your aim 'cause YOU MISSED"
    }


}
