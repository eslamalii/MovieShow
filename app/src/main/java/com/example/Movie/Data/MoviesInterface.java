package com.example.Movie.Data;

import com.example.Movie.Model.Movie;
import com.example.Movie.Model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviesInterface {

    @GET("3/discover/movie?api_key=fd36c8251c4b668a206e976e0ca52a52&sort_by=popularity.desc")
    Call<Movie> getMovies();

}
