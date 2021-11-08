package com.example.movierecommendationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements UpdateProfileDialog.ExampleDialogListener{
    private TextView textViewName;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private TextView textViewAge;
    private TextView textViewPhno;

    private Button btnUpdateProfile;
    private Button btnSurfMovies;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewName = (TextView) findViewById(R.id.userName) ;
        textViewEmail = (TextView) findViewById(R.id.userEmail);
        textViewPassword = (TextView) findViewById(R.id.userPassword);
        textViewAge = (TextView) findViewById(R.id.userAge);
        textViewPhno = (TextView) findViewById(R.id.userPhone);

        btnUpdateProfile = (Button) findViewById(R.id.updateProfileBtn);
        btnSurfMovies = (Button) findViewById(R.id.surfMoviesBtn);

        setProfileDetails();

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        btnSurfMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),movieslist.class);
//                startActivity(intent);
                finish();
            }
        });


    }

    private void setProfileDetails() {
        preferences = getApplicationContext().getSharedPreferences("Register", Context.MODE_PRIVATE);
        String registeredName = preferences.getString("Name", "passwordNotMatching");
        String registeredPhone = preferences.getString("Phno", "passwordNotMatching");
        String registeredAge = preferences.getString("Age", "passwordNotMatching");

        String registeredEmail = preferences.getString("Email", "emailNotRegistered");
        String registeredPassword = preferences.getString("Password", "passwordNotMatching");

        textViewName.setText(registeredName);
        textViewEmail.setText(registeredEmail);
        textViewPassword.setText(registeredPassword);
        textViewAge.setText(registeredAge);
        textViewPhno.setText(registeredPhone);
    }

    public void openDialog() {
        UpdateProfileDialog dialog = new UpdateProfileDialog();
        dialog.show(getSupportFragmentManager(), "Update Profile");
    }

    @Override
    public void applyTexts(String name,String phno, String password, String age) {
       /* textViewName.setText(name);
        textViewPhno.setText(phone);
        textViewPassword.setText(password);
        textViewAge.setText(age);
        */
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("Register", Context.MODE_PRIVATE);


        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Name",name);
        editor.putString("Password",password);
        editor.putString("Age",age);
        editor.putString("Phno",phno);
        editor.commit();
        setProfileDetails();
    }
}