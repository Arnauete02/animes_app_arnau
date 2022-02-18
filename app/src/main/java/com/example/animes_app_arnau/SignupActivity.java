package com.example.animes_app_arnau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText passText;
    private EditText phoneText;
    private Button btSignUp1;
    private Button btLogin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        hook();

        //TODO: onClickListener btLogin1

        //TODO: onCLickListener btSignUp1

    }

    private void hook(){
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        passText = findViewById(R.id.passText);
        phoneText = findViewById(R.id.phoneText);
        btSignUp1 = findViewById(R.id.btSignUp1);
        btLogin1 = findViewById(R.id.btLogin1);
    }
}