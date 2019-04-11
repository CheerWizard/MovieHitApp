package com.example.moviehitapp.utils.factories;

import com.example.moviehitapp.annotations.Factory;
import com.google.gson.Gson;

@Factory
public final class GsonFactory {
    public static synchronized Gson gson() {
        return new Gson();
    }
}
