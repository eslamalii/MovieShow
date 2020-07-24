package com.example.Movie.Data;

import com.example.Movie.Model.Movie;
import com.example.Movie.Model.MovieDetailsObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesInterface {

    @GET("discover/movie")
    Call<Movie> getMovies(
            @Query("api_key") String apiKey,
            @Query("sort_by") String sort);


//    @GET("3/movie/{Movie_ID}?api_key=fd36c8251c4b668a206e976e0ca52a52&language=en-US")
    @GET("movie/{movie_id}")
    Call<MovieDetailsObject> getMovieDetails(
            @Path("movie_id") Integer movieId,
            @Query("api_key") String apiKey
    );

}
