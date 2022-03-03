package com.example.animes_app_arnau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Intent intent;

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passEditText;
    private EditText phoneEditText;
    private Button btUpdate;
    private Button btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        hook();

        setSupportActionBar(toolbar);

        //TODO: UPDATE USER

        //TODO: DELETE USER
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu:
                intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
                return true;
            case R.id.profile:
                intent = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intent);
                return true;
            case R.id.like:
                intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hook() {
        toolbar = findViewById(R.id.toolbar);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passEditText = findViewById(R.id.passEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
    }
}