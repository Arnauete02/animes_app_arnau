package com.example.animes_app_arnau;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText2;
    private EditText passText2;
    private Button btLogin2;
    private Button btSignUp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hook();

        //TODO: onClickListener btLogin2

        //TODO: onCLickListener btSignUp2

    }

    private void hook() {
        emailText2 = findViewById(R.id.emailText2);
        passText2 = findViewById(R.id.passText2);
        btLogin2 = findViewById(R.id.btLogin2);
        btSignUp2 = findViewById(R.id.btSignUp2);
    }
}