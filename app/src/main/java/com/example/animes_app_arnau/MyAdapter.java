package com.example.animes_app_arnau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Anime> mAnimes;
    private Context mContext;

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

        Picasso.get().load(mAnimes.get(position).getFavorite())
                .fit()
                .centerCrop()
                .into(holder.favorite);
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
