 package com.example.Movie.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Movie.Model.Results;
import com.example.Movie.R;
import com.example.Movie.Util.Constants;
import com.squareup.picasso.Picasso;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
        String posterLink = Constants.IMAGE_URL + movie.getPoster_path();

        holder.movieName.setText(movie.getTitle());

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
        TextView movieName;
//        TextView genres;
        OnMovieListener onMovieListener;

        public ViewHolder(@NonNull final View view, OnMovieListener onMovieListener) {
            super(view);

            this.onMovieListener = onMovieListener;

            poster = view.findViewById(R.id.moviePosterID);
            movieName = view.findViewById(R.id.movieNameID);
//            genres = view.findViewById(R.id.genresID);

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