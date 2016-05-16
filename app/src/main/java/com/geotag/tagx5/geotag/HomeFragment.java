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

/**
 * Created by csastudent2015 on 4/14/16.
 */
public class HomeFragment extends Fragment {

    private TextView mScore;
    private TextView mLivesRemaining;
    private Button mLocUpdate;
    public static final String TAG = "HomeFragment";

    private static final String ARG_LATITUDE = "latitude";
    private static final String ARG_LONGITUDE = "longitude";

    private static final int REQUEST_LAT_LONG = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mScore = (TextView) rootView.findViewById(R.id.text_score);
        mLivesRemaining = (TextView) rootView.findViewById(R.id.text_lives_remaining);
        mLocUpdate = (Button) rootView.findViewById(R.id.button_location_update);


        mLocUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                MapSuperDuperFragment dialog = MapSuperDuperFragment.newInstance();
                dialog.setTargetFragment(HomeFragment.this, REQUEST_LAT_LONG);

            }
        });


        Intent i = new Intent();
        i = getActivity().getIntent();

        return rootView;
    }

    public static MapSuperDuperFragment newInstance(Double x, Double y){
        Bundle args = new Bundle();
        args.putSerializable(ARG_LATITUDE,x);
        args.putSerializable(ARG_LONGITUDE,y);

        MapSuperDuperFragment fragment = new MapSuperDuperFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public double returnth(){
        return 5.2;
    }


}
