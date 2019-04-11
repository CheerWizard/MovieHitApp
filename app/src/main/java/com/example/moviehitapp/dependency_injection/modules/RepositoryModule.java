package com.example.moviehitapp.dependency_injection.modules;

import android.util.Log;

import com.example.moviehitapp.business_logic.dao.MovieResponseDao;
import com.example.moviehitapp.business_logic.repositories.IMovieRepository;
import com.example.moviehitapp.business_logic.repositories.MovieRepository;
import com.example.moviehitapp.network.webservices.TMDBWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {NetworkModule.class , StorageModule.class})
public class RepositoryModule {
    @Singleton
    @Provides
    static IMovieRepository provideMovieRepository(TMDBWebService tmdbWebService , MovieResponseDao movieResponseDao) {
        Log.d("repository module" , "started");
        return new MovieRepository(tmdbWebService, movieResponseDao);
    }
}
