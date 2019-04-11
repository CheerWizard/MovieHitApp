package com.example.moviehitapp.dependency_injection.modules;

import android.util.Log;

import com.example.moviehitapp.business_logic.caches.IMovieCache;
import com.example.moviehitapp.business_logic.dao.MovieDao;
import com.example.moviehitapp.business_logic.repositories.IMovieRepository;
import com.example.moviehitapp.business_logic.repositories.MovieRepository;
import com.example.moviehitapp.network.webservices.TMDBWebService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {NetworkModule.class , StorageModule.class , CacheModule.class})
public class RepositoryModule {
    @Singleton
    @Provides
    static IMovieRepository provideMovieRepository(TMDBWebService tmdbWebService , IMovieCache movieCache , MovieDao movieDao) {
        Log.d("repository module" , "started");
        return new MovieRepository(tmdbWebService, movieCache, movieDao);
    }
}
