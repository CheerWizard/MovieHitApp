package com.example.moviehitapp.utils.factories;

import com.google.gson.Gson;

public final class GsonFactory {
    public static synchronized Gson gson() {
        return new Gson();
    }
}
