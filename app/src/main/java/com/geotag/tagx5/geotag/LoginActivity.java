package com.geotag.tagx5.geotag;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername;
    private EditText mPassword;
    private TextView mRegistrationButton;
    private Button mLoginButton;

    private static final int REGISTER_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //no onCreateView in activities
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.text_username); //cast
        mRegistrationButton = (TextView) findViewById(R.id.button_go_to_registration);
        mPassword = (EditText) findViewById(R.id.text_password);
        mLoginButton = (Button) findViewById(R.id.button_login);

        //needed for all Backendless Services
        Backendless.initApp(this, BackendSettings.APPLICATION_ID,
                BackendSettings.ANDROID_SECRET_KEY, BackendSettings.VERSION);

        mRegistrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivityForResult(intent, REGISTER_REQUEST_CODE);


            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //will go to different activity later
                //Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                //startActivityForResult(intent, REGISTER_REQUEST_CODE);

                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();


                LoadingCallback<BackendlessUser> loginCallback = createLoginCallback();
                loginCallback.showDialog();
                //wDialog();
                loginUser(username, password, loginCallback);


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REGISTER_REQUEST_CODE:

                    String username = data.getStringExtra(BackendlessUser.ID_KEY);
                    mUsername.setText(username);
                    mPassword.requestFocus();
                    Toast.makeText(this, " REGISTRATION SUCCESSFUL YAY YOU'RE A STAR", Toast.LENGTH_LONG).show();
            }
        }
    }

    public LoadingCallback<BackendlessUser> createLoginCallback() {
        return new LoadingCallback<BackendlessUser>(this, "Entering the space portal...") {
            @Override
            public void handleResponse(BackendlessUser user) {
                super.handleResponse(user);

                Intent i = new Intent(LoginActivity.this, SetupActivity.class);
                Toast.makeText(LoginActivity.this, user.getProperty("firstName").toString() + " you done bin logged in", Toast.LENGTH_LONG).show();
                startActivity(i);



            }
        };
    }

    public void loginUser(String username, String password, AsyncCallback<BackendlessUser> loginCallback) {
        Backendless.UserService.login(username, password, loginCallback);



    }



}
