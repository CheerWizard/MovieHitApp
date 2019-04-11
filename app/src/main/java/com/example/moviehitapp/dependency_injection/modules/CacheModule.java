package com.example.moviehitapp.dependency_injection.modules;

import android.util.Log;

import com.example.moviehitapp.business_logic.caches.IMovieCache;
import com.example.moviehitapp.business_logic.caches.MovieCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class CacheModule {
    @Singleton
    @Provides
    static IMovieCache provideMovieCache() {
        Log.d("cache module" , "started");
        return new MovieCache();
    }
}
