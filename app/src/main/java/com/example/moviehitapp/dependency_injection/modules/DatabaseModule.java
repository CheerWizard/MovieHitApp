package com.example.moviehitapp.dependency_injection.modules;

import android.content.Context;
import android.util.Log;

import com.example.moviehitapp.utils.managers.DatabaseManager;
import com.example.moviehitapp.business_logic.databases.MovieDatabase;
import com.example.moviehitapp.constants.SqlStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module(includes = ContextModule.class)
public class DatabaseModule {
    @Singleton
    @Provides
    static MovieDatabase provideMovieDatabase(Context context) {
        Log.d("database module" , "started");
        DatabaseManager.open(context , MovieDatabase.class , SqlStorage.Databases.MOVIE_DB);
        return (MovieDatabase) DatabaseManager.getRoomDataBase();
    }
}
