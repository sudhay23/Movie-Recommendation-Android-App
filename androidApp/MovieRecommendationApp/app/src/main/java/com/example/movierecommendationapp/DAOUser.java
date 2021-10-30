package com.example.movierecommendationapp;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUser {
    private DatabaseReference databaseReference;

    public DAOUser()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
    }

    //To add a new user upon registration
    public Task<Void> addUser(User new_user)
    {
        return databaseReference.push().setValue(new_user);
    }
}
