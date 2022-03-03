package com.example.animes_app_arnau;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Anime> mAnimes;
    private Context mContext;
    private int count = 0;

    public MyAdapter(Context mContext, List<Anime> mAnimes) {
        this.mAnimes = mAnimes;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mAnimes.get(position).getName());
        holder.description.setText(mAnimes.get(position).getDescription());
        holder.year.setText(mAnimes.get(position).getYear());
        holder.type.setText(mAnimes.get(position).getType());

        Picasso.get().load(mAnimes.get(position).getImage())
                .fit()
                .centerCrop()
                .into(holder.image);

        holder.favorite.setImageResource(R.drawable.like_off);

        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 0){
                    holder.favorite.setImageResource(R.drawable.like_off);
                    count++;

                    String animeV = holder.name.toString();
                    String emailV = LoginActivity.EXTRA_TEXT_EMAIL;

                    RequestQueue queue = Volley.newRequestQueue(mContext.getApplicationContext());
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST,
                            "https://www.joanseculi.com/edt69/deletefavorite.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("favorite deleted")) {
                                        Log.d("tag", response);
                                    } else {
                                        Log.d("tag", response);
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
                            params.put("email", emailV);
                            params.put("anime", animeV);
                            return params;
                        }
                    };
                    queue.add(stringRequest);
                } else {
                    holder.favorite.setImageResource(R.drawable.like_on);
                    count--;

                    String animeV = holder.name.toString();
                    String emailV = LoginActivity.EXTRA_TEXT_EMAIL;

                    RequestQueue queue = Volley.newRequestQueue(mContext.getApplicationContext());
                    StringRequest stringRequest = new StringRequest(
                            Request.Method.POST,
                            "https://joanseculi.com/edt69/insertfavorite.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("favorite inserted")) {
                                        Log.d("tag", response);
                                    } else {
                                        Log.d("tag", response);
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
                            params.put("email", emailV);
                            params.put("anime", animeV);
                            return params;
                        }
                    };
                    queue.add(stringRequest);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAnimes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView description;
        TextView year;
        TextView type;
        ImageView favorite;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            year = itemView.findViewById(R.id.year);
            type = itemView.findViewById(R.id.type);
            favorite = itemView.findViewById(R.id.favorite);
        }
    }
}
