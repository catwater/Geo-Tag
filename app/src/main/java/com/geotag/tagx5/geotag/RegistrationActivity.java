package com.geotag.tagx5.geotag;

/**
 * Created by csastudent2015 on 4/14/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;





public class RegistrationActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmailAddress;
    private EditText mFirstName;
    private EditText mLastName;
    private Button mSignButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* mUsername = (EditText) findViewById(R.id.enter_username);
        mPassword = (EditText) findViewById(R.id.enter_password);
        mEmailAddress = (EditText) findViewById(R.id.enter_email);
        mFirstName = (EditText) findViewById(R.id.enter_first_name);
        mLastName = (EditText) findViewById(R.id.enter_last_name);
        mSignButton = (Button) findViewById(R.id.signup_button); */

        //needed for all Backendless Services
        Backendless.initApp(this, BackendSettings.APPLICATION_ID,
                BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION);


        mSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                String emailAddress = mEmailAddress.getText().toString();
                String firstName = mFirstName.getText().toString();
                String lastName = mLastName.getText().toString();


                LoadingCallback<BackendlessUser> regCallback = createRegCallback();
                regCallback.showDialog();
                registerUser(firstName, lastName, username, emailAddress, password, regCallback);


            }
        });


    }

    public void registerUser(String firstName, String lastName, String username, String email,
                             String password, AsyncCallback<BackendlessUser> regCallback) {

        BackendlessUser user = new BackendlessUser();
        user.setEmail(email);
        user.setPassword(password);
        user.setProperty("firstName", firstName);
        user.setProperty("lastName", lastName);
        user.setProperty("username", username);

        //make the call to do the registration
        Backendless.UserService.register(user, regCallback);

    }

    public LoadingCallback<BackendlessUser> createRegCallback() {
        return new LoadingCallback<BackendlessUser>(this, "Registering...\npatience is a virtue") {
            @Override
            public void handleResponse(BackendlessUser user) {
                super.handleResponse(user);

                Intent registrationResult = new Intent();
                registrationResult.putExtra(BackendlessUser.ID_KEY, user.getProperty("username").toString());
                setResult(RESULT_OK, registrationResult);
                RegistrationActivity.this.finish();

            }
        };
    }
}

