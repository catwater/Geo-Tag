package com.geotag.tagx5.geotag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.backendless.Backendless;

public class LoginActivity extends AppCompatActivity {
int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        number = 12;

        //needed for all Backendless Services
        Backendless.initApp(this, BackendSettings.APPLICATION_ID,
                BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION);
    }


}
