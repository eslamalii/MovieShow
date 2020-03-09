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
import com.example.Movie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Movie> moviesList;

    public MovieRecyclerViewAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.moviesList = movies;
    }

    @NonNull
    @Override
    public MovieRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRecyclerViewAdapter.ViewHolder holder, int position) {

        Movie movie = moviesList.get(position);
        String posterLink = "https://image.tmdb.org/t/p/w500" + movie.getPoster();

        Picasso.get()
                .load(posterLink)
                .into(holder.poster);

        holder.name.setText(movie.getTitle());


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;
        TextView name;

        public ViewHolder(@NonNull final View view, final Context ctx) {
            super(view);
            context = ctx;

            poster = view.findViewById(R.id.moviePosterID);
            name = view.findViewById(R.id.movieNameID);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Movie movie = moviesList.get(getAdapterPosition());

                    Intent intent = new Intent(context, MovieDetails.class);

                    intent.putExtra("movie", movie);
                    ctx.startActivity(intent);


                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
