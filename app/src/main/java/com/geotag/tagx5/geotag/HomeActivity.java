//package com.geotag.tagx5.geotag;
//
//import android.os.Bundle;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//
//import com.backendless.Backendless;
//
///**
// * Created by csastudent2015 on 4/14/16.
// */
//public class HomeActivity extends AppCompatActivity {
//
//    private int score;
//    private int numLives;
//    private String username;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_home);
//
//        Backendless.initApp( this, BackendSettings.APPLICATION_ID,
//                BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION );
//
//        FragmentManager fm = getSupportFragmentManager();
//        if(fm.findFragmentByTag("TripFragment")== null)
//            fm.beginTransaction()
//                    .add(R.id.home_fragment_container, new HomeFragment(), "TripListFragment")
//                    .commit();
//
//    }
//
//
//}
