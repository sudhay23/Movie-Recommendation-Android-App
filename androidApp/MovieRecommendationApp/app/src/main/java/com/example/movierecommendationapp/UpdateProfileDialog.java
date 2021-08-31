package com.example.movierecommendationapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;


public class UpdateProfileDialog extends AppCompatDialogFragment {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextAge;

    private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.editprofile_dialog, null);

        builder.setView(view)
                .setTitle("Update Profile")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editTextName.getText().toString();
                        String email= editTextEmail.getText().toString();
                        String password = editTextPassword.getText().toString();
                        String age = editTextAge.getText().toString();
                        listener.applyTexts(name,email,password,age);
                    }
                });

        editTextName = view.findViewById(R.id.dialogUserName);
        editTextEmail = view.findViewById(R.id.dialogUserEmail);
        editTextPassword = view.findViewById(R.id.dialogUserPassword);
        editTextAge = view.findViewById(R.id.dialogUserAge);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener {
        void applyTexts(String name, String email, String username, String password);
    }
}
