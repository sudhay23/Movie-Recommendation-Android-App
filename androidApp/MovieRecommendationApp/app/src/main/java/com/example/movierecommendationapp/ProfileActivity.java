package com.example.movierecommendationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements UpdateProfileDialog.ExampleDialogListener{
    private TextView textViewName;
    private TextView textViewEmail;
    private TextView textViewPassword;
    private TextView textViewAge;
    private Button btnUpdateProfile;
    private Button btnSurfMovies;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textViewName = (TextView) findViewById(R.id.userName) ;
        textViewEmail = (TextView) findViewById(R.id.userEmail);
        textViewPassword = (TextView) findViewById(R.id.userPassword);
        textViewAge = (TextView) findViewById(R.id.userAge);
        btnUpdateProfile = (Button) findViewById(R.id.updateProfileBtn);
        btnSurfMovies = (Button) findViewById(R.id.surfMoviesBtn);

        btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        btnSurfMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ShowMoviesActivity.class);
                startActivity(intent);
            }
        });


    }

    public void openDialog() {
        UpdateProfileDialog dialog = new UpdateProfileDialog();
        dialog.show(getSupportFragmentManager(), "Update Profile");
    }

    @Override
    public void applyTexts(String username,String email, String password, String age) {
        textViewName.setText(username);
        textViewEmail.setText(email);
        textViewPassword.setText(password);
        textViewAge.setText(age);
    }
}