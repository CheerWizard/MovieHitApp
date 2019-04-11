package com.example.moviehitapp.network.webservices;

import com.example.moviehitapp.annotations.WebService;
import com.example.moviehitapp.model.MoviesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

@WebService
public interface TMDBWebService {
    //It will make http GET request to corresponding server entity and map param
    //result is popular movies
    @GET("movie/popular")
    Single<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);
    //It will make http GET request to corresponding server entity and map param
    //result is top rated movies
    @GET("movie/top_rated")
    Single<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
