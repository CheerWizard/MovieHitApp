package com.example.moviehitapp.dependency_injection.components;

import android.app.Application;

import com.example.moviehitapp.dependency_injection.modules.ActivityBindingModule;
import com.example.moviehitapp.dependency_injection.modules.ApplicationModule;
import com.example.moviehitapp.dependency_injection.modules.ContextModule;
import com.example.moviehitapp.ui.application.MovieHitApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<MovieHitApplication> {

    void inject(MovieHitApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}