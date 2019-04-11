package com.example.moviehitapp.dependency_injection.modules;

import android.util.Log;

import com.example.moviehitapp.network.webservices.TMDBWebService;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Singleton
@Module(includes = {RetrofitModule.class})
public class NetworkModule {
    @Singleton
    @Provides
    static TMDBWebService provideTmdbWebService(@NonNull Retrofit retrofit) {
        Log.d("network module" , "started");
        return retrofit.create(TMDBWebService.class);
    }
}
