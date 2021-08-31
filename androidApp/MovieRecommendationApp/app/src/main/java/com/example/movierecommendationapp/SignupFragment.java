package com.example.movierecommendationapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignupFragment extends Fragment {
    boolean isUserValidated = false;
    Button validateBtn, clearBtn, registerBtn, profilePicBtn;
    EditText nameEditText,emailEditText,ageEditText,phoneEditText,passwordEditText,reenterpasswordEditText;
    TextView validationStatusTv;
    ImageView dpImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        validateBtn = view.findViewById(R.id.validateBtn);
        clearBtn = view.findViewById(R.id.clearBtn);
        registerBtn = view.findViewById(R.id.registerBtn);
        profilePicBtn = view.findViewById(R.id.profilePicBtn);
        dpImageView = view.findViewById(R.id.dpImageView);
        nameEditText = view.findViewById(R.id.nameEditText);
        emailEditText = view.findViewById(R.id.emailEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        phoneEditText = view.findViewById(R.id.phoneEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        reenterpasswordEditText = view.findViewById(R.id.reenterpasswordEditText);

        validationStatusTv = view.findViewById(R.id.validationStatusTv);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Clear Fields");
                alertDialog.setMessage("Are you sure to clear?");
                alertDialog.setIcon(R.drawable.ic_baseline_warning_24);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nameEditText.setText("");
                        emailEditText.setText("");
                        ageEditText.setText("");
                        phoneEditText.setText("");
                        passwordEditText.setText("");
                        reenterpasswordEditText.setText("");
                        isUserValidated = false;
                        validationStatusTv.setText("NOT VALIDATED");
                        validationStatusTv.setTextColor(Color.RED);
                        registerBtn.setEnabled(false);
                    }
                });

                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });


        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String age = ageEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password1 = passwordEditText.getText().toString();
                String password2 = reenterpasswordEditText.getText().toString();

                if(name.length()==0 || email.length()==0 || age.length()==0 || phone.length()==0 || password1.length()==0 || password2.length()==0)
                {
                    Toast.makeText(getActivity(), "Not all fields were filled!", Toast.LENGTH_LONG).show();
                    isUserValidated = false;
                }
                else if(phone.length()!=10)
                {
                    Toast.makeText(getActivity(), "Phone no. should have 10 digits", Toast.LENGTH_LONG).show();
                    isUserValidated = false;
                }
                else if(email.indexOf('@')==-1 || email.indexOf('.')==-1 || email.indexOf('@')==email.length()-1 || email.indexOf('.')==email.length()-1)
                {
                    Toast.makeText(getActivity(), "Check Email Address Format", Toast.LENGTH_LONG).show();
                    isUserValidated = false;
                }
                else if(!password1.equals(password2))
                {
                    Toast.makeText(getActivity(), "Enter matching passwords", Toast.LENGTH_LONG).show();
                    isUserValidated = false;
                }
                else
                {
                    for(int i=0;i<name.length();i++)
                    {
                        if(!((name.charAt(i)>='a' && name.charAt(i)<='z')||(name.charAt(i)>='A' && name.charAt(i)<='Z')||(name.charAt(i)==' ')))
                        {
                            Toast.makeText(getActivity(), "Only letters allowed for name", Toast.LENGTH_LONG).show();
                            isUserValidated = false;
                            break;
                        }
                        else
                        {
                            isUserValidated = true;
                        }
                    }
                }
                if(phone.length()==10)
                {
                    try
                    {
                        Long.parseLong(phone);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getActivity(), "Phone no. should have digits only", Toast.LENGTH_LONG).show();
                        isUserValidated = false;
                    }
                }


                if(isUserValidated==true)
                {
                    validationStatusTv.setText("VALIDATED");
                    validationStatusTv.setTextColor(getResources().getColor(R.color.green));
                    registerBtn.setEnabled(true);
                    Toast.makeText(getActivity(),"All data validated, proceed to register",Toast.LENGTH_LONG).show();
                }
                else
                {
                    validationStatusTv.setText("NOT VALIDATED");
                    validationStatusTv.setTextColor(Color.RED);
                    registerBtn.setEnabled(false);
                }
            }
        });


        //Register Button Listener
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isUserValidated==true)
                {
                    Toast.makeText(getActivity(),"Registering User...Proceed to Login",Toast.LENGTH_SHORT).show();

                    //TEMPORARY ADDITION - Go To Login on Click
                    Button loginTriggerFragmentBtn = getActivity().findViewById(R.id.loginFragTrigger);
                    loginTriggerFragmentBtn.performClick();
                }
                else
                {
                    Toast.makeText(getActivity(),"Validate first before registering",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Profile Pic Button handler
        profilePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] options = { "Take Photo","Cancel" };
                AlertDialog.Builder dpAlertDialog = new AlertDialog.Builder(getActivity());
                dpAlertDialog.setTitle("Choose your profile picture");

                dpAlertDialog.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (options[item].equals("Take Photo")) {
                            Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePicture, 0);
                        } else if (options[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                dpAlertDialog.setCancelable(false);
                dpAlertDialog.show();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode != getActivity().RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == getActivity().RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        dpImageView.setImageBitmap(selectedImage);
                    }
                    break;
            }
        }
    }
}