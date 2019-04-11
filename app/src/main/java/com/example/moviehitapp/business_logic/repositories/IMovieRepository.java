package com.example.moviehitapp.business_logic.repositories;

import com.example.moviehitapp.model.MoviesResponse;

import io.reactivex.Single;

public interface IMovieRepository extends IRepository<MoviesResponse, Integer> {
    Single<MoviesResponse> findHighestRatedMovies();
}
