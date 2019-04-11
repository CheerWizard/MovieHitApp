package com.example.moviehitapp.dependency_injection.modules;

import android.util.Log;

import com.example.moviehitapp.business_logic.dao.MovieDao;
import com.example.moviehitapp.business_logic.databases.MovieDatabase;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = {DatabaseModule.class})
public class DaoModule {
    @Singleton
    @Provides
    static MovieDao provideMovieDao(@NonNull MovieDatabase movieDatabase) {
        Log.d("dao module" , "started");
        return movieDatabase.movieDao();
    }
}
