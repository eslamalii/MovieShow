package com.example.Movie.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Movie.Data.MoviesClient;
import com.example.Movie.Model.Cast;
import com.example.Movie.Model.CastData;
import com.example.Movie.Model.Crew;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CastViewModel extends ViewModel {
    public MutableLiveData<List<Cast>> castLiveData = new MutableLiveData<>();


    public void getMovieCast(int movieID, String api) {
        MoviesClient.getInstance().getMoviesCast(movieID, api).enqueue(new Callback<CastData>() {
            @Override
            public void onResponse(Call<CastData> call, Response<CastData> response) {
                CastData castData = response.body();
                if (castData != null) {
                    List<Cast> casts = castData.getCast();
                    castLiveData.setValue(casts);
                }
            }

            @Override
            public void onFailure(Call<CastData> call, Throwable t) {

            }
        });
    }
}
