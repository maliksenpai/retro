package com.example.retro;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("2.0//?method=tag.gettoptracks&tag=disco&api_key=abe9839f793d51055ef6e52e3decd49b&limit=20&format=json")
    Call<Gelenler> getMovieDetails();

}
