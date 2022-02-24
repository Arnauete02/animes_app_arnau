package com.example.animes_app_arnau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText emailText;
    private EditText passText;
    private EditText phoneText;
    private Button btSignUp1;
    private Button btLogin1;

    private static String CREATE_USER_URL = "https://joanseculi.com/edt69/createuser2.php";

    private String nameV;
    private String emailV;
    private String passwordV;
    private String phoneV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        hook();

        btLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btSignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nameText.getText().toString().isEmpty() || !emailText.getText().toString().isEmpty()
                        || !passText.getText().toString().isEmpty() || !phoneText.getText().toString().isEmpty()){
                    createUser();
                } else if (nameText.getText().toString().isEmpty()) {
                    nameText.setError("Required");
                } else if (emailText.getText().toString().isEmpty()) {
                    emailText.setError("Required");
                } else if (passText.getText().toString().isEmpty()) {
                    passText.setError("Required");
                } else if (phoneText.getText().toString().isEmpty()) {
                    phoneText.setError("Required");
                }
            }
        });
    }

    private void createUser() {
        nameV = nameText.getText().toString();
        emailV = emailText.getText().toString();
        passwordV = passText.getText().toString();
        phoneV = phoneText.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                CREATE_USER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("User created")) {
                            Log.d("tag", response);
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d("tag", response);
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", nameV);
                params.put("email", emailV);
                params.put("password", passwordV);
                params.put("phone", phoneV);
                return params;
            }
        };
        queue.add(stringRequest);
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