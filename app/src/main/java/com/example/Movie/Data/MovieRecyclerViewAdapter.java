package com.example.Movie.Data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Movie.Activities.MovieDetails;
import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Results;
import com.example.Movie.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Results> moviesList = new ArrayList<>();



    private OnMovieListener onMovieListener;

    public MovieRecyclerViewAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false), onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder holder, int position) {

        Results movie = moviesList.get(position);
        String posterLink = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();

        holder.name.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());

        Picasso.get()
                .load(posterLink)
                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public void setMoviesList(ArrayList<Results> list) {
        this.moviesList = list;
        notifyDataSetChanged();
    }

    public ArrayList<Results> getMoviesList() {
        return moviesList;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;
        TextView name;
        TextView overview;
        OnMovieListener onMovieListener;

        public ViewHolder(@NonNull final View view, OnMovieListener onMovieListener) {
            super(view);

            poster = view.findViewById(R.id.moviePosterID);
            name = view.findViewById(R.id.movieNameID);
            overview = view.findViewById(R.id.movieOverviewID);
            this.onMovieListener = onMovieListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMovieListener.OnMovieClick(getAdapterPosition());
        }
    }

    public interface OnMovieListener {
        void OnMovieClick(int position);
    }
}