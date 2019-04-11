package com.example.moviehitapp.business_logic.repositories;

import com.example.moviehitapp.business_logic.dao.MovieResponseDao;
import com.example.moviehitapp.constants.ApiConstants;
import com.example.moviehitapp.business_logic.data.MoviesResponse;
import com.example.moviehitapp.network.webservices.TMDBWebService;
import com.example.moviehitapp.utils.ConnectivityUtils;

import javax.inject.Inject;

import io.reactivex.Single;

public class MovieRepository implements IMovieRepository {
    //webservice
    private final TMDBWebService tmdbWebService;
    //dao
    private MovieResponseDao movieResponseDao;

    @Inject
    public MovieRepository(TMDBWebService tmdbWebService, MovieResponseDao movieResponseDao) {
        this.tmdbWebService = tmdbWebService;
        this.movieResponseDao = movieResponseDao;
    }
    @Override
    public synchronized Single<MoviesResponse> findHighestRatedMovies() {
        if (ConnectivityUtils.isInternetConnected()) return tmdbWebService.getTopRatedMovies(ApiConstants.key).cache();
        else return movieResponseDao.select();
    }
    @Override
    public synchronized Single<MoviesResponse> findPopularMovies() {
        if (ConnectivityUtils.isInternetConnected()) return tmdbWebService.getPopularMovies(ApiConstants.key).cache();
        else return movieResponseDao.select();
    }
    @Override
    public synchronized void saveData(MoviesResponse moviesResponse) {
        movieResponseDao.insert(moviesResponse);
    }
    @Override
    public synchronized void updateData(MoviesResponse moviesResponse) {
        movieResponseDao.update(moviesResponse);
    }
    @Override
    public synchronized void deleteData(MoviesResponse moviesResponse) {
        movieResponseDao.delete(moviesResponse);
    }
}
