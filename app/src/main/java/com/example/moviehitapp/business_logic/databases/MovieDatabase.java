package com.example.moviehitapp.business_logic.databases;

import com.example.moviehitapp.utils.converters.GenreIdsConverter;
import com.example.moviehitapp.business_logic.dao.MovieDao;
import com.example.moviehitapp.business_logic.data.Movie;

import javax.inject.Singleton;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Singleton
@Database(entities = {Movie.class} ,
        version = 1 , exportSchema = false)
@TypeConverters({GenreIdsConverter.class})
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
