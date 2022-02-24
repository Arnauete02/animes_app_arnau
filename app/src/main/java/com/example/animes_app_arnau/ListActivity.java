package com.example.animes_app_arnau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    List animes = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        hook();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getAnimes();
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
                return true;
            case R.id.profile:
                return true;
            case R.id.like:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hook() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getAnimes(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url(),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray animesArray = response.getJSONArray("animes");
                            for (int i = 0; i < response.length(); i++){
                                JSONObject jsonObject = animesArray.getJSONObject(i);
                                Anime anime = new Anime();
                                anime.setName(jsonObject.getString("name"));
                                anime.setDescription(jsonObject.getString("description"));
                                anime.setYear(jsonObject.getInt("year"));
                                anime.setType(jsonObject.getString("type"));
                                anime.setImage(jsonObject.getString("image"));
                                anime.setFavorite(jsonObject.getString("favorite"));
                                animes.add(anime);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        myAdapter = new MyAdapter(getApplicationContext(), animes);
                        recyclerView.setAdapter(myAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag", "onErrorResponse: "+ error.getMessage());
                    }
                }
        );
        queue.add(jsonObjectRequest);
    }

    private String url() {
        String email = getIntent().getStringExtra(LoginActivity.EXTRA_TEXT_EMAIL);
        String url = "https://joanseculi.com/edt69/animes2.php?email"+ email;
        return url;
    }
}