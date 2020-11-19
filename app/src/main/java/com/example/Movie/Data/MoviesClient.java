package com.example.Movie.Data;

import com.example.Movie.Model.CastData;
import com.example.Movie.Model.Movie;
import com.example.Movie.Model.MovieDetailsObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesClient {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesClient instance;
    private MoviesInterface moviesInterface;

    public MoviesClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviesInterface = retrofit.create(MoviesInterface.class);
    }

    public static MoviesClient getInstance() {
        if (instance == null)
            instance = new MoviesClient();

        return instance;
    }

    public Call<Movie> getMovies(String API, String sort) {
        return moviesInterface.getMovies(API, sort);
    }

    public Call<MovieDetailsObject> getMoviesDetails(int movieId, String api) {
        return moviesInterface.getMovieDetails(movieId, api);
    }

    public Call<CastData> getMoviesCast(int movieId, String api) {
        return moviesInterface.getMovieCredits(movieId, api);
    }
}
