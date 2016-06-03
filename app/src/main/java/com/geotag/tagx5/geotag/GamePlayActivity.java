package com.geotag.tagx5.geotag;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.*;

public class GamePlayActivity extends FragmentActivity{


    private GoogleApiClient mGoogleApiClient;
    public static final String TAG ="GamePlayActivity";
    private com.google.android.gms.maps.MapFragment mapFragment;
    private MapSuperDuperFragment msdf;
    //private Button mButtonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);

        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag("HomeFragment")== null)
            fm.beginTransaction()
                    .add(R.id.home_fragment_container, new HomeFragment(), "HomeFragment")//.add(R.id.rel_layout_container, new GamePlayActivity(), "") //UGHHH
                    .commit();


        //mButtonUpload = (Button) findViewById(R.id.button);
       // mButtonUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                backendlessUser.setProperty("latitude",latBlaster);
//                backendlessUser.setProperty("longitude",longDoink);
//                backendlessUser.setProperty("firstName","poopy");
//                Backendless.UserService.update(backendlessUser, new BackendlessCallback<BackendlessUser>() {
//                    @Override
//                    public void handleResponse(BackendlessUser response) {
//
//                    }
//                });
//                Log.e("ZZOOOBA", "onClick: did it " + backendlessUser.getProperty("firstName") );
//            }
//        });
        Log.e("", "onCreate: ");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        msdf = new MapSuperDuperFragment();
        mapFragment = (com.google.android.gms.maps.MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        if(fm.findFragmentByTag("map")== null)
            fm.beginTransaction()
                    .add(R.id.map, msdf, "MapSuperDuperFragment")
                    .commit();




    }
    public void performFunction(View v){
        msdf.shoot();
    }

}
