package com.geotag.tagx5.geotag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by csastudent2015 on 4/14/16.
 */
public class HomeFragment extends Fragment {

    private TextView mScore;
    private TextView mLivesRemaining;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mScore = (TextView) rootView.findViewById(R.id.text_score);
        mLivesRemaining = (TextView) rootView.findViewById(R.id.text_lives_remaining);


        Intent i = new Intent();
        i = getActivity().getIntent();

        return rootView;
    }


}
