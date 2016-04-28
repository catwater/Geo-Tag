package com.geotag.tagx5.geotag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;


public class SetupFragment extends Fragment {

    private TextView mGreeting;
    private TextView mJoinGameButton;
    private Button mContinueGameButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_setup, container, false);
        String name = getActivity().getIntent().getStringExtra("username");



        mGreeting = (TextView) rootView.findViewById(R.id.text_greeting);
        BackendlessUser b = Backendless.UserService.CurrentUser();
        mGreeting.setText("GREETINGS " + b.getProperty("username").toString().toUpperCase());
        mJoinGameButton = (TextView) rootView.findViewById(R.id.button_join_game);
        mContinueGameButton = (Button) rootView.findViewById(R.id.button_continue_game);

        mContinueGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GamePlayActivity.class);
                startActivity(intent);
            }
        });








        return rootView;
    }
/*
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
*/



}
