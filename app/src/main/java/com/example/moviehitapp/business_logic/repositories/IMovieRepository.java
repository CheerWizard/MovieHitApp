package com.example.moviehitapp.business_logic.repositories;

import com.example.moviehitapp.business_logic.data.MoviesResponse;

import io.reactivex.Single;

public interface IMovieRepository extends IRepository<MoviesResponse, Integer> {
    Single<MoviesResponse> findHighestRatedMovies();
    Single<MoviesResponse> findPopularMovies();
}
