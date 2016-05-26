package com.geotag.tagx5.geotag;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.backendless.Backendless;

/**
 * Created by csastudent2015 on 4/14/16.
 */
public class SetupActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_setup);

        Backendless.initApp( this, BackendSettings.APPLICATION_ID,
                BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION );

       FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag("SetupFragment")== null)
            fm.beginTransaction()
                    .add(R.id.setup_fragment_container, new SetupFragment(), "HomeFragment")
                    .commit();

    }


}
