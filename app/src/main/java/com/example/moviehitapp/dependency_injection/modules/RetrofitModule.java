package com.example.moviehitapp.dependency_injection.modules;

import android.util.Log;

import com.example.moviehitapp.constants.ApiConstants;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class RetrofitModule {
    @NonNull
    @Singleton
    @Provides
    static Retrofit provideRetrofit() {
        Log.d("retrofit module" , "started");
        return new Retrofit.Builder()
                .baseUrl(ApiConstants.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
