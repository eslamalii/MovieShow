package com.example.Movie.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Movie.Data.MoviesClient;
import com.example.Movie.Model.MovieDetailsObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel {
    public MutableLiveData<MovieDetailsObject> detailsMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> busy;

    public MutableLiveData<Integer> getBusy() {
        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }
        return busy;
    }

    public void getMovieDetails(int movieId, String apiKey) {
        getBusy().setValue(0); //visible
        MoviesClient.getInstance().getMoviesDetails(movieId, apiKey).enqueue(new Callback<MovieDetailsObject>() {
            @Override
            public void onResponse(Call<MovieDetailsObject> call, Response<MovieDetailsObject> response) {
                MovieDetailsObject movieDetails = response.body();
                if (movieDetails != null)
                    detailsMutableLiveData.setValue(movieDetails);

                getBusy().setValue(8); //invisible
            }

            @Override
            public void onFailure(Call<MovieDetailsObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
