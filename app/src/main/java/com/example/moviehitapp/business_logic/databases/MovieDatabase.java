package com.example.moviehitapp.business_logic.databases;

import com.example.moviehitapp.business_logic.dao.MovieDao;
import com.example.moviehitapp.business_logic.dao.MovieResponseDao;
import com.example.moviehitapp.business_logic.data.Movie;
import com.example.moviehitapp.business_logic.data.MoviesResponse;

import javax.inject.Singleton;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Singleton
@Database(entities = {Movie.class , MoviesResponse.class} ,
        version = 1 , exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public abstract MovieResponseDao movieResponseDao();
}
