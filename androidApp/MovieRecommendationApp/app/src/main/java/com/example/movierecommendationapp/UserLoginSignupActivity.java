package com.example.movierecommendationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserLoginSignupActivity extends AppCompatActivity {
    Button signupFragTrigger, loginFragTrigger;
    TextView fillerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_signup);
        signupFragTrigger = findViewById(R.id.signupFragTrigger);
        loginFragTrigger = findViewById(R.id.loginFragTrigger);
        fillerText = findViewById(R.id.fillerText);

        loginFragTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillerText.setVisibility(View.GONE);
                loadFragment(new LoginFragment());

            }
        });

        signupFragTrigger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillerText.setVisibility(View.GONE);
                loadFragment(new SignupFragment());
            }
        });

    }

    protected void loadFragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentHolder,fragment);
        ft.commit();
    }
}