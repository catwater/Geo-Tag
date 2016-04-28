package com.geotag.tagx5.geotag;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class GamePlayActivity extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private LocationRequest locationRequest;
    private MarkerOptions markerOptions;
    private Marker mMarker;
    private Button mButtonUpload;
    private BackendlessUser backendlessUser;
    private Double latBlaster = 30.0;
    private Double longDoink = 30.0;

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        backendlessUser = Backendless.UserService.CurrentUser();

        setContentView(R.layout.activity_maps);


        FragmentManager fm = getSupportFragmentManager();
        if(fm.findFragmentByTag("HomeFragment")== null)
            fm.beginTransaction()
                    .add(R.id.home_fragment_container, new HomeFragment(), "HomeFragment")
                    .commit();


        mButtonUpload = (Button) findViewById(R.id.button);
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backendlessUser.setProperty("latitude",latBlaster);
                backendlessUser.setProperty("longitude",longDoink);
                backendlessUser.setProperty("firstName","poopy");
                Backendless.UserService.update(backendlessUser, new BackendlessCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {

                    }
                });
                Log.e("ZZOOOBA", "onClick: did it " + backendlessUser.getProperty("firstName") );
            }
        });
        Log.e("", "onCreate: ");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        setLocationRequest();


    }
    protected void setLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        //locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //locationRequest.setSmallestDisplacement(5);
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, new com.google.android.gms.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mMarker.remove();
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                        mGoogleApiClient);
                LatLng here = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
                Log.e("TAG", "" + mLastLocation.getLatitude() );
                Log.e("TAG", "" + mLastLocation.getLongitude() );
                longDoink = mLastLocation.getLongitude();
                latBlaster = mLastLocation.getLatitude();


                Log.e("", "onCreate: " );
                mMarker = mMap.addMarker(markerOptions.position(here).title("Marker here"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(here,20f));
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-33.8675, 151.2070 );
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        LatLng here = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
        markerOptions = new MarkerOptions().position(here).title("Marker here");

        //mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
        mMarker = mMap.addMarker(markerOptions);
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    public boolean sendLocationDataToBackendless(){
        final BackendlessPoint playerPoint = new BackendlessPoint(mLastLocation.getLatitude(),mLastLocation.getLongitude());
        Backendless.Persistence.save(playerPoint, new BackendlessCallback<BackendlessPoint>() {
            @Override
            public void handleResponse(BackendlessPoint point)
            {
                Log.i( "uploaded"," point " + point.toString() + " for ");
            }
        });
        return true;
    }

    public Player createPlayer(String UserId){
        BackendlessUser b= Backendless.UserService.findById(UserId);
        b.getProperty("username");
        Player p = new Player(3, (double) b.getProperty("latitude"), (double) b.getProperty("longitude"), b.getProperty("username").toString());
        return p;
    }
}
