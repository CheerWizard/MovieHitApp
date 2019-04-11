package com.example.moviehitapp.business_logic.dao;

import com.example.moviehitapp.business_logic.data.MoviesResponse;

import javax.inject.Singleton;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

@Dao
@Singleton
public interface MovieResponseDao {
    @Query("SELECT * FROM moviesresponse")
    Single<MoviesResponse> select();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MoviesResponse moviesResponse);
    @Update
    void update(MoviesResponse moviesResponse);
    @Delete
    void delete(MoviesResponse moviesResponse);
}
