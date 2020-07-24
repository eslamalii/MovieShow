package com.example.Movie.Data;

import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Results;
import com.example.Movie.Util.Constants;

import java.util.List;

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

}
