package com.example.moviehitapp.utils.converters;

import com.example.moviehitapp.business_logic.data.Movie;
import com.example.moviehitapp.utils.factories.GsonFactory;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

public final class MoviesConverter {
    @TypeConverter
    public static String fromMovies(List<Movie> movies) {
        return movies == null ? "" : GsonFactory.gson().toJson(movies);
    }
    @TypeConverter
    public static List<Movie> toMovies(String json) {
        return json == null ? Collections.emptyList() : GsonFactory.gson().fromJson(json , new TypeToken<List<Movie>>(){}.getType());
    }
}