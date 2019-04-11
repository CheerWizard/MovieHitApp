package com.example.moviehitapp.business_logic.caches;

import com.example.moviehitapp.model.MoviesResponse;

import io.reactivex.Single;

public interface IMovieCache extends ICache<Integer , Single<MoviesResponse>> {
    boolean isEmpty();
    boolean contains(Integer integer);
}
