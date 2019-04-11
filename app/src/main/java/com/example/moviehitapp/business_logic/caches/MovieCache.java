package com.example.moviehitapp.business_logic.caches;

import com.example.moviehitapp.model.MoviesResponse;

import java.util.Map;

import io.reactivex.Single;

public class MovieCache extends AbstractCache<Integer , Single<MoviesResponse>> implements IMovieCache {
    //make access to default constructor
    public MovieCache() {
        super();
    }
    //optionally
    public MovieCache(Map<Integer, Single<MoviesResponse>> cache) {
        super(cache);
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public boolean contains(Integer integer) {
        return cache.containsKey(integer);
    }
}
