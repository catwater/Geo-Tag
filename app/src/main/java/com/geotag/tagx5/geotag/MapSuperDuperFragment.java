package com.geotag.tagx5.geotag;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.backendless.BackendlessUser;
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

/**
 * Created by csastudent2015 on 5/4/16.
 */
public class MapSuperDuperFragment extends SupportMapFragment implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{
    private GoogleMap mMap;

    private Location mLastLocation;
    private LocationRequest locationRequest;
    private MarkerOptions markerOptions;
    private Marker mMarker;
    private GeographicPoint point;
    private static final String EXTRA_POINT = "com.geotag.tagx5.geotag.GeographicPoint";


    private GoogleApiClient mGoogleApiClient;

    private BackendlessUser backendlessUser;
    private Double latBlaster = 30.0;
    private Double longDoink = 30.0;
    private static final String ARG_LATITUDE = "latitude";
    private static final String ARG_LONGITUDE = "longitude";




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        point = new GeographicPoint(0,0);
        this.getMapAsync(this);
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
                point.setX(longDoink);
                point.setY(latBlaster);

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

    public static MapSuperDuperFragment newInstance(){
        MapSuperDuperFragment fragment = new MapSuperDuperFragment();
        return fragment;
    }

    private void sendResult(int resultCode,GeographicPoint gp){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_POINT,point);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }

}
