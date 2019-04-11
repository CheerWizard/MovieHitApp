package com.example.moviehitapp.utils.converters;

import com.example.moviehitapp.utils.factories.GsonFactory;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;

import androidx.room.TypeConverter;

public final class GenreIdsConverter {
    @TypeConverter
    public static String fromGenreIds(List<Integer> genreIds) {
        return genreIds == null ? "" : GsonFactory.gson().toJson(genreIds);
    }
    @TypeConverter
    public static List<Integer> toGenreIds(String json) {
        return json == null ? Collections.emptyList() : GsonFactory.gson().fromJson(json , new TypeToken<List<Integer>>(){}.getType());
    }
}
