package com.example.animes_app_arnau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText emailText2;
    private EditText passText2;
    private Button btLogin2;
    private Button btSignUp2;

    private static String LOGIN_USER_URL;
    public static String EXTRA_TEXT_EMAIL = "com.example.animes_app_arnau.EXTRA_TEXT_EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hook();

        btLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        btSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginUser() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("tag", "Loged");
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        intent.putExtra(EXTRA_TEXT_EMAIL, emailText2.getText().toString());
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.getMessage());
                        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                        startActivity(intent);
                    }
                }
        );
        queue.add(objectRequest);
    }

    private String url() {
        if (!emailText2.getText().toString().isEmpty() || !passText2.getText().toString().isEmpty()){
            String email = emailText2.getText().toString();
            String pass = passText2.getText().toString();
            LOGIN_USER_URL = "https://joanseculi.com/edt69/loginuser.php?email="+ email +"&password="+ pass;
            return LOGIN_USER_URL;
        } else if (emailText2.getText().toString().isEmpty()) {
            emailText2.setError("Required");
        } else if (passText2.getText().toString().isEmpty()) {
            passText2.setError("Required");
        }
        return null;
    }

    private void hook() {
        emailText2 = findViewById(R.id.emailText2);
        passText2 = findViewById(R.id.passText2);
        btLogin2 = findViewById(R.id.btLogin2);
        btSignUp2 = findViewById(R.id.btSignUp2);
    }
}