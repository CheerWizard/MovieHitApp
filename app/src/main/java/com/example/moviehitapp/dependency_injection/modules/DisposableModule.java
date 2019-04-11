package com.example.moviehitapp.dependency_injection.modules;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@Singleton
@Module
public class DisposableModule {
    @Singleton
    @Provides
    static Disposable provideCompositeDisposable() {
        Log.d("disposable module" , "started");
        return new CompositeDisposable();
    }
}
