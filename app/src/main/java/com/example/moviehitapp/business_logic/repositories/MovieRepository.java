package com.example.moviehitapp.business_logic.repositories;

import com.example.moviehitapp.business_logic.caches.IMovieCache;
import com.example.moviehitapp.business_logic.dao.MovieDao;
import com.example.moviehitapp.constants.ApiConstants;
import com.example.moviehitapp.model.MoviesResponse;
import com.example.moviehitapp.network.webservices.TMDBWebService;

import javax.inject.Inject;

import io.reactivex.Single;

public class MovieRepository implements IMovieRepository {
    //webservice
    private final TMDBWebService tmdbWebService;
    //cache
    private IMovieCache movieCache;
    //dao
    private MovieDao movieDao;

    @Inject
    public MovieRepository(TMDBWebService tmdbWebService, IMovieCache movieCache, MovieDao movieDao) {
        this.tmdbWebService = tmdbWebService;
        this.movieCache = movieCache;
        this.movieDao = movieDao;
    }
    @Override
    public Single<MoviesResponse> findData() {
        return tmdbWebService.getPopularMovies(ApiConstants.key).cache();
    }
    @Override
    public Single<MoviesResponse> findHighestRatedMovies() {
        return tmdbWebService.getTopRatedMovies(ApiConstants.key).cache();
    }
}
