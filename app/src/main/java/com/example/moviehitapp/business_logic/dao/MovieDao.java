package com.example.moviehitapp.business_logic.dao;

import com.example.moviehitapp.business_logic.data.Movie;

import java.util.List;

import javax.inject.Singleton;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
@Singleton
public interface MovieDao {
    @Query("SELECT * FROM movie WHERE id LIKE :id")
    Movie select(int id);
    @Query("SELECT * FROM movie")
    List<Movie> selectAll();
    @Insert
    void insert(Movie movie);
    @Update
    void update(Movie movie);
    @Delete
    void delete(Movie movie);
}
