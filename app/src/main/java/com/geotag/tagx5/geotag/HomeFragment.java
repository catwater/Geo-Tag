package com.geotag.tagx5.geotag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.persistence.BackendlessDataQuery;

import java.util.ArrayList;
import java.util.List;

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
    private List<BackendlessUser> userSet;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mScore = (TextView) rootView.findViewById(R.id.text_score);
        mLivesRemaining = (TextView) rootView.findViewById(R.id.text_lives_remaining);

        //display user's lives remaining & score
        BackendlessUser k = Backendless.UserService.CurrentUser();
        mLivesRemaining.setText("Lives Remaining: " + k.getProperty("livesRemaining"));
        mScore.setText("Score: " + k.getProperty("score"));
        downloadOtherUsers();

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

    public void downloadOtherUsers(){
        BackendlessDataQuery bdq = new BackendlessDataQuery();
        LoadingCallback<BackendlessCollection<BackendlessUser>> lcbcbu = returnUsers();
        Backendless.Persistence.of(BackendlessUser.class).find(bdq, lcbcbu);

    }

    public LoadingCallback<BackendlessCollection<BackendlessUser>> returnUsers(){
        return new LoadingCallback<BackendlessCollection<BackendlessUser>>(getActivity(), "todo ") {
            @Override
            public void handleResponse(BackendlessCollection<BackendlessUser> response) {
                super.handleResponse(response);
                userSet = response.getData();

                for(BackendlessUser bu : userSet){
                    Log.e(TAG, "onCreateView: "+ bu.getProperty("username").toString() );
                }

                //TODO: ADD THIS STUFF INTO SHOOT BUTTON LISTENERNENERNERNRNRNNRRR
                BackendlessUser curr = Backendless.UserService.CurrentUser();

                for(BackendlessUser bu : userSet){
                    if (Math.abs(Integer.valueOf(bu.getProperty("latitude").toString()) - Integer.valueOf(curr.getProperty("latitude").toString())) < 100
                            && Math.abs(Integer.valueOf(bu.getProperty("longitude").toString()) - Integer.valueOf(curr.getProperty("longitude").toString())) < 100
                            && !(bu.getProperty("username").equals(curr.getProperty("username"))))
                    {
                        bu.setProperty("score", (Integer.valueOf(bu.getProperty("score").toString())) - 1);
                        bu.setProperty("livesRemaining", Integer.valueOf(bu.getProperty("livesRemaining").toString()) - 1);
                        curr.setProperty("score", Integer.valueOf(curr.getProperty("score").toString()) + 1);
                    }
                    Log.e(TAG, "onCreateView: "+ bu.getProperty("score").toString() );
                }
            }
        };
    }

}
