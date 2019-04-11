package com.example.moviehitapp.utils.sorters;

import com.example.moviehitapp.business_logic.data.Movie;
import com.example.moviehitapp.constants.SortOrder;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
/**Class that sorts entered elements in entered order*/
public final class MoviesSorter {
    public static synchronized List<Movie> sort(List<Movie> movieList , @NonNull SortOrder sortOrder) {
        switch (sortOrder) {
            case BY_MOVIES_ORIGINAL_TITLE:
                Collections.sort(movieList, ((o1, o2) -> o1.getOriginalTitle().compareTo(o2.getOriginalTitle())));
                break;
        }
        return movieList;
    }
}
